package com.example.wificonnection;




import java.util.List;

import android.os.Bundle;

import android.app.Activity;
import android.content.Context;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.net.wifi.*;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
			
		 setContentView(R.layout.activity_main);

         final Button button = (Button) findViewById(R.id.menu_settings);
         button.setText("SCAN");
         button.setOnClickListener(new View.OnClickListener() {
            

			public void onClick(View v) {
            	WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            	wifi.startScan();
            	Context context = getApplicationContext();
            	CharSequence text = "SCANNING....PLS WAIT!!!";
            	int duration = Toast.LENGTH_LONG;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            	 List<ScanResult> wifiList = wifi.getScanResults();
            	 String BSS;
            	 String SSD;
            	 for (int i = 0; i < wifiList.size(); i++) {
                     ScanResult scanResult = wifiList.get(i);
                     BSS = scanResult.BSSID;
                     SSD = scanResult.SSID;
                     Context context1 = getApplicationContext();
                 	String text1 = BSS;
                 	int duration1 = Toast.LENGTH_LONG;

                 	Toast toast1 = Toast.makeText(context1, text1, duration1);
                 	toast1.show();
                 	Context context2 = getApplicationContext();
                	CharSequence text2 = SSD;
                	int duration2 = Toast.LENGTH_LONG;

                	Toast toast2 = Toast.makeText(context2, text2, duration2);
                	toast2.show();
                     
                     
                     
                     
                    


            	
            	 
            	 
                 // Perform action on click
             }
         }});
     }
 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
}