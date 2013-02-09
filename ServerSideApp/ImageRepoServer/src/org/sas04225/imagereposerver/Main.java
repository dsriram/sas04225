/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.imagereposerver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Logger;

/**
 *
 * @author sriram
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static final String DEFAULT_REPO_DIR = System.getProperty("user.home")+"/Development/Databases/camera_repo";
    public static final int REPO_SERVER_PORT = 33445;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 1) {
            System.out.println("Repository location not specified");
            System.out.println("Using default location : " + DEFAULT_REPO_DIR);
        }

        InetAddress addr;
        addr = ServiceBroadcaster.getFirstNonLocalAddress();
        System.out.println("Listening at "+addr.getHostAddress());
        int port = REPO_SERVER_PORT;
        String repo_dir = (args.length == 0) ? DEFAULT_REPO_DIR : args[0];

        File repo = new File(repo_dir);
        
        ImageRepoServer server = new ImageRepoServer(repo, addr, port);

        Thread s;
        s = new Thread(server, "Server Thread");
        s.start();

        Logger log = Logger.getLogger("ImageRepoTest:main");
        ServiceBroadcaster sb = new ServiceBroadcaster("ImageRepoServer", ImageRepoServer.mDNS_SERVICE_TYPE, port, "Image repo service", addr);
        Thread t = new Thread(sb, "mDNS_Broadcaster");
        t.start();
        s.join();
        t.join();
    }
}
