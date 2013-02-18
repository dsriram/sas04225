/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver.Server;

import com.google.protobuf.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sas04225.proto.RecognitionServerBackendInitProto;

/**
 *
 * @author sriram
 */
public class Backend {

    public static final String backend_exe = "/home/sriram/Development/sas04225.git/ServerSideApp/RecognitionServerBackend/dist/Debug/Intel-Linux-x86/recognitionserverbackend";
    public InputStream pipe_in;
    public OutputStream pipe_out;
    private InputStream pipe_err;
    private Process p;

    public Backend() {
        try {
            final ProcessBuilder b = new ProcessBuilder(new String[]{backend_exe});
            b.redirectError(ProcessBuilder.Redirect.INHERIT);
            p = b.start();
            pipe_out = p.getOutputStream();
            pipe_in = p.getInputStream();
            pipe_err = p.getErrorStream();
//            Backend.startBackendLogger(pipe_err);
        } catch (IOException ex) {
            Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, "Unable to start process", ex);
        }
    }

    public void initBackend(RecognitionServerBackendInitProto.RecognitionServerBackendInit msg) {
        try {
            Backend.writeMessage(pipe_out, msg);
        } catch (IOException ex) {
            Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, "Unable to init backend", ex);
        }
    }

    public static void writeMessage(OutputStream out, Message msg) throws IOException {
        byte[] data = msg.toByteArray();
        int len = data.length;

        out.write(len);
        out.write(len >> 8);
        out.write(len >> 16);
        out.write(len >> 24);

        out.write(data);

        out.flush();
    }

    public static byte readMessageData  (InputStream in)  [] throws IOException {
        int len = 0;

        len = in.read();
        len |= (in.read()) << 8;
        len |= (in.read()) << 16;
        len |= (in.read()) << 24;

        byte[] data = new byte[len];
        in.read(data, 0, len);

        return data;
    }

    private static void startBackendLogger(final InputStream err) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner s = new Scanner(err);
                    while (true) {
                        String msg = s.next();
                        if(msg.equals("__@endl__")) {
                            msg = "\n";
                        }
                        if (s.ioException() == null) {
                            Logger.getLogger(Backend.class.getName()).log(Level.INFO, msg);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    Logger.getLogger(Backend.class.getName()).log(Level.INFO,null, e);
                }
            }
        });
        t.start();
    }
    
    public void waitFor() throws InterruptedException
    {
        p.waitFor();
    }
}
