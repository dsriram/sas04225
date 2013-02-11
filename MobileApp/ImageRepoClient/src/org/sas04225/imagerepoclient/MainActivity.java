package org.sas04225.imagerepoclient;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final String PREFERENCE_NAME = "ImageRepoClientPref";
	private int count, current_count;
	private String group_name;
	private String host;
	private int port;
	private File temp_dir;
	private File[] toBeSent;
	private NumberPicker np;
	private boolean working_offline;

	private SharedPreferences setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		np = (NumberPicker) findViewById(R.id.numberPicker1);
		np.setMinValue(1);
		np.setMaxValue(10);
		temp_dir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator + "ImageRepoTempDir");
		temp_dir.mkdir();
		setting = this.getSharedPreferences(PREFERENCE_NAME, 0);
		Intent intent = this.getIntent();
		if (intent.getBooleanExtra("offline", false)) {
			working_offline = false;
			host = intent.getStringExtra("serverIP");
			port = intent.getIntExtra("serverPort", 12345);
		} else {
			working_offline = true;
		}
	}

	protected void onStart() {
		super.onStart();
		if (!working_offline)
			checkPendingUploads();
	}

	private void checkPendingUploads() {
		boolean uploads_pending = setting.getBoolean("uploads_pending", false);
		if (uploads_pending) {
			processPendingUploads();
		}
		SharedPreferences.Editor editor = setting.edit();
		editor.putBoolean("uploads_pending", false);
		editor.commit();
	}

	private void processPendingUploads() {
		File[] group = temp_dir.listFiles();
		if (group != null && group.length > 0) {
			for (int i = 0; i < group.length; i++) {
				if(group[i].isDirectory()) {
					String group_name = group[i].getName();
					File[] toBeSent = group[i].listFiles();
					if(toBeSent != null && toBeSent.length > 0)
					{
						uploadFiles(group_name,toBeSent);
					}
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onCapture(View v) {
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		group_name = editText1.getEditableText().toString();
		editText1.getEditableText().clear();
		count = np.getValue();
		toBeSent = new File[count];
		File group_dir = new File(temp_dir.getAbsolutePath()+"/"+group_name);
		group_dir.mkdir();
		for (int i = 0; i < count; i++) {
			toBeSent[i] = new File(temp_dir.getAbsolutePath() + "/"+group_name+"/"
					+ (System.currentTimeMillis() + i) + ".jpg");
		}
		current_count = 0;
		captureImage(current_count);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast t = Toast.makeText(getApplicationContext(), "Image captured",
				Toast.LENGTH_SHORT);
		t.show();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		current_count++;
		if (current_count == count) {
			if (working_offline) {
				Toast t0 = Toast.makeText(getApplicationContext(),
						this.group_name + " saved to cache", Toast.LENGTH_SHORT);
				t0.show();
				SharedPreferences.Editor editor = setting.edit();
				editor.putBoolean("uploads_pending", true);
				editor.commit();
			} else {
				uploadFiles(this.group_name, this.toBeSent);
			}

		} else {
			captureImage(current_count);
		}
	}

	private void captureImage(int index) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		Uri fileUri = Uri.fromFile(toBeSent[index]);
		Toast t = Toast.makeText(getApplicationContext(), fileUri.toString(),
				Toast.LENGTH_LONG);
		t.show();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
															// name

		// start the image capture Intent
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	private void uploadFiles(String group_name, File[] toBeSent) {
		Intent intent = new Intent(this, Uploading.class);
		String[] fileNames = new String[count];
		for (int i = 0; i < count; i++) {
			fileNames[i] = toBeSent[i].getAbsolutePath();
		}
		intent.putExtra("toBeSent", fileNames);
		intent.putExtra("groupName", group_name);
		intent.putExtra("port", port);
		intent.putExtra("address", host);
		intent.putExtra("count", count);
		Toast t1 = Toast.makeText(getApplicationContext(), "Uploading",
				Toast.LENGTH_LONG);
		t1.show();
		startActivity(intent);
	}

}
