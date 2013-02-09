/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.imagereposerver;

/**
 *
 * @author sriram
 */
import java.io.*;
import java.net.*;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageRepoServer implements Runnable {

    public static final int MAX_CONN = 3;
    public static String mDNS_SERVICE_TYPE = "_imageRepoServer._raw._tcp.local";
    private File repoDir;
    private InetAddress bindAddress;
    private int port;
    private ServerSocketChannel sock;
    private boolean run_server;

    public ImageRepoServer(File repo, InetAddress addr, int port) {
        if (repo.exists() && repo.isDirectory() && repo.canWrite()) {
            repoDir = repo;
        } else {
            throw new java.lang.IllegalArgumentException("Cannot write to the path " + repo.getPath());
        }

        bindAddress = addr;
        this.port = port;
        run_server = true;
    }

    @Override
    public void run() {
        try {
            sock = ServerSocketChannel.open();
            sock = sock.bind(new InetSocketAddress(bindAddress, port), MAX_CONN);
            sock.configureBlocking(true);
            while (run_server) {
                SocketChannel incoming = sock.accept();
                if (incoming == null) {
                    continue;
                }
                Socket incoming_sock = incoming.socket();
                incoming_sock.setSoTimeout(5000); //Set read() timeout for 5s
                incoming_sock.setTrafficClass(0x04);//Set IP_TOS for Reliability
                ConnectionHandler cHandler = new ConnectionHandler(incoming_sock);
                Thread t = new Thread(cHandler);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageRepoServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sock.close();
            } catch (IOException ex) {
                Logger.getLogger(ImageRepoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopServer() {
        run_server = false;
    }

    public class ConnectionHandler implements Runnable {

        public static final int HEADER = 0xCAFECAFE;
        private Socket sock;
        private DataInputStream in;
        private DataOutputStream out;
        private int file_count;
        private String folder_name, ext;

        public ConnectionHandler(Socket sock) {
            this.sock = sock;
        }

        private void init() throws IOException {
            sock.getChannel().finishConnect();
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
            out.writeInt(HEADER);
            out.flush();
        }

        private void readParams() throws IOException {
            if (in.readInt() == HEADER) {
                file_count = in.readInt();
                folder_name = in.readUTF();
                ext = in.readUTF();
            } else {
                throw new java.io.IOException("Invalid header");
            }
        }

        private void setUpFiles() throws IOException {
            File folder = new File(repoDir.getAbsolutePath() + File.separator + folder_name);
            if (folder.exists()) {
                File[] children = folder.listFiles();
                for (File f : children) {
                    f.delete();
                }
            } else {
                if (!folder.mkdir()) {
                    throw new IOException("Unable to create directory " + folder.getAbsolutePath());
                }
            }
            for (int i = 0; i < file_count; i++) {
                File f = new File(folder.getAbsolutePath() + File.separator + i + ext);
                f.createNewFile();
            }

        }

        private void fetchFile(int index, long length) throws IOException {
            SocketChannel incoming = sock.getChannel();
            FileChannel ch;
            ch = (new FileOutputStream(repoDir.getAbsolutePath() + File.separator + folder_name + File.separator + index + ext)).getChannel();
            ch.transferFrom(incoming, 0, length);
            ch.close();
        }

        @Override
        public void run() {

            Logger logger = Logger.getLogger(ConnectionHandler.class.getCanonicalName());
            try {
                logger.log(Level.INFO, "ImageRepoServer on {0}:{1}", new Object[]{sock.getInetAddress(), sock.getPort()});
                init();
                logger.log(Level.INFO, "Client Connected");
                readParams();
                setUpFiles();
                logger.log(Level.INFO, "Fetching files and writing");
                for (int i = 0; i < file_count; i++) {
                    int index;
                    long length;
                    index = in.readInt();
                    length = in.readLong();
                    fetchFile(index, length);
                }

            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error when recieving the files", ex);
            } finally {
                try {
                    sock.close();
                    logger.log(Level.INFO, "Client Disconnected");
                } catch (IOException ex) {
                    Logger.getLogger(ImageRepoServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
