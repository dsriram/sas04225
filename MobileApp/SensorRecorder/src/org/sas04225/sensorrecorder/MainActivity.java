package org.sas04225.sensorrecorder;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String filename, record_dir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		File root = Environment.getExternalStorageDirectory();
		File record_dir = new File(root.getAbsolutePath() + "/SensorRecordings");
		if (!record_dir.exists())
			;
		{
			record_dir.mkdir();
		}
		this.record_dir = record_dir.getAbsolutePath();
		filename = "recording";

		SensorManager sManager = (SensorManager) this
				.getSystemService(SENSOR_SERVICE);
		List<Sensor> sList = sManager.getSensorList(Sensor.TYPE_ALL);
		boolean accel, orientation, magnetic;
		accel = orientation = magnetic = false;

		for (Sensor s : sList) {
			if (s.getType() == Sensor.TYPE_ACCELEROMETER)
				accel = true;
//			if (s.getType() == Sensor.TYPE_GYROSCOPE)
//				gyro = true;
			if (s.getType() == Sensor.TYPE_MAGNETIC_FIELD)
				magnetic = true;
		}
		orientation = accel||magnetic;
		CheckBox acs = (CheckBox) this.findViewById(R.id.SelectAccelerometer);
		CheckBox orient = (CheckBox) this.findViewById(R.id.SelectOrientation);
		CheckBox mag = (CheckBox) this.findViewById(R.id.SelectCompass);
		acs.setEnabled(accel);
		orient.setEnabled(orientation);
		mag.setEnabled(magnetic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void readyToRecord(View v) {
		CheckBox acs = (CheckBox) this.findViewById(R.id.SelectAccelerometer);
		CheckBox orientation_chkbox = (CheckBox) this.findViewById(R.id.SelectOrientation);
		CheckBox mag = (CheckBox) this.findViewById(R.id.SelectCompass);
		EditText fname = (EditText) this.findViewById(R.id.editText1);
		boolean accel = acs.isChecked();
		boolean orientaion = orientation_chkbox.isChecked();
		boolean magnetic = mag.isChecked();

		if (!(accel || orientaion || magnetic)) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Select a sensor", Toast.LENGTH_SHORT);
			toast.show();
			return;
		}

		if (fname.getText().toString().length() < 1) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Enter file name", Toast.LENGTH_SHORT);
			toast.show();
			return;
		}
		boolean[] sensor_recorded = new boolean[3];
		sensor_recorded[0] = accel;
		sensor_recorded[1] = orientaion;
		sensor_recorded[2] = magnetic;

		filename = record_dir + "/" + fname.getText().toString();
		Intent intnt = new Intent(this, Recording.class);
		intnt.putExtra("sensors", sensor_recorded);
		intnt.putExtra("filename", filename);
		Toast toast = Toast.makeText(getApplicationContext(), "Recording...",
				Toast.LENGTH_SHORT);
		toast.show();
		this.startActivity(intnt);
	}

}
