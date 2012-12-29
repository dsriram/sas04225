package org.sas04225.sensorrecorder;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SensorRecorder {
	private final String tag;
	private DataOutputStream out;
	private boolean recording;

	public SensorRecorder(FileOutputStream fostream, int buf_size, String tag) {
		out = new DataOutputStream(new BufferedOutputStream(fostream, buf_size));
		this.tag = "SensorRecorder: " + tag;
		recording = true;
	}

	public void putSample(float[] samples,long timestamp) {
		if (recording) {
			try {
				for (int i = 0; i < samples.length; i++) {
					out.writeFloat(samples[i]);
				}
			} catch (IOException e) {
				android.util.Log.e(tag, "Unable to write to the file", e);
			}
		}
	}

	public void putSample3(float[] samples,long timestamp) {
		if (recording) {
			try {
				out.writeLong(timestamp);
				out.writeFloat(samples[0]);
				out.writeFloat(samples[1]);
				out.writeFloat(samples[2]);
			} catch (IOException e) {
				android.util.Log.e(tag, "Unable to write to the file", e);
			}
		}
	}

	public void close() {
		try {
			recording = false;
			out.flush();
			out.close();
		} catch (IOException e) {
			android.util.Log.e(tag,
					"Unable to flush buffer to the file when closing", e);
		}
	}
}
