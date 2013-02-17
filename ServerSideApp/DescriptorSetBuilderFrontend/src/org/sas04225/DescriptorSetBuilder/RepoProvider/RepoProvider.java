/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder.RepoProvider;

import java.util.List;

/**
 *
 * @author sriram
 */
public interface RepoProvider {

    public int fetchDataSets();

    public List<ImageGroup> getImageGroups();
}
