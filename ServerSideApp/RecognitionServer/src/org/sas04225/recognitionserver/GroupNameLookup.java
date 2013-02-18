/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver;

import java.util.ArrayList;
import java.util.List;
import org.sas04225.DescriptorSetBuilder.DescriptorSetBuilderResult;

/**
 *
 * @author sriram
 */
public class GroupNameLookup {
    
    private volatile ArrayList<String> group_names;
    
    public GroupNameLookup(List<DescriptorSetBuilderResult> builder_result) {
        group_names = new ArrayList<>(builder_result.size());
        for (int i = 0; i < builder_result.size(); i++) {
            DescriptorSetBuilderResult result = builder_result.get(i);
            group_names.add(result.group_name);
        }
    }
    
    public String lookup(int groupIdx)
    {
        String name = null;
        try{
           name = group_names.get(groupIdx);
        }catch(ArrayIndexOutOfBoundsException e)
        {
            //TODO log the exception
        }
        return name;
    }
    
}
