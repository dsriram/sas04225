/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder.RepoProvider;

import java.io.File;
import java.net.URI;
import java.util.List;

/**
 *
 * @author sriram
 */
public class CameraRepo implements RepoProvider{
    
    private List<ImageGroup> image_groups;
    private String repo_dir;

    public CameraRepo(String repo_dir) {
        if (!isRepoDir(repo_dir)) {
            throw new java.lang.IllegalArgumentException("Not a repo dir");
        }
        this.repo_dir = repo_dir;
    }
    

    private static boolean isRepoDir(String repo_dir) {
        File dir = new File(repo_dir);
        boolean isRepo = false;
        if (dir.exists() && dir.isDirectory() && dir.canRead()) {
            String[] children = dir.list();
            for (String child : children) {
                if (child.endsWith(".camera_repo")) {
                    isRepo = true;
                }
            }
        }
        return isRepo;
    }

    @Override
    public int fetchDataSets() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ImageGroup> getImageGroups() {
        return image_groups;
    }
    
    private class CameraRepoImageGroup implements ImageGroup
    {
        private String group_name;
        private List<URI> images;
        
        public CameraRepoImageGroup(String group_dir)
        {
            
        }
        
        @Override
        public String getGroupName() {
            return group_name;
        }

        @Override
        public int getImageCount() {
            return images.size();
        }

        @Override
        public List<URI> getImages() {
            return images;
        }
        
    }
}
