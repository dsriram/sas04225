package org.sas04225.RadioMapStorageReader;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.sas04225.proto.WifiScanResultProto.WifiScanResult;
import static org.sas04225.proto.WifiScanResultProto.AccessPoint;
import java.io.FileOutputStream;
import java.io.*;
import java.util.*;


import static org.sas04225.proto.WifiScanResultProto.AccessPoint;
/* @author sriram
 16	18	  */

public class Main {

    static int no_of_ids = 0, no_of_loc = 1, row = -1;
    static java.util.Hashtable<String, Integer> bssids = new java.util.Hashtable<String, Integer>();
    static java.util.Hashtable<String, Integer> loc_tag = new java.util.Hashtable<String, Integer>();
    static int[][] vector = new int[100][100];
    static java.util.ArrayList<WifiScanResult> results = new ArrayList<>();
    
    static final String loc_tags_csv = "H:/loc_tags.csv";
    static final String bssids_csv = "H:/bssids.csv";
    static final String output_vector_csv ="H:/output_vector1.csv";
    static final String WifiReadingsFolder = "H:/wifireadings/";

    public static void get_vectors() {
        for (WifiScanResult res : results) {
            System.out.println();
            String location = res.getLocationTag();
//             System.out.println("Location :"+ location);
            if (loc_tag.containsKey(location)) {
                vector[++row][0] = loc_tag.get(location);
            }
            List<AccessPoint> accsPts = res.getResultList();
            for (AccessPoint pt : accsPts) {
                if (bssids.containsKey(pt.getBssid())) {
                    vector[row][bssids.get(pt.getBssid()) + 1] = (pt.getLevel());
                }
            }

        }


    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        int i = 0, j = 0;
        no_of_ids = 0;
        no_of_loc = 1;
        row = -1;
        File readings_folder = new File(WifiReadingsFolder);
        File[] files = readings_folder.listFiles();
        java.util.TreeSet<String> access_pts = new TreeSet<>();
        java.util.TreeSet<String> locations = new TreeSet<>();
        for (File f_name : files) {
            System.out.println("name" + f_name.getName());
//       for(i=0;i<2;i++)
//               {
            WifiRecordReader reader = new WifiRecordReader(WifiReadingsFolder + f_name.getName());
            WifiScanResult res = null;
            while ((res = reader.nextResult()) != null) {
                results.add(res);
                System.out.println();
                String location = res.getLocationTag();
                System.out.println(WifiReadingsFolder + f_name.getName());
                System.out.println("Location :" + location);
//                if (!(loc_tag.containsKey(location))) {
//                    loc_tag.put(location, no_of_loc++);
                    locations.add(location);
//                }
                //add to hashtable and locationtag file
                List<AccessPoint> accsPts = res.getResultList();
                for (AccessPoint pt : accsPts) {
                    System.out.println("bssid: " + pt.getBssid() + " level: " + pt.getLevel());
//                    if (!(bssids.containsKey(pt.getBssid()))) {
//                        bssids.put(pt.getBssid(), no_of_ids++);
                        access_pts.add(pt.getBssid());
//                    }
                }

            }
//           }

        }
        Iterator<String> itr = access_pts.iterator();
        while(itr.hasNext()) {
            bssids.put(itr.next(), no_of_ids++);
        }
        itr = locations.iterator();
        while (itr.hasNext()) {
            loc_tag.put(itr.next(), no_of_loc++);
        }
        
        //print bssids,loc_tags and write to file bssids and loc_tag.csv in c drive
        System.out.println("New hash locations: " + loc_tag);//the reference #table
        System.out.println("New hash table value: " + bssids);//the reference #table
        try (FileWriter writer = new FileWriter(bssids_csv)) {
            for (Map.Entry<String, Integer> entry : bssids.entrySet()) {
                String string = entry.getKey();
                Integer integer = entry.getValue();
                writer.write(string);
                writer.write(',');
                writer.write(integer.toString());
                writer.write('\n');
            }
            writer.flush();
            writer.close();
        }
        try (FileWriter writer1 = new FileWriter(loc_tags_csv)) {
            for (Map.Entry<String, Integer> entry : loc_tag.entrySet()) {
                String string = entry.getKey();
                Integer integer = entry.getValue();
                writer1.write(string);
                writer1.write(',');
                writer1.write(integer.toString());
                writer1.write('\n');
            }
            writer1.flush();
        }

        vector = new int[results.size()][bssids.size()];

        Main.get_vectors();
        try (FileWriter writer = new FileWriter(output_vector_csv)) {
            for (i = 0; i < results.size(); i++) {
                for (j = 0; j < bssids.size(); j++) {
                    writer.write(vector[i][j]);
                }
                writer.write('\n');
            }

            writer.flush();
            writer.close();
        }


    }
}