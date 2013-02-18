/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.recognitionserver;

import java.util.List;
import org.sas04225.DescriptorSetBuilder.DescriptorSetBuilderResult;

/**
 *
 * @author sriram
 */
public class IndexRangeLookup {
    
    private volatile long[][] ranges;
    
    public IndexRangeLookup(List<DescriptorSetBuilderResult> builder_result)
    {
        ranges = new long[builder_result.size()][2];
        for(int i=0 ;i<builder_result.size();i++) {
            DescriptorSetBuilderResult result = builder_result.get(i);
            ranges[i][0] = result.startIndex;
            ranges[i][1] = result.endIndex;
        }
    }
    
    public int lookup(long trainIdx)
    {
        int groupIdx = -1;
        for(int i=0;i<ranges.length;i++)
        {
            if(trainIdx>ranges[i][1])
            {
                continue;
            }
            if(trainIdx<= ranges[i][1] && trainIdx >= ranges[i][0])
            {
                groupIdx = i;
                break;
            }
        }
        return groupIdx;
    }
    
    public long getTotalDescriptorCount()
    {
        return ranges[ranges.length-1][1]+1;
    }
    
}
