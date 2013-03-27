/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver.Server;

import com.google.protobuf.ByteString;
import com.google.protobuf.ByteString.Output;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sas04225.proto.RecognitionServerQueryProto;
import org.sas04225.proto.RecognitionServerQueryProto.Query;
import org.sas04225.proto.RecognitionServerResultProto.Result;

/**
 *
 * @author sriram
 */
public class ImageLookupQueryClient {
    
    private byte[] data;
    
    public ImageLookupQueryClient(byte[] img) {
        data = new byte[img.length];
        System.arraycopy(img, 0, data, 0, img.length);
    }
    
    public ImageLookupQueryClient(String filename) {
        File file = new File(filename);
        data = new byte[(int)file.length()];
            try (FileInputStream in = new FileInputStream(file)) {
                in.read(data);
        } catch (IOException ex) {
            Logger.getLogger(ImageLookupQueryClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Result performLookup() {
        Result r = null;
        try {
            ByteString bs = ByteString.copyFrom(data);
            System.out.println(bs.size());
            Query q = Query.newBuilder().setRequestId(System.currentTimeMillis()).setLocationId(0xFFFFFFFF).setQueryType(RecognitionServerQueryProto.QueryType.GENERAL_QUERY).setQueryDataType(RecognitionServerQueryProto.QueryDataType.JPEG_IMAGE).setQueryData(bs).build();
            InetAddress addr = ImageLookupServer.getFirstNonLocalAddress();
            int sPort = ImageLookupServer.LISTENING_PORT;
            try (Socket sock = new Socket(addr, sPort)) {
                ImageLookupServer.writeMessage(sock.getOutputStream(), q);
                byte[] response = ImageLookupServer.readMessageData(sock.getInputStream());
                r = Result.parseFrom(response);
            }
        } catch (SocketException ex) {
            Logger.getLogger(ImageLookupQueryClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        return r;
    }
    }
}
