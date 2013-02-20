package org.sas04225.wificonnection;


import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity1 extends Activity {
	
	private WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    }
       
     
        @SuppressWarnings("deprecation")
		public void onButtonClick(View button) {
        	
        	if ((wifi.isWifiEnabled()) && (wifi.getConnectionInfo() != null)
    				&& (wifi.getConnectionInfo().getIpAddress() != 0)) {
        		EditText Urlis=(EditText)findViewById(R.id.location_tag);
            	Intent a = new Intent(MainActivity1.this,MainActivity.class);
            	final String fname=Urlis.getText().toString();
            	a.putExtra("fileName",fname );
    			startActivity(a);
    			
    		} else {
    			Toast t = Toast.makeText(getApplicationContext(),"Wifi not enabled", Toast.LENGTH_SHORT);
    			t.show();
    		}
            
        }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.activity_main_activity1, menu);
        return true;
    
    
    }
}