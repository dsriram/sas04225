package com.example.dialogactivity;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1);}
        private static final String TAG = "DialogActivity";
        private static final int DLG_EXAMPLE1 = 0;
        private static final int TEXT_ID = 0;
     
        public void onCreate1(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_activity1);
        }
     
        @SuppressWarnings("deprecation")
		public void onButtonClick(View button) {
            // Creates the dialog if necessary, then shows it.
            // Will show the same dialog if called multiple times.
            showDialog(DLG_EXAMPLE1);
        }
     
        /**
         * Called to create a dialog to be shown.
         */
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
     
        /**
         * Create and return an example alert dialog with an edit text box.
         */
        private Dialog createExampleDialog() {
     
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hello User");
            builder.setMessage("LOCATION TAG");
     
             // Use an EditText view to get user input.
             final EditText input = new EditText(builder.getContext());
             input.setId(TEXT_ID);
             builder.setView(input);
     
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
     
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    String value = input.getText().toString();
                    Log.d(TAG, "LOCATION" + value);
                    return;
                }
            });
     
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
     
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
     
            return builder.create();
        
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.activity_main_activity1, menu);
        return true;
    
    
    }
}