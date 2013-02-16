package org.sas04225.wificonnection;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;


@SuppressLint("NewApi") public class AlertPopupDialogue extends DialogFragment{
	 public static interface NoticeDialogListener {
	        public void onDialogPositiveClick(DialogFragment dialog);
	        public void onDialogNegativeClick(DialogFragment dialog);
	    }
	 NoticeDialogListener mListener;
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
                   getDialog().cancel();
               }
           });
		 return null;
	 }
		 @Override
		    public void onAttach(Activity activity) {
		        super.onAttach(activity);
		        try {
		        	 mListener = (NoticeDialogListener) activity;
		        }catch (ClassCastException e) {
		            // The activity doesn't implement the interface, throw exception
		            throw new ClassCastException(activity.toString()
		                    + " must implement NoticeDialogListener");
			
		 
	 }

}}
