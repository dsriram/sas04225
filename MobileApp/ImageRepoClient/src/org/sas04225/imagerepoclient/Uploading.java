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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class Uploading extends Activity {

	private String host;
	private String[] files;
	private String group_name;
	private int port,count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uploading);
		Intent intent = this.getIntent();
		host = intent.getStringExtra("address");
		port = intent.getIntExtra("port", 0);
		group_name = intent.getStringExtra("groupName");
		files = intent.getStringArrayExtra("toBeSent");
		count = intent.getIntExtra("count", 0);
		File[] toBeSent = new File[count];
		for(int i=0; i<count; i++)
		{
			toBeSent[i] = new File(files[i]);
		}
		try {
			ImageRepoClient client = new ImageRepoClient(toBeSent,group_name,".jpg",InetAddress.getByName(host),port);
			client.execute();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_updating, menu);
		return true;
	}
	
	public void onExit()
	{
		
	}
	
	private class ImageRepoClient extends AsyncTask<Void,Void,Boolean> {

		private File[] file;
		private InetAddress host;
		private Socket sock;
		private SocketChannel sockChannel;
		private int port;
		private String name, ext;

		private long[] file_length;
		private int file_count;
		private ByteBuffer header;
		private ByteBuffer file_info;

		public ImageRepoClient(File[] filesToBeSent, String name, String ext,
				InetAddress host, int port) {
			for (File f : filesToBeSent) {
				if (!(f.canRead() || f.isFile())) {
					throw new java.lang.IllegalArgumentException(
							"Illegal file argument : " + f.getAbsolutePath());
				}
			}
			file = filesToBeSent;
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
			FileChannel ch = (new FileInputStream(file[index])).getChannel();
			sockChannel.write(file_info);
			ch.transferTo(0, file_length[index], sockChannel);
			ch.close();
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
			Toast t = Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
			t.show();
			finish();
		}

	}
	

}
