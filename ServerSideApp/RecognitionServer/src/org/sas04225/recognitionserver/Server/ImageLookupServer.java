/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver.Server;

import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.ServiceInfo;
import org.sas04225.proto.LookupQueryProto;
import org.sas04225.proto.LookupResultProto;
import org.sas04225.proto.LookupResultProto.DMatch;
import org.sas04225.proto.RecognitionServerQueryProto.Query;
import org.sas04225.proto.RecognitionServerResultProto.Result;
import org.sas04225.proto.RecognitionServerResultProto.Tag;
import org.sas04225.recognitionserver.GroupNameLookup;
import org.sas04225.recognitionserver.IndexRangeLookup;

/**
 *
 * @author sriram
 */
public class ImageLookupServer {

    private IndexRangeLookup indxLukUp;
    private GroupNameLookup grpLukUp;
    private Backend bcknd;
    public static final int LISTENING_PORT = 7325;
    public static final String SERVICE_TYPE = "_imageLookupServer._raw._tcp.local";
    public static final int MAX_SIMULTANEOUS_CONN = 5;

    public ImageLookupServer(Backend backend, IndexRangeLookup iLU, GroupNameLookup gLU) {
        indxLukUp = iLU;
        grpLukUp = gLU;
        bcknd = backend;
    }

    public Thread startServer() {
        Thread t = null;
        try {
//            final DatagramSocket sock = new DatagramSocket(LISTENING_PORT, ImageLookupServer.getFirstNonLocalAddress());
            final ServerSocketChannel sock;
            sock = ServerSocketChannel.open();
            sock.bind(new InetSocketAddress(ImageLookupServer.getFirstNonLocalAddress(), LISTENING_PORT));
            sock.configureBlocking(true);
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            SocketChannel incoming = sock.accept();
                            try (Socket incoming_sock = incoming.socket()) {
                                incoming_sock.setSoTimeout(5000); //Set read() timeout for 5s
                                incoming_sock.setTrafficClass(0x04);//Set IP_TOS for Reliability
                                byte[] msg = ImageLookupServer.readMessageData(incoming_sock.getInputStream());
                                Query q = Query.parseFrom(msg);
                                switch (q.getQueryType()) {
                                    case GENERAL_QUERY:
                                        Result result = processGeneralQuery(q);
                                        ImageLookupServer.writeMessage(incoming_sock.getOutputStream(), result);
                                        break;
                                    case LOCATION_BASED_QUERY:
                                        processLocationBasedQuery(q);
                                        break;
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ImageLookupServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }, "ImageLookupServerThread");
            t.setDaemon(true);
            t.start();
            

        } catch (SocketException ex) {
            Logger.getLogger(ImageLookupServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return t;
        }
    }

    public Result processGeneralQuery(Query q) {
        Result.Builder msg = Result.newBuilder();
        switch (q.getQueryDataType()) {
            case JPEG_IMAGE:
                ByteString str = q.getQueryData();
                byte[] img_data = str.toByteArray();
                String filename = System.getProperty("tmpfs") + q.getRequestId() + ".jpg";
                File img = new File(filename);
                try {
                    img.createNewFile();
                    try (FileOutputStream f = new FileOutputStream(img)) {
                        f.write(img_data);
                        f.flush();
                    }
                    LookupQueryProto.LookupQuery.Builder lukqb = LookupQueryProto.LookupQuery.newBuilder();
                    lukqb.setRequestId(q.getRequestId());
                    lukqb.setPath(filename);
                    LookupQueryProto.Descriptors des = LookupQueryProto.Descriptors.newBuilder().setDescriptorSize(0).addDescriptors(ByteString.EMPTY).build();
                    lukqb.setDescriptors(des);
                    Backend.writeMessage(bcknd.pipe_out, lukqb.build());
                    byte[] res_data = Backend.readMessageData(bcknd.pipe_in);
                    LookupResultProto.LookupResult result = LookupResultProto.LookupResult.parseFrom(res_data);
                    msg.addAllTags(this.getTags(result.getMatchesList()));
                    msg.setRequestId(q.getRequestId());
                    img.delete();
                } catch (IOException ex) {
                    Logger.getLogger(ImageLookupServer.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            case FREAK_DESCRIPTOR:
                Tag t = Tag.newBuilder().setCount(1).setName("Unknown").build();
                msg.addTags(t);
                msg.setRequestId(q.getRequestId());
                break;
        }
        return msg.build();
    }
    
    public List<Tag> getTags(List<LookupResultProto.DMatch> matches) {
        if(matches.isEmpty()) {
            Tag t = Tag.newBuilder().setCount(1).setName("Unknown").build();
            return Collections.nCopies(1, t);
        }
        else {
            ArrayList<Tag> tags = new ArrayList<>(50);
            HashMap<Integer, Integer> matchTable = new HashMap<>(50);
            for (DMatch match : matches) {
                int grpIdx = indxLukUp.lookup(match.getTrainIdx());
                if (matchTable.containsKey(grpIdx)) {
                    int count = matchTable.get(grpIdx) + 1;
                    matchTable.put(grpIdx, count);
                } else {
                    matchTable.put(grpIdx, 1);
                }
            }
            
            for (Map.Entry<Integer, Integer> entry : matchTable.entrySet()) {
                int grp = entry.getKey();
                int count = entry.getValue();
                String name = grpLukUp.lookup(grp);
                Tag t = Tag.newBuilder().setName(name).setCount(count).build();
                tags.add(t);
            }
            return tags;
        }
    }

    public Result processLocationBasedQuery(Query q) {
        Result.Builder msg = Result.newBuilder();
        Tag t = Tag.newBuilder().setCount(1).setName("Unknown").build();
        msg.addTags(t);
        msg.setRequestId(q.getRequestId());
        return msg.build();
    }

    public ServiceInfo getInfo() {
        return ServiceInfo.create(SERVICE_TYPE, "ImageLookupServer", LISTENING_PORT, 0, 0, "Image lookup server");
    }

    public void testLookup() {
        try {
            LookupQueryProto.LookupQuery.Builder lukqb = LookupQueryProto.LookupQuery.newBuilder();
            lukqb.setRequestId(0x123456);
            lukqb.setPath("/home/sriram/Development/Databases/camera_repo/1.jpg");
            LookupQueryProto.Descriptors des = LookupQueryProto.Descriptors.newBuilder().setDescriptorSize(0).addDescriptors(ByteString.EMPTY).build();
            lukqb.setDescriptors(des);
            Backend.writeMessage(bcknd.pipe_out, lukqb.build());
            byte[] res_data = Backend.readMessageData(bcknd.pipe_in);
            LookupResultProto.LookupResult result = LookupResultProto.LookupResult.parseFrom(res_data);
            System.out.println(processResult0(result));
        } catch (IOException ex) {
            Logger.getLogger(ImageLookupServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String processResult0(LookupResultProto.LookupResult result) {
        int i = 0;
        System.out.println("Total matches: " + result.getMatchesCount());
        HashMap<Integer, Integer> matches = new HashMap<>(50);
        for (DMatch match : result.getMatchesList()) {
            int grpIdx = indxLukUp.lookup(match.getTrainIdx());
            if (matches.containsKey(grpIdx)) {
                int count = matches.get(grpIdx) + 1;
                matches.put(grpIdx, count);
            } else {
                matches.put(grpIdx, 1);
            }
        }
        int maxCountGroup = 0, maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
            int grp = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                maxCountGroup = grp;
            }
        }
        return grpLukUp.lookup(maxCountGroup);
    }

    public static InetAddress getFirstNonLocalAddress() throws SocketException {
        InetAddress addr = null;
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface net_inf : Collections.list(interfaces)) {
            for (InetAddress address : Collections.list(net_inf.getInetAddresses())) {
                if (!(address.isLinkLocalAddress() || address.isLoopbackAddress() || (address instanceof java.net.Inet6Address))) {
                    addr = address;
                }
            }
        }
        if(addr == null) {
            try {
                addr = InetAddress.getByName("127.0.0.1");
            } catch (UnknownHostException ex) {
                Logger.getLogger(ImageLookupServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return addr;
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

    public static byte readMessageData   (InputStream in)  [] throws IOException {
        int len = 0;

        len = in.read();
        len |= (in.read()) << 8;
        len |= (in.read()) << 16;
        len |= (in.read()) << 24;

        byte[] data = new byte[len];
        int bytes_read = 0;
        while(bytes_read != len) {
            bytes_read += in.read(data, bytes_read, len-bytes_read);
        }
        return data;
    }
}
