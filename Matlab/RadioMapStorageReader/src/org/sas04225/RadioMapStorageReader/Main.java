package org.sas04225.RadioMapStorageReader;

import java.io.FileNotFoundException;
import java.util.List;
import static org.sas04225.proto.WifiScanResultProto.WifiScanResult;
import java.io.*;
import java.util.*;


import static org.sas04225.proto.WifiScanResultProto.AccessPoint;
/* @author sriram
 16	18	  */

public class Main {

    static int no_of_ids = 0, no_of_loc = 0,no_of_testloc=0, row = -1;
    static java.util.Hashtable<String, Integer> bssids = new java.util.Hashtable<String, Integer>();
    static java.util.Hashtable<String, Integer> loc_tag = new java.util.Hashtable<String, Integer>();
     static java.util.Hashtable<String, Integer> test_loc_tag = new java.util.Hashtable<String, Integer>();
    
    static int[][] vector = new int[100][100];
     static int[][] test_vector = new int[100][100];
    static java.util.ArrayList<WifiScanResult> results = new ArrayList<>();
     static java.util.ArrayList<WifiScanResult> test_results = new ArrayList<>();
    
    static final String loc_tags_csv = "H:/loc_tags.csv";
    static final String test_loc_tags_csv = "H:/test_loc_tags.csv";
    
    static final String bssids_csv = "H:/bssids.csv";
    static final String output_vector_csv ="C:/output_vectorsnew.csv";
    static final String test_vector_csv ="C:/output_testvectors.csv";
    
    static final String WifiReadingsFolder = "H:/wifireadingsnew/";
static final String testfolder = "H:/wifitestdata/";

    public static void get_vectors() throws IOException {
        row = -1;
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
                    vector[row][bssids.get(pt.getBssid())] = (pt.getLevel());
                }
            }

        }

    }
     public static void get_test_vectors() throws IOException {
         
        java.util.TreeSet<String> test_locations = new TreeSet<>();
    File readings_folder = new File(testfolder);
        File[] files = readings_folder.listFiles();
     for (File f_name : files) {
            System.out.println("name" + f_name.getName());
WifiRecordReader test_reader = new WifiRecordReader(testfolder + f_name.getName());
            WifiScanResult res = null;
            while ((res = test_reader.nextResult()) != null) {
                test_results.add(res);
                System.out.println();
                String location = res.getLocationTag();
                System.out.println(testfolder + f_name.getName());
                System.out.println("Location :" + location);
//                if (!(test_loc_tag.containsKey(location))) {
             //    test_loc_tag.put(location, no_of_testloc++);
                    test_locations.add(location);
            }
     }
     
     Iterator<String> itr = test_locations.iterator();
        while (itr.hasNext()) {
            test_loc_tag.put(itr.next(), no_of_testloc++);
        }
        
         try (FileWriter writer1 = new FileWriter(test_loc_tags_csv)) {
            for (Map.Entry<String, Integer> entry : test_loc_tag.entrySet()) {
                String string = entry.getKey();
                Integer integer = entry.getValue();
                writer1.write(string);
                writer1.write(',');
                writer1.write(integer.toString());
                writer1.write('\n');
            }
            writer1.flush();
        }
        row = -1;
       
        for (WifiScanResult mes : test_results) {
            System.out.println();
            String location = mes.getLocationTag();
//             System.out.println("Location :"+ location);
            if (test_loc_tag.containsKey(location)) {
                test_vector[++row][0] = test_loc_tag.get(location);
            }
            List<AccessPoint> accsPts = mes.getResultList();
            for (AccessPoint pt : accsPts) {
                if (bssids.containsKey(pt.getBssid())) {
                    test_vector[row][bssids.get(pt.getBssid())] = (pt.getLevel());
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
            System.out.println("Results len: "+results.size());
            
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
            bssids.put(itr.next(), ++no_of_ids);
        }
        itr = locations.iterator();
        while (itr.hasNext()) {
            loc_tag.put(itr.next(), no_of_loc++);
        }
        
        //print bssids,loc_tags and write to file bssids and loc_tag.csv in c drive
        System.out.println("New hash locations: " + loc_tag);//the reference #table
        System.out.println("Hash table len : "+bssids.size());
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

        vector = new int[results.size()][bssids.size()+1];
        test_vector = new int[results.size()][bssids.size()+1];

 
        
        System.out.println();
        System.out.println("Results len: "+results.size());
get_vectors();
get_test_vectors();
        try (FileWriter writer = new FileWriter(output_vector_csv)) {
            for (i = 0; i < vector.length; i++) {
                for (j = 0; j < vector[i].length ; j++) {
                    writer.write(","+vector[i][j]);                }
                writer.write('\n');
                // System.out.println("vector :"+ vector[i]);
            }

            writer.flush();
            writer.close();
        }
        
        
         try (FileWriter writer = new FileWriter(test_vector_csv)) {
            for (i = 0; i < vector.length; i++) {
                for (j = 0; j < vector[i].length ; j++) {
                    writer.write(","+test_vector[i][j]);                }
                writer.write('\n');
                // System.out.println("vector :"+ vector[i]);
            }

            writer.flush();
            writer.close();
        }
         
         java.io.ObjectOutputStream objos = new ObjectOutputStream(new FileOutputStream("C:/bssidsNlocids.hashtable"));
         objos.writeObject(bssids);
         objos.writeObject(loc_tag);
         objos.flush();
         objos.close();


    }
}