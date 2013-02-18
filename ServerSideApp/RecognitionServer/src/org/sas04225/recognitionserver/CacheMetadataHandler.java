/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver;

/**
 *
 * @author sriram
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sas04225.DescriptorSetBuilder.DescriptorSetBuilderResult;
import static org.sas04225.DescriptorSetBuilder.Main.cache_dir;

public class CacheMetadataHandler {
    
    List<DescriptorSetBuilderResult> builder_result;
    
    public CacheMetadataHandler() {
        builder_result = null;
    }
    
    public void loadMetaData() {
        DataInputStream in = null;
        String metadata_file = System.getProperty("user.home")+"/"+cache_dir+"/"+"metadata";
        try {
            in = new DataInputStream(new FileInputStream(metadata_file));
            builder_result = new ArrayList<>();
            while(true)
            {
                DescriptorSetBuilderResult result = DescriptorSetBuilderResult.fromDataStream(in);
                if(result == null) {
                    break;
                }
                builder_result.add(result);
                System.out.println(result);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CacheMetadataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CacheMetadataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(CacheMetadataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
