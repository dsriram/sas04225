package org.sas04225.mycamera;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Main_camera extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_camera);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_camera, menu);
		return true;
	}

}
