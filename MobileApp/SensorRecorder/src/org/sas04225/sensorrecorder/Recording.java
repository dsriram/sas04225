package org.sas04225.sensorrecorder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Recording extends Activity {

	private int[] sampling_rate;
	private int[] sample_count;
	private boolean recording_now = false;

	private boolean recording_accel, recording_orientation, recording_magnetic;
	private SensorManager sManager;
	private Sensor accelerometer, magneticSensor;
	private SensorEventListener accel, magnetic;
	private PowerManager.WakeLock wl;
	private SensorRecorder acc, magn, orientation;
	private float[] current_accel, current_magnetic, current_orientation,
			rotation_matrix;

	private Chronometer chrmtr;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recording);
		// Show the Up button in the action bar.
		// getActionBar().setDisplayHomeAsUpEnabled(true);

		recording_accel = recording_orientation = recording_magnetic = false;

		chrmtr = (Chronometer) findViewById(R.id.chronometer1);
		sManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		Intent start_intent = this.getIntent();

		boolean[] sensor_recorded = start_intent
				.getBooleanArrayExtra("sensors");
		recording_accel = sensor_recorded[0];
		recording_orientation = sensor_recorded[1];
		recording_magnetic = sensor_recorded[2];
		sample_count = new int[3];
		sample_count[0] = sample_count[1] = sample_count[2] = 0;
		sampling_rate = new int[3];

		String file_name = start_intent.getStringExtra("filename");

		if (recording_accel||recording_orientation) {
			accelerometer = sManager
					.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sampling_rate[0] = (int) (1.0e6 / (float) accelerometer
					.getMinDelay());
			current_accel = new float[3];
		}
		if (recording_orientation) {
			rotation_matrix = new float[9];
			current_orientation = new float[3];
			// gyroscope = sManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
			sampling_rate[1] = (int) (1.0e6 / (float) accelerometer	.getMinDelay());
		}
		if (recording_magnetic || recording_orientation) {
			magneticSensor = sManager
					.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
			sampling_rate[2] = (int) (1.0e6 / (float) magneticSensor
					.getMinDelay());
			current_magnetic = new float[3];
		}

		openFiles(file_name);

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");

		accel = new SensorEventListener() {

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				Log.w("Accuracy changed", "Accelerometer - new accuracy: "
						+ accuracy);

			}

			@Override
			public void onSensorChanged(SensorEvent event) {
				if (recording_accel) {
					acc.putSample3(event.values, event.timestamp);
					sample_count[0]++;
				}
				current_accel = event.values;
				if (recording_orientation) {
					SensorManager.getRotationMatrix(rotation_matrix, null,
							current_accel, current_magnetic);
					SensorManager.getOrientation(rotation_matrix,current_orientation);
					orientation.putSample3(current_orientation, event.timestamp);
					sample_count[1]++;
				}
			}

		};

		magnetic = new SensorEventListener() {

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				Log.w("Accuracy changed", "MagneticSensor - new accuracy: "
						+ accuracy);

			}

			@Override
			public void onSensorChanged(SensorEvent event) {
				if (recording_magnetic) {
					sample_count[2]++;
					magn.putSample3(event.values, event.timestamp);
				}
				current_magnetic = event.values;
			}

		};

	}

	@Override
	protected void onStart() {
		super.onStart();
		ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar1);
		progress.setIndeterminate(true);
		chrmtr.start();
		String text = "";
		if (recording_accel || recording_orientation) {
			Log.i("Started Recording", "Accelerometer @ " + sampling_rate[0]
					+ " samples/s");
			text += "Accelerometer @ " + sampling_rate[0] + " samples/s" + "\n";
			sManager.registerListener(accel, accelerometer,
					SensorManager.SENSOR_DELAY_FASTEST);
		}
		// if(recording_gyro)
		// {
		// Log.i("Started Recording","Gyroscope @ "+sampling_rate[1]+" samples/s");
		// text += "Gyroscope @ "+sampling_rate[1]+" samples/s"+"\n";
		// sManager.registerListener(gyro, gyroscope,
		// SensorManager.SENSOR_DELAY_FASTEST);
		// }
		if (recording_orientation) {
			Log.i("Started Recording", "Orientaion @ " + sampling_rate[1]
					+ " samples/s");
			text += "Orientation @ " + sampling_rate[0] + " samples/s" + "\n";
			// sManager.registerListener(accel, accelerometer,
			// SensorManager.SENSOR_DELAY_FASTEST);
		}
		if (recording_magnetic || recording_orientation) {
			Log.i("Started Recording", "MagneticField @ " + sampling_rate[2]
					+ " samples/s");
			text += "MagneticField @ " + sampling_rate[2] + " samples/s";
			sManager.registerListener(magnetic, magneticSensor,
					SensorManager.SENSOR_DELAY_FASTEST);
		}
		Toast toast = Toast.makeText(getApplicationContext(), text,
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		recording_now = true;
		wl.acquire();
	}

	@Override
	protected void onPause() {
		if (wl.isHeld())
			wl.release();
		unRegisterSensorEventListeners();
		closeFiles();
		super.onPause();
		this.finish();
	}

	@Override
	protected void onStop() {
		if (wl.isHeld())
			wl.release();
		unRegisterSensorEventListeners();
		closeFiles();
		Log.i("Sample count:", "Accel: " + sample_count[0] + " Orientation: "
				+ sample_count[1] + " Mag: " + sample_count[2]);
		CharSequence text = "ACCELOMETER READING:" + sample_count[0]
				+ "\n ORIENTATION READING:" + sample_count[1]
				+ "\nMAGNETIC FIELD:" + sample_count[2];
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(getApplicationContext(), text, duration);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
		super.onStop();
	}

	private void openFiles(String filename) {
		FileOutputStream accel, _orientation, magnetic;
		try {
			if (recording_accel) {
				accel = new FileOutputStream(filename + "_accel.dat");
				acc = new SensorRecorder(accel, 20 * sampling_rate[0] * 10,
						"Accel_Recorder");
			}
			if (recording_orientation) {
				_orientation = new FileOutputStream(filename
						+ "_orientation.dat");
				orientation = new SensorRecorder(_orientation,
						20 * sampling_rate[1] * 10, "Orientation_recorder");
			}
			if (recording_magnetic) {
				magnetic = new FileOutputStream(filename + "_magnetic.dat");
				magn = new SensorRecorder(magnetic, 20 * sampling_rate[2] * 10,
						"Magnetic_Recorder");
			}
		} catch (FileNotFoundException e) {
			android.util.Log.e("activity1_recording", "at openFiles", e);
			Toast err = Toast.makeText(this.getApplicationContext(),
					"Unable to start recording", Toast.LENGTH_LONG);
			err.show();
			this.finish();
		}

	}

	private void closeFiles() {
		if (recording_now) {
			if (recording_accel)
				acc.close();
			if (recording_orientation)
				orientation.close();
			if (recording_magnetic)
				magn.close();
			recording_now = false;
		}
	}

	private void unRegisterSensorEventListeners() {
		if (recording_accel || recording_orientation)
			sManager.unregisterListener(accel);
		if (recording_magnetic || recording_orientation)
			sManager.unregisterListener(magnetic);

		chrmtr.stop();
	}

	public void stopRecording(View v) {
		if (wl.isHeld())
			wl.release();
		unRegisterSensorEventListeners();
		closeFiles();
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_recording, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
