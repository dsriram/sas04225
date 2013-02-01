package org.sas04225.wificonnection;




import java.util.List;
import org.sas04225.wificonnection.R;


import android.os.Bundle;

import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.net.wifi.*;
import android.widget.TextView;


public class MainActivity extends Activity {

	
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
            	wifi.startScan();
            	Toast toast = Toast.makeText(getApplicationContext(),  "SCANNING....PLS WAIT!!!", Toast.LENGTH_SHORT);
            	toast.show();
            	
            	Log.d("PROJECT1","Wifi WAITING");
                
            	
            	 try {
                     Thread.sleep(TIMEOUT);
                 } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
            	 
            	 List<ScanResult> wifiList = wifi.getScanResults();
            	 String BSSID="";
            	 String SSID="";
            	 String RSSID="";
            	 String disp = "";
            	 for (int i = 0; i < wifiList.size(); i++) {
                     ScanResult scanResult = wifiList.get(i);                  
                     SSID = scanResult.SSID;
                     BSSID = scanResult.BSSID;
                     RSSID = ""+scanResult.level;
                     disp += SSID+"  "+BSSID+" "+RSSID+"\n";
                 // Perform action on click
             }
            	 Res.setText(disp);
            	 Log.i("Scan Results:", disp);
         }});
     }
 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
}