/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sas04225.DescriptorSetBuilder.RepoProvider.CameraRepo;

/**
 *
 * @author sriram
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static final String cache_dir = "Development/DescriptorSetCache";
    public static final String default_repo_dir = "Development/Databases/camera_repo";
    public static final String make_pipe = "mkfifo /tmp/common_pipe_in /tmp/common_pipe_out";
    public static final String rm_pipe = "rm /tmp/common_pipe_*";
    public static final String command = "/home/sriram/Development/sas04225.git/ServerSideApp/DescriptorSetBuilderBackend/dist/Debug/Intel-Linux-x86/descriptorsetbuilderbackend";
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        CameraRepo repo = new CameraRepo(System.getProperty("user.home")+"/"+default_repo_dir);
        repo.fetchDataSets();
        //Runtime.getRuntime().exec(make_pipe);
        final ProcessBuilder b = new ProcessBuilder(new String[]{command});
        Process p = b.start();
        OutputStream outstrm;
        InputStream instrm;
        outstrm = p.getOutputStream();
        instrm = p.getInputStream();
        BackendFeeder feeder = new BackendFeeder(outstrm, instrm, repo);
        feeder.pushImageGroups();
        Logger.getLogger(Main.class.getName()).log(Level.INFO, "Quitting");
    }
}
