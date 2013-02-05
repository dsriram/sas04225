package org.sas04225.wificonnection;

import org.ServerRecord;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class WifiRecord implements RadioMapStorage {

	private String tag;
	private String filePath;
	boolean recording=true;
	DataOutputStream out;

	public WifiRecord(String filePath ){

		this.filePath = filePath;
		this.tag= "WIFI RECORDING:";
		this.recording = false;
	}

	public void init() throws IOException {
		FileOutputStream fostream = new FileOutputStream(filePath);
		final int buf_size = 0;
		out = new DataOutputStream(new BufferedOutputStream(fostream, buf_size));
	}
	
	public boolean addLocation(long location_id, String location_tag, String[] bssid, int[] level)
	{
		try{
			out.writeChars("#Start Record\n")
			out.writeChars(new String(location_id+" "+location_tag+"\n"));
			for(int i = 0; i< bssid.length ; i++) {
				out.writeChars(bssid[i]+" "+level[i]+"\n");
			}
			out.writeChars("#End Record\n");
		}catch(IOException e)
		{
			android.util.Log.e(tab, "Unable to add location \'location_tag\'", e);
			return false;
		}
		return true;

	}
	
	public void close() throws IOException
	{
		out.flush();
		out.close();
	}

}


