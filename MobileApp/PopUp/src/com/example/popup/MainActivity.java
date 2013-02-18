package com.example.popup;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

// Setting Dialog Title
alertDialog.setTitle("Alert Dialog");

// Setting Dialog Message
alertDialog.setMessage("Welcome to wifiConnection");

// Setting Icon to Dialog


// Setting OK Button
alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(final DialogInterface dialog, final int which) {
        // Write your code here to execute after dialog closed
       
        }
});

// Showing Alert Message
alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }
    
}
