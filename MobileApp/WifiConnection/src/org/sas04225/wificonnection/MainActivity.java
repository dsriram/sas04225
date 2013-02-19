package org.sas04225.wificonnection;




import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Hashtable;



import org.sas04225.wificonnection.AlertPopupDialogue.NoticeDialogListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint("NewApi") public class MainActivity extends Activity{
	
	
	
	private String filename,record_dir;
	String BSSID="";
	 String SSID="";
	 String RSSID="";
	 String disp = "";
	 private static final String TAG = "DialogActivity";
     public static final int DLG_EXAMPLE1 = 0;
     private static final int TEXT_ID = 0;
     
		
	private WifiManager wifi;
	
	private long TIMEOUT = 4000;

	WifiScanAsync asyncTask;
	WifiRecord wifirec;
	ProgressBar progressBar1;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
			
		 setContentView(R.layout.activity_main);
		 progressBar1.findViewById(R.id.progressBar1);
		 progressBar1.setVisibility(View.INVISIBLE);
		 final TextView Res = (TextView) findViewById(R.id.textView1);
		 wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		 
		 File root = Environment.getExternalStorageDirectory();
			File record_dir1 = new File(root.getAbsolutePath()+"/RadioMapStorage");
			record_dir1.mkdirs();
			this.record_dir = record_dir1.getAbsolutePath();
			Intent a= getIntent();
	         String fname = a.getStringExtra("fileName");
			filename = record_dir+"/"+fname;
		 wifirec = new WifiRecord(filename);
		 asyncTask = new WifiScanAsync(wifi);
		
			
         try {
        	
			wifirec.init();
			 Log.i("init()wrking", fname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("init()", "pblm", e);
		}
         
         Toast toast = Toast.makeText(getApplicationContext(),  "Sleep time: "+this.TIMEOUT+"ms", Toast.LENGTH_SHORT);
     	toast.show();
	}
     	 @Override
         protected Dialog onCreateDialog(int id) {
      
             switch (id) {
                 case DLG_EXAMPLE1:
                     return createExampleDialog();
                 default:
                     return null;
             }
         }
      
         /**
          * If a dialog has already been created,
          * this is called to reset the dialog
          * before showing it a 2nd time. Optional.
          */
         @Override
         protected void onPrepareDialog(int id, Dialog dialog) {
      
             switch (id) {
                 case DLG_EXAMPLE1:
                     // Clear the input box.
                     EditText text = (EditText) dialog.findViewById(TEXT_ID);
                     text.setText("");
                     break;
             }
         }
         public void scanButtonHandler(View v){
        	
        	 asyncTask.execute(new Void[]{});
         }
      
         /**
          * Create and return an example alert dialog with an edit text box.
          */
         private Dialog createExampleDialog() {
         	
         	
             AlertDialog.Builder builder = new AlertDialog.Builder(this);
             builder.setTitle("Hello User");
             builder.setMessage("LOCATION TAG");
         	final Button button = (Button) findViewById(R.id.buttonScan);
              // Use an EditText view to get user input.
              final EditText input = new EditText(builder.getContext());
              input.setId(TEXT_ID);
              builder.setView(input);
      
             builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      
                 @Override
                 public void onClick(DialogInterface dialog, int whichButton) {
                	 
                     String value = input.getText().toString();
                     Log.d(TAG, "LOCATION" + value);
                     String[] BSSID = new String[]{};
                     Integer[] RSSID = new Integer[]{};
                     BSSID = asyncTask.result.keySet().toArray(BSSID);
                     RSSID= asyncTask.result.values().toArray(RSSID);
                     int[] rssid= new int[RSSID.length];
                     for(int i =0;i<RSSID.length;i++)
                     {
                    	 rssid[i]=RSSID[i].intValue();
                     }
                     wifirec.addLocation(value, BSSID,rssid );
                     
         			
              
                     return;
                 }
             });
      
             builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     return;
                 }
             });
      
            
         
     
         
         button.setOnClickListener(new View.OnClickListener() {
            

			public void onClick(View v) {
			 
					progressBar1.setVisibility(View.VISIBLE);
				
				//popup.show(getSupportFragmentManager(),"popup");
            	//wifi.startScan();
				  
            	Toast toast = Toast.makeText(getApplicationContext(),  "SCANNING....PLS WAIT!!!", Toast.LENGTH_SHORT);
            	toast.show();
            	 
//            	
//            	Log.d("PROJECT1","Wifi WAITING");
//                
//            	
//            	 try {
//                     Thread.sleep(TIMEOUT);
//                 } catch (InterruptedException e) {
//                     // TODO Auto-generated catch block
//                     e.printStackTrace();
//                 }
//            	 
//            	 List<ScanResult> wifiList = wifi.getScanResults();
//            	 
//            	 for (int i = 0; i < wifiList.size(); i++) {
//                     ScanResult scanResult = wifiList.get(i);                  
//                     SSID = scanResult.SSID;
//                     BSSID = scanResult.BSSID;
//                     RSSID = ""+scanResult.level;
//                     disp += SSID+"  "+BSSID+" "+RSSID+"\n";
//                 // Perform action on click
//             }
//            	 Res.setText(disp);
//            	 Log.i("Scan Results:", disp);
         }
		});
         
			progressBar1.setVisibility(View.VISIBLE);
         return builder.create();   
     }
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	//public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		//WifiRecord record= new WifiRecord(record_dir1);
		//try {
			//record.init();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		//String location_tag="";
		//record.addLocation(location_tag,new String[] {}, new int[] {});
		
	//}


	//public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		


	
//}
private class WifiScanAsync extends AsyncTask<Void, Void, Hashtable<String,Integer> > {
	 
public final long TIMEOUT = 5000;
 
android.net.wifi.WifiManager wifi;
public java.util.Hashtable<String,Integer> result;
 
public WifiScanAsync(android.net.wifi.WifiManager wifi) {
result = new java.util.Hashtable<String,Integer>();
this.wifi = wifi;
}
 
protected Hashtable<String, Integer> doInBackground(Void... params) {
            wifi.startScan();
           
           	
           	Log.d("WifiScanAsync","Scanning.. Thread sleep()");
               
           	
           	 try {
                    Thread.sleep(TIMEOUT);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
           	 
           	 List<ScanResult> wifiList = wifi.getScanResults();
           	 
           	 result.clear();
			for (int i = 0; i < wifiList.size(); i++) {
                    ScanResult scanResult = wifiList.get(i);
                     result.put(scanResult.BSSID,scanResult.level);
                    Log.d("ScanResult"," "+scanResult.BSSID+" "+scanResult.level+"dBm");
            }
			TextView textView1 = (TextView) findViewById(R.id.textView1);
 			textView1.setText(result.toString());
 			textView1.setVisibility(View.INVISIBLE);
			
            return result;
}
 
protected void onPostExecute(Hashtable<String, Integer> result) {
super.onPostExecute(result);
showDialog(MainActivity.DLG_EXAMPLE1);
 //TODO show the popup window
 
 // Create a method inside MainActivity and call it from here directly
}
 
}
}