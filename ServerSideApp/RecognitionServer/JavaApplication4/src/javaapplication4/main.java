package org.sas04225.RadioMapStorageReader;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aishu
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.sas04225.proto.WifiScanResultProto.WifiScanResult;


public class main {
    static int size=71;
    static int l1_size=71;
    static int l2_size=24;
    float [] inputs=new float [size];
    float [][] l1_w=new float [size][l1_size];// weights for l1
    float[] l2_w= new float [l2_size];
    float[] l1_bias=new float[l1_size];
    float l2_bias=0;
     static java.util.Hashtable<String, Integer> bssids = new java.util.Hashtable<String, Integer>();
    static java.util.Hashtable<String, Integer> loc_tag = new java.util.Hashtable<String, Integer>();
    
   void neural()
    {
    /*   l1_w={ };
                
        l2_w= {-0.20184 , 0.18465,0.20171,-0.18175,-0.19939,0.20325,-0.13101,0.2024,0.20078,0.19994,-0.20291,-0.19937,-0.19429,-0.2018,0.203,0.20351,-0.15049,0.13931,-0.20245,-0.030203,0.20252,0.20108,0.17637,0.20338};
                l1_bias={0.00072162,
 -0.00058759,
 -0.00072146,
 0.00056559,
 0.00070713,
 -0.00065799,
 0.00028843,
 -0.00071924,
 -0.00071721,
 -0.00071134,
 0.00070387,
 0.00070698,
 0.0006649,
 0.00072159,
 -0.00069778,
 -0.00050694,
 0.00037087,
 -0.00032001,
 0.00071859,
 0.00010479,
 -0.00071754,
 -0.00071899,
 -0.00052662,
 -0.0012728};
                l2_bias=-0.0041044;*/
    }
    
    void simulate()//pass input to this
    {
        float [] sum1 =new float[l2_size];
        float sum2 =0;
    for(int i=0;i<l2_size;i++)
    {
        for(int j=0;j<l1_size;j++)
        {
        sum1[i]=sum1[i] + l1_w[i][j]*inputs[j];
        }
        sum1[i]=sum1[i]+l1_bias[i];//add tansig func here
    }
    for(int i=0;i<l2_size;i++)
    {
       
    sum2=sum2 + sum1[i]*l2_w[i];
           
    }
    sum2=sum2+l2_bias;//add tansig fnc
    }
    
     public static void get_vectors(java.util.ArrayList<WifiScanResult> results ) throws IOException {
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
   public static void main(String args[]) throws FileNotFoundException, ClassNotFoundException
   {
        try {
            java.io.ObjectInputStream objos=new ObjectInputStream(new FileInputStream("C:/bssidsNlocids.hashtable"));
            bssids=(Hashtable<String, Integer>) objos.readObject();
            System.out.println("bssids:"+bssids);
            loc_tag=(Hashtable<String, Integer>) objos.readObject();
            System.out.println("loc:"+loc_tag);
        } catch (IOException ex) {
            //Logger.getLogger(neural.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in opening file");
        }
         }
    
}
    


