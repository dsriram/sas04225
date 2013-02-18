/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.DescriptorSetBuilder;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author sriram
 */
public class DescriptorSetBuilderResult {
    public final String group_name;
    public final int descriptor_count;
    public final long startIndex,endIndex;
    
    public DescriptorSetBuilderResult(String group_name,int descriptor_count,long startIndex,long endIndex)
    {
        this.group_name = group_name;
        this.descriptor_count = descriptor_count;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Group Name: ").append(group_name).append(" count:").append(descriptor_count).append(" index: [").append(startIndex).append(",").append(endIndex).append("]");
        return str.toString();
    }
    
    public static DescriptorSetBuilderResult fromDataStream(DataInputStream in) throws IOException
    {
        if(in.available() == 0) {
            return null;
        }
        String name = in.readUTF();
        int count = in.readInt();
        long start = in.readLong();
        long end = in.readLong();
        DescriptorSetBuilderResult result = new DescriptorSetBuilderResult(name, count, start, end);
        return result;
    }
}
