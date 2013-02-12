/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder.RepoProvider;

import java.net.URI;
import java.util.List;

/**
 *
 * @author sriram
 */
public interface ImageGroup {

    public String getGroupName();

    public int getImageCount();

    public List<URI> getImages();
}
