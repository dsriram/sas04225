package org.sas04225.imagerepoclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
		if (!intent.getBooleanExtra("offline", false)) {
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
						uploadFiles(group_name,toBeSent,group[i]);
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
				uploadFiles(this.group_name, this.toBeSent, this.toBeSent[0].getParentFile());
			}

		} else {
			captureImage(current_count);
		}
	}

	private void captureImage(int index) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		Uri fileUri = Uri.fromFile(toBeSent[index]);
		Toast t = Toast.makeText(getApplicationContext(), fileUri.toString(),
				Toast.LENGTH_SHORT);
		t.show();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
															// name

		// start the image capture Intent
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	private void uploadFiles(String group_name, File[] toBeSent, File group_dir) {
		/*Intent intent = new Intent(this, Uploading.class);*/
		String[] fileNames = new String[count];
		Log.i(MainActivity.class.getCanonicalName(),"Uploading files:");
		for (int i = 0; i < count; i++) {
			fileNames[i] = toBeSent[i].getAbsolutePath();
			Log.i(MainActivity.class.getCanonicalName(),fileNames[i]);
		}
		try {
			ImageRepoClient client = new ImageRepoClient(toBeSent,group_dir,group_name,".jpg",InetAddress.getByName(host),port);
			client.execute();
		} catch (UnknownHostException e) {
			Log.e(MainActivity.class.getCanonicalName(),"Unable to create AsyncTask ImageRepoClient",e);
		}
		
		/*intent.putExtra("toBeSent", fileNames);
		intent.putExtra("groupName", group_name);
		intent.putExtra("group_dir", group_dir.getAbsolutePath());
		intent.putExtra("port", port);
		intent.putExtra("address", host);
		intent.putExtra("count", count);*/
		Toast t1 = Toast.makeText(getApplicationContext(), "Uploading",
				Toast.LENGTH_SHORT);
		t1.show();
		/*startActivity(intent);*/
	}
	
	private class ImageRepoClient extends AsyncTask<Void,Void,Boolean> {

		private File[] file;
		private File group_dir;
		private InetAddress host;
		private Socket sock;
		private SocketChannel sockChannel;
		private int port;
		private String name, ext;

		private long[] file_length;
		private int file_count;
		private ByteBuffer header;
		private ByteBuffer file_info;

		public ImageRepoClient(File[] filesToBeSent, File group_dir, String name, String ext,
				InetAddress host, int port) {
			for (File f : filesToBeSent) {
				if (!(f.canRead() || f.isFile())) {
					throw new java.lang.IllegalArgumentException(
							"Illegal file argument : " + f.getAbsolutePath());
				}
			}
			file = filesToBeSent;
			this.group_dir = group_dir; 
			this.host = host;
			this.port = port;
			this.name = name;
			this.ext = ext;
		}

		private void init() throws IOException {
			file_count = file.length;
			file_length = new long[file_count];
			file_info = ByteBuffer.allocate(12);
			header = ByteBuffer.allocate(8);
			header.putInt(0xCAFECAFE);
			header.putInt(file_count);
			header.rewind();
			for (int i = 0; i < file_count; i++) {
				file_length[i] = file[i].length();
			}
			sockChannel = SocketChannel.open(new java.net.InetSocketAddress(host,
					port));
			sockChannel.finishConnect();
			sock = sockChannel.socket();
			System.out.println("Client: Connected");
		}

		private void verifyConnect() throws IOException {
			DataInputStream in = new DataInputStream(sock.getInputStream());
			if (in.readInt() != 0xCAFECAFE) {
				throw new IOException("Illegal RepoServer header recieved");
			}
		}

		private void writeHeader() throws IOException {
			sockChannel.write(header);
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			out.writeUTF(name);
			out.writeUTF(ext);
		}

		private void writeFile(int index) throws IOException {
			file_info.putInt(index);
			file_info.putLong(file_length[index]);
			file_info.rewind();
			FileInputStream fileInputStream = new FileInputStream(file[index]);
			FileChannel ch = fileInputStream.getChannel();
			sockChannel.write(file_info);
			ch.transferTo(0, file_length[index], sockChannel);
			ch.close();
			fileInputStream.close();
			file_info.clear();
		}


		@Override
		protected Boolean doInBackground(Void... params) {
			Boolean b = null;
			try {
				init();
				verifyConnect();
				writeHeader();
				for (int i = 0; i < file_count; i++) {
					writeFile(i);
					file[i].delete();
				}
				b = Boolean.TRUE;
			} catch (IOException ex) {
				Logger.getLogger(
						ImageRepoClient.class.getName() + " : "
								+ Thread.currentThread().getName()).log(
						Level.SEVERE, "Error when sending the files", ex);
				b = Boolean.FALSE;
			}
			return b;
			
		}

		@Override
		protected void onPostExecute(Boolean result) {
			String msg;
			if(result.booleanValue())
			{
				msg = "Uploaded";
			}
			else
			{
				msg = "Upload failed";
			}
			Toast t = Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT);
			t.show();
			for (int i = 0; i < file.length; i++) {
				file[i].delete();
			}
			this.group_dir.delete();
		}

	}
	

}

