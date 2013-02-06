package org.sas04225.wificonnection;

import org.ServerRecord;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class WifiRecord implements ServerRecord {
	private String tag1;
	private String tag;

	public void RadioStorageMap(){
		boolean recording=true;
		DataOutputStream out;
			FileOutputStream fostream = null;
			try{
				File[] args = null;
				fostream = new FileOutputStream(args[0]);
			}catch(FileNotFoundException e){
				System.out.println("ERROR OPENING FILE");
				
			}
			final int buf_size = 0;
			out = new DataOutputStream(new BufferedOutputStream(fostream, buf_size));
			this.tag= "WIFI RECORDING:" + tag1;
			recording = true;
	}
		
		public void RadioStorageMap(String bssid,int dBm) {
			boolean recording=true;
			FileOutputStream fostream = null;
			DataOutputStream out;
			int buf_size = 0;
			if (recording) {
				out = new DataOutputStream(new BufferedOutputStream(fostream, buf_size));
				try {
					for (int i = 0; i <bssid.length() ; i++) {
						out.writeChars(bssid);
						out.writeInt(dBm);
					}
				} catch (IOException e) {
					android.util.Log.e(tag, "Unable to write to the file", e);
				}
			}
		}

	}


