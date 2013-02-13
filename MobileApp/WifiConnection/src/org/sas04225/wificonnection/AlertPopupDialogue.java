package org.sas04225.wificonnection;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

@SuppressLint("NewApi") public class AlertPopupDialogue extends DialogFragment{
	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setTitle(R.string.alert_title);
		 LayoutInflater inflater = getActivity().getLayoutInflater();
		 builder.setView(inflater.inflate(R.layout.alert_popup_dialogue,null)).setPositiveButton(R.string.ok_but,new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				
			}
		})
		 .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   AlertPopupDialogue.this.getDialog().cancel();
               }
           });      
		 
		 
			return null;
		 
	 }

}
