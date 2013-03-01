/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sas04225.RadioMapStorageReader;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import static org.sas04225.proto.WifiScanResultProto.WifiScanResult;
import static org.sas04225.proto.WifiScanResultProto.AccessPoint;

/**
 *
 * @author sriram
 */
public class Main {
    
    public static void main(String args[]) throws FileNotFoundException {
        WifiRecordReader reader = new WifiRecordReader("/home/sriram/room");
        WifiScanResult res = null;
        java.util.HashSet<String> bssids = new HashSet<>();
        while((res = reader.nextResult()) != null) {
            System.out.println();
            String location = res.getLocationTag();
            System.out.println("Location :"+ location);
            List<AccessPoint> accsPts = res.getResultList();
            for (AccessPoint pt : accsPts) {
             System.out.println("bssid: "+pt.getBssid()+" level: "+pt.getLevel());   
             bssids.add(pt.getBssid());
            }
        }
    }
}
