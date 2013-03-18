
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
   
 
	   static int no_of_ids=0,no_of_loc=1,row=-1;
          static java.util.Hashtable<String, Integer> bssids = new java.util.Hashtable<String, Integer>();
       static java.util.Hashtable<String, Integer> loc_tag = new java.util.Hashtable<String, Integer>();
        static int[][] vector= new int[100][100];
          
	   public static void get_vectors(File f_name1) throws IOException
	   { int i=0,j=0;
                   WifiRecordReader reader=null;
	      try {
	          reader = new WifiRecordReader("H:\\wifireadings\\" + f_name1.getName());
                  
	      } catch (FileNotFoundException ex) {
	         System.out.println("File open Error");
	      }
	         WifiScanResult res = null;
                
        while((res = reader.nextResult()) != null) {
        
	             System.out.println();
             String location = res.getLocationTag();
            // System.out.println("Location :"+ location);
            if(loc_tag.containsKey(location))
            { vector[++row][0]=loc_tag.get(location);}
                 List<AccessPoint> accsPts = res.getResultList();
	        for (AccessPoint pt : accsPts) {
                 if(bssids.containsKey(pt.getBssid()))
                 {  vector[row][bssids.get(pt.getBssid())+1]=(pt.getLevel());}
               }
            
    }
         
        
	 } 
           
         
	public static void main(String args[]) throws FileNotFoundException, IOException {
                int i=0,j=0;
                no_of_ids=0;no_of_loc=1;row=-1;
       File readings_folder = new File("H:\\wifireadings");
       File[] files = readings_folder.listFiles();
       for(File f_name : files)
       { System.out.println("name" + f_name.getName());
//       for(i=0;i<2;i++)
//               {
        WifiRecordReader reader = new WifiRecordReader("H:\\wifireadings\\" + f_name.getName());
        WifiScanResult res = null;
         while((res = reader.nextResult()) != null) {
            System.out.println();
            String location = res.getLocationTag();
            System.out.println("H:\\wifireadings\\" + f_name.getName());
           System.out.println("Location :"+ location);
           if(!(loc_tag.containsKey(location)))
            loc_tag.put(location,no_of_loc++);
            //add to hashtable and locationtag file
	             List<AccessPoint> accsPts = res.getResultList();
	             for (AccessPoint pt : accsPts) {
             System.out.println("bssid: "+pt.getBssid()+" level: "+pt.getLevel());   
                 if(!(bssids.containsKey(pt.getBssid())))
                { bssids.put(pt.getBssid(),no_of_ids++);}}
             
            } 
//           }
        
       }
       //print bssids,loc_tags and write to file bssids and loc_tag.csv in c drive
      System.out.println("New hash locations: " + loc_tag );//the reference #table
        System.out.println("New hash table value: " + bssids );//the reference #table
       String csv = "C:\\bssids.csv";
         try (FileWriter writer = new  FileWriter(csv)) {
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

        FileWriter writer1 = new  FileWriter("C:\\loc_tags.csv");
        for (Map.Entry<String, Integer> entry : loc_tag.entrySet()) {
                String string = entry.getKey();
                Integer integer = entry.getValue();
                writer1.write(string);
                writer1.write(',');
                writer1.write(integer.toString());
                writer1.write('\n');}
                writer1.flush();
                writer1.close();
                
                File readings_folder1 = new File("H:\\wifireadings");
       File[] files1 = readings_folder1.listFiles();
                for(File f_name : files1)
                {
                System.out.println("H:\\wifireadings\\" + f_name.getName());
                    get_vectors(f_name);
                
                }
                try (FileWriter writer = new  FileWriter("C:\\output_vector1.csv")) {
             for (i=0;i<row;i++) {
                 for(j=0;j<no_of_ids;j++)
                 {  writer.write("," + vector[i][j]);}
                 writer.write('\n');
             }
        
  writer.flush();
  writer.close();
         } 

        
}
 }