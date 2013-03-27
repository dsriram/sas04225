/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver;

import java.io.IOException;
import static org.sas04225.DescriptorSetBuilder.Main.cache_dir;
import org.sas04225.imagereposerver.ServiceBroadcaster;
import org.sas04225.proto.RecognitionServerBackendInitProto;
import org.sas04225.proto.RecognitionServerBackendInitProto.RecognitionServerBackendInit;
import org.sas04225.recognitionserver.Server.Backend;
import org.sas04225.recognitionserver.Server.ImageLookupQueryClient;
import org.sas04225.recognitionserver.Server.ImageLookupServer;
/**
 *
 * @author sriram
 */

public class RecognitionServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        CacheMetadataHandler metaHandler = new CacheMetadataHandler();
        metaHandler.loadMetaData();
        IndexRangeLookup idxLookup = new IndexRangeLookup(metaHandler.builder_result);
        GroupNameLookup groupLookup = new GroupNameLookup(metaHandler.builder_result);
        System.out.println("Descriptor count: "+idxLookup.getTotalDescriptorCount());
        RecognitionServerBackendInit.Builder b = RecognitionServerBackendInitProto.RecognitionServerBackendInit.newBuilder();
        b.setDescriptorCount(idxLookup.getTotalDescriptorCount());
        String cache_file = System.getProperty("user.home")+"/"+cache_dir+"/"+"cache";
        b.setCachePath(cache_file);
        final Backend bcknd = new Backend();
        bcknd.initBackend(b.build());
        System.setProperty("tmpfs", "/run/user/sriram/");
        ImageLookupServer server = new ImageLookupServer(bcknd, idxLookup, groupLookup);
        server.testLookup();
        Thread imgLUS = server.startServer();
        System.out.println("ImageLookupServer running at: "+ImageLookupServer.getFirstNonLocalAddress().getHostAddress()+" "+ImageLookupServer.LISTENING_PORT);
        ServiceBroadcaster brdcaster = new ServiceBroadcaster( "ImageLookupServer", ImageLookupServer.SERVICE_TYPE, ImageLookupServer.LISTENING_PORT, "Image lookup server", ImageLookupServer.getFirstNonLocalAddress());
        Thread t = new Thread(brdcaster, "mDNS_Broadcaster");
        t.start();
        ImageLookupQueryClient testclient = new ImageLookupQueryClient("/home/sriram/Development/Databases/camera_repo/1.jpg");
        System.out.println(testclient.performLookup());
        imgLUS.join();
        t.join();
        imgLUS.join();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                bcknd.endProcess();
            }
        }));
    }
}
