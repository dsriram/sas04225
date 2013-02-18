package org.sas04225.wificonnection;




import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Hashtable;
import org.sas04225.wificonnection.AlertPopupDialogue.NoticeDialogListener;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint("NewApi") public class MainActivity extends FragmentActivity implements NoticeDialogListener{
	
	
	protected static final String TAG = null;
	private String filename,record_dir;
	String BSSID="";
	 String SSID="";
	 String RSSID="";
	 String disp = "";
	
	private WifiManager wifi;
	
	private long TIMEOUT = 8000;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
			
		 setContentView(R.layout.activity_main);
		 final TextView Res = (TextView) findViewById(R.id.textView1);
		 wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
         final Button button = (Button) findViewById(R.id.buttonScan);
         Toast toast = Toast.makeText(getApplicationContext(),  "Sleep time: "+this.TIMEOUT+"ms", Toast.LENGTH_SHORT);
     	toast.show();
         
         button.setOnClickListener(new View.OnClickListener() {
            

			public void onClick(View v) {
				try{
				DialogFragment popup = new AlertPopupDialogue();
				popup.show(getFragmentManager(), "pop");
				Log.d("PROJECT1","popup hidden: "+popup.isHidden());
				}catch(NullPointerException ex)
				{
					Log.e("Pp", "null ptr", ex);
				}
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
         }});
     }
	File root = Environment.getExternalStorageDirectory();
	File record_dir1 = new File(root.getAbsolutePath(),"/WifiRecording");{
	if (!record_dir1.exists())
		
	{
		record_dir1.mkdir();
	}
	this.record_dir = record_dir1.getAbsolutePath();
	filename = "recording";}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		WifiRecord record= new WifiRecord(record_dir1);
		try {
			record.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String location_tag="";
		record.addLocation(location_tag,new String[] {}, new int[] {});
		
	}


	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	
}
class WifiScanAsync extends AsyncTask<Void, Void, Hashtable<String,Integer>> {
	 
public final long TIMEOUT = 5000;
 
android.net.wifi.WifiManager wifi;
java.util.Hashtable<String,Integer> result;
 
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
           	 
           	 
			for (int i = 0; i < wifiList.size(); i++) {
                    ScanResult scanResult = wifiList.get(i);
                     result.put(scanResult.BSSID,scanResult.level);
                    Log.d("ScanResult"," "+scanResult.BSSID+" "+scanResult.level+"dBm");
            }
            
            return result;
}
 
protected void onPostExecute(Hashtable<String, Integer> result) {
super.onPostExecute(result);
 //TODO show the popup window
 
 // Create a method inside MainActivity and call it from here directly
}
 
}