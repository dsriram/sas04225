/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    
    public static void main(String[] args) throws FileNotFoundException {
        CameraRepo repo = new CameraRepo(System.getProperty("user.home")+"/"+default_repo_dir);
        repo.fetchDataSets();
        FileOutputStream strm = new FileOutputStream("/home/sriram/Desktop/log.txt");
        BackendFeeder feeder = new BackendFeeder(strm, null, repo);
        feeder.pushImageGroups();
        Logger.getLogger(Main.class.getName()).log(Level.INFO, "Quitting");
    }
}
