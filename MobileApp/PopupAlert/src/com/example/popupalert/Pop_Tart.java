package com.example.popupalert;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

@SuppressLint("NewApi") public class Pop_Tart extends DialogFragment {



	 public static interface NoticeDialogListener {
	        public void onDialogPositiveClick(DialogFragment dialog);
	        public void onDialogNegativeClick(DialogFragment dialog);
	    }
	 NoticeDialogListener mListener;
	 @TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setTitle(R.string.title);
		 LayoutInflater inflater = getActivity().getLayoutInflater();
		 builder.setView(inflater.inflate(R.layout.pop,null)).setPositiveButton(R.string.ok_but,new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
			
			}
		})
		 .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
              @TargetApi(Build.VERSION_CODES.HONEYCOMB) public void onClick(DialogInterface dialog, int id) {
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
