package org.sas04225.wificonnection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.sas04225.proto.WifiScanResultProto;
import org.sas04225.proto.WifiScanResultProto.AccessPoint;
import org.sas04225.proto.WifiScanResultProto.WifiScanResult;
import org.sas04225.proto.WifiScanResultProto.WifiScanResult.Builder;

public class WifiRecord implements RadioMapStorage {

	private String tag;
	private String filePath;
	boolean recording=true;
	OutputStream out;

	public WifiRecord(String filePath ){

		this.filePath = filePath;
		this.tag= "WIFI RECORDING:";
		this.recording = false;
	}

	public void init() throws IOException {
		FileOutputStream fostream = new FileOutputStream(filePath);
		out = fostream;
	}
	
	public boolean addLocation(String location_tag, String[] bssid, int[] level)
	{
		Builder scanresult = WifiScanResultProto.WifiScanResult.newBuilder();
		scanresult.setLocationTag(location_tag);
		for(int i = 0; i< bssid.length ; i++) {
			AccessPoint ap = AccessPoint.newBuilder().setBssid(bssid[i]).setLevel(level[i]).build();
			scanresult.addResult(ap);
		}
		WifiScanResult result = scanresult.build();
		
		try{
			result.writeTo(out);
		}catch(IOException e)
		{
			android.util.Log.e(tag, "Unable to add location \'location_tag\'", e);
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


