/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder.RepoProvider;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sriram
 */
public class CameraRepo implements RepoProvider{
    
    private List<ImageGroup> image_groups;
    private String repo_dir;

    public CameraRepo(String repo_dir) {
        if (!isRepoDir(repo_dir)) {
            throw new java.lang.IllegalArgumentException(repo_dir + " is not a repo dir");
        }
        this.repo_dir = repo_dir;
        image_groups = new java.util.ArrayList<>();
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
        File _repo = new File(repo_dir);
        _repo = _repo.getAbsoluteFile();
        File[] dirs = _repo.listFiles(new FileFilter(){

            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        image_groups.clear();
        for(File group:dirs)
        {
            ImageGroup imgg = this.buildGroup(group);
            if(imgg != null)
            {
                image_groups.add(imgg);
            }
        }
        return image_groups.size();
    }
    
    private CameraRepoImageGroup buildGroup(File group_directory)
    {
        File[] images = group_directory.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(".jpg")||name.endsWith(".jpeg"))
                {
                    return true;
                }
                return false;
            }
        } );
        if((images == null)||(images.length == 0)) {
            return null;
        }
        else
        {
            CameraRepoImageGroup group = new CameraRepoImageGroup();
            String[] parts = group_directory.getAbsolutePath().split(File.separator);
            String group_name = parts[parts.length-1];
            System.out.println("Group Name:"+group_name);
            URI[] files = new URI[images.length];
            for (int i = 0; i < images.length; i++) {
                files[i] = images[i].getAbsoluteFile().toURI();
                System.out.println(files[i].toASCIIString());
            }
            group.setGroupName(group_name);
            group.addImages(files);
            return group;
        }
    }

    @Override
    public List<ImageGroup> getImageGroups() {
        return image_groups;
    }
    
    private class CameraRepoImageGroup implements ImageGroup
    {
        private String group_name;
        private List<URI> images;
        
        public void setGroupName(String group_name)
        {
            this.group_name = group_name;
        }
        
        public void addImages(URI[] image_uris)
        {
            ArrayList<URI> list = new ArrayList<>(image_uris.length);
            java.util.Collections.addAll(list, image_uris);
            images = list;
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
