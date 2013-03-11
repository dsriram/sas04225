
	 package org.sas04225.RadioMapStorageReader;
     import java.io.FileNotFoundException;
	 import java.util.HashSet;
   import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
	 import static org.sas04225.proto.WifiScanResultProto.WifiScanResult;
	 import static org.sas04225.proto.WifiScanResultProto.AccessPoint;
	 
import static org.sas04225.proto.WifiScanResultProto.AccessPoint;
/* @author sriram
16	18	  */
 public class Main {
   
 
	   static int no_of_ids=0,no_of_loc=0;
          //static int[] vector= new int[50];
           List<Integer> vector = new java.util.ArrayList<Integer> ();
	   public static void get_vectors(String f_name)
	   { int i=0;
               java.util.Hashtable<String, Integer> bssids = new java.util.Hashtable<String, Integer>();
       java.util.Hashtable<String, Integer> loc_tag = new java.util.Hashtable<String, Integer>();
           for(i=0;i<no_of_ids;i++)//initialstn
          vector[i]=0;
               //read csv file to bssids and loc_tag
	    //java.util.Hashtable<String, Integer> cur_set = new java.util.Hashtable<String, Integer>();
	      WifiRecordReader reader=null;
	      try {
	          reader = new WifiRecordReader(f_name);
	      } catch (FileNotFoundException ex) {
	         System.out.println("File open Error");
	      }
	         WifiScanResult res = null;
     // java.util.HashSet<String>  = new HashSet<>();
     while((res = reader.nextResult()) != null) {
        
	             System.out.println();
             String location = res.getLocationTag();
             System.out.println("Location :"+ location);
             if(loc_tag.containsKey(location))
                       write to filein newline(loc_tag.get(location);
           List<AccessPoint> accsPts = res.getResultList();
	        for (AccessPoint pt : accsPts) {
                    //chk bssids here and get_values
	          // cur_set.put(pt.getBssid(),pt.getLevel());
                   if(bssids.containsKey(pt.getBssid()))
                       vector[bssids.get(pt.getBssid())]=(pt.getLevel());//wrote loc_tag in file
                      
                       
                   
	   }
    }
     //write vector to file, still in progress
    java.io.BufferedWriter out = new java.io.BufferedWriter(new java.io.FileWriter("C:/out_file.csv", true));
      out.newLine();
      out.append(vector[i]);
      out.close();
      out=null;
     System.out.println("Bssids and level for the location: " + cur_set );
        //write to csv file outpt.csv,cmp location and next vectors f length noofids
	 } 
           
         
	public static void main(String args[]) throws FileNotFoundException {
                int i=0;
       String f_name="H:/wifireadings/vv4";
       java.util.Hashtable<String, Integer> bssids = new java.util.Hashtable<String, Integer>();
       java.util.Hashtable<String, Integer> loc_tag = new java.util.Hashtable<String, Integer>();
       for(i=0;i<2;i++)
               {
        WifiRecordReader reader = new WifiRecordReader(f_name);
        WifiScanResult res = null;
         while((res = reader.nextResult()) != null) {
            System.out.println();
            String location = res.getLocationTag();
           // System.out.println("Location :"+ location);
            loc_tag.put(location,no_of_loc++);
            //add to hashtable and locationtag file
	             List<AccessPoint> accsPts = res.getResultList();
	             for (AccessPoint pt : accsPts) {
             System.out.println("bssid: "+pt.getBssid()+" level: "+pt.getLevel());   
                 if(!(bssids.containsKey(pt.getBssid())))
                { bssids.put(pt.getBssid(),no_of_ids++);}}
           //  System.out.println("bssid: "+pt.getBssid()+" level: "+pt.getLevel()+" Id "+no_of_ids);  } 
             f_name="H:/wifireadings/vv5";
            } 
           }
        
   
      System.out.println("New hash locations: " + loc_tag );//the reference #table
        System.out.println("New hash table value: " + bssids );//the reference #table
       //write to csv file
      get_vectors("H:/wifireadings/vv4");  
       get_vectors("H:/wifireadings/vv5");  
         //display file content
 	     }
}