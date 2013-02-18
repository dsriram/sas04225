package org.sas04225.wificonnection;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1);
        
    }
       
     
        @SuppressWarnings("deprecation")
		public void onButtonClick(View button) {
        	EditText Urlis=(EditText)findViewById(R.id.location_tag);
        	Intent a = new Intent(MainActivity1.this,MainActivity.class);
        	final String fname=Urlis.getText().toString();
        	a.putExtra("fileName",fname );
			startActivity(a);
            // Creates the dialog if necessary, then shows it.
            // Will show the same dialog if called multiple times.
            
        }
     
        /**
         * Called to create a dialog to be shown.
         */
        
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.activity_main_activity1, menu);
        return true;
    
    
    }
}