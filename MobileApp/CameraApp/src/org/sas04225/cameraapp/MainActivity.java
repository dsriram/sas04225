package org.sas04225.cameraapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import org.sas04225.proto.RecognitionServerQueryProto.Query;
import org.sas04225.proto.RecognitionServerQueryProto.QueryDataType;
import org.sas04225.proto.RecognitionServerQueryProto.QueryType;
import org.sas04225.proto.RecognitionServerResultProto.Result;
import org.sas04225.proto.RecognitionServerResultProto.Tag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.protobuf.ByteString;
import com.google.protobuf.ByteString.Output;
import com.google.protobuf.Message;

public class MainActivity extends Activity implements SurfaceHolder.Callback,
		Camera.PreviewCallback {
	Camera camera;
	SurfaceView surfaceview;
	SurfaceHolder surfaceholder;
	boolean preview = false;
	private boolean serverFound = false;
	int previewFormat;
	private Timer t0;
	private volatile boolean grabFrame = false;
	TimerTask t0_task;
	private LinkedBlockingQueue<Query> queries;
	public static String mDNS_SERVICE_TYPE = "_imageLookupServer._raw._tcp.local";
	private InetSocketAddress serverAddr; 
	private RecognitionServerClient client;
	private String lastResult = "";
	private WakeLock wl;
	private volatile boolean prevRequestComplete = true;
	private MulticastLock lock;
	private String[] last5Results;
	private int curAvgWindowPos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonStartCameraPreview = (Button) findViewById(R.id.button1);
		Button buttonStopCameraPreview = (Button) findViewById(R.id.button2);
		grabFrame = false;
		last5Results = new String[]{"Unknown","Unknown","Unknown","Unknown","Unknown"};
		curAvgWindowPos = 0;
		TextView res = (TextView)findViewById(R.id.textView1);
		res.setTextColor(Color.WHITE);
		queries = new LinkedBlockingQueue<Query>();
		t0 = new Timer("FrameGrab");
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
		t0_task = new TimerTask() {

			@Override
			public void run() {
				grabFrame = true;
				Log.i("TimerTask", "Task triggered");
			}
		};
		
		android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) getSystemService(android.content.Context.WIFI_SERVICE);
		if ((wifi.isWifiEnabled()) && (wifi.getConnectionInfo() != null)
				&& (wifi.getConnectionInfo().getIpAddress() != 0)) {
			ServerDiscovery sDisc = new ServerDiscovery(wifi);
			sDisc.execute();
		}else {
			Toast t = Toast.makeText(this, "Wifi not enabled", Toast.LENGTH_SHORT);
			t.show();
		}
		
		surfaceview = (SurfaceView) findViewById(R.id.surfaceview);
		surfaceholder = surfaceview.getHolder();
		surfaceholder.addCallback(this);
		surfaceholder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		final Camera.PreviewCallback cb = this;
		camera = Camera.open();
		buttonStartCameraPreview
				.setOnClickListener(new Button.OnClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (!preview) {
							int rotation = 90; // 90 degrees clockwise
							camera.setDisplayOrientation(rotation);
							if (camera != null) {
								try {
									camera.setPreviewDisplay(surfaceholder);
									camera.startPreview();
									camera.setPreviewCallback(cb);
									previewFormat = camera.getParameters()
											.getPreviewFormat();
									Camera.Parameters params = camera.getParameters();
									params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
									params.setVideoStabilization(true);
									camera.setParameters(params);
									preview = true;
									wl.acquire();
									t0.scheduleAtFixedRate(new TimerTask() {

										@Override
										public void run() {
											grabFrame = true;
											Log.d("TimerTask", "Task triggered");
										}
									}, 0, 100);
								} catch (IOException e) {
									Logger.getLogger(
											MainActivity.class.getName()).log(
											Level.SEVERE, null, e);

								}
							}
						}
					}

				});
		buttonStopCameraPreview
				.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (camera != null && preview) {
							camera.stopPreview();
//							camera = null;
							preview = false;
							t0.cancel();
							t0.purge();
							t0 = new Timer("FrameGrab");
							wl.release();
						}

					}
				});
	}

	// public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_main);
	// return true;
	// }

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		if (grabFrame&&serverFound&&prevRequestComplete) {
			Size s = camera.getParameters().getPreviewSize();
			if (previewFormat == ImageFormat.NV21) {
				Rect rect = new Rect(0, 0, s.width, s.height);
				YuvImage img = new YuvImage(data, ImageFormat.NV21, s.width,
						s.height, null);
				try {
					Output b_output = ByteString.newOutput();
					img.compressToJpeg(rect, 95, b_output);
					b_output.flush();
					b_output.close();
					Query q = Query.newBuilder()
							.setLocationId(Integer.MAX_VALUE)
							.setQueryType(QueryType.GENERAL_QUERY)
							.setQueryDataType(QueryDataType.JPEG_IMAGE)
							.setRequestId(System.currentTimeMillis())
							.setQueryData(b_output.toByteString()).build();
					try {
						queries.put(q);
						Log.d("onPreviewFrame", "added to queue");
						prevRequestComplete = false;
						client = new RecognitionServerClient(serverAddr);
						client.execute(new Void[]{});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Logger.getLogger(MainActivity.class.getName()).log(
								Level.SEVERE, null, e);
					}
				} catch (IOException e) {
					Logger.getLogger(MainActivity.class.getName()).log(
							Level.SEVERE, null, e);
				} finally {
					/*if((client.getStatus() != AsyncTask.Status.RUNNING) || (client.getStatus() != AsyncTask.Status.PENDING))
					{
						client.execute(new Void[]{});
					}*/
					grabFrame = false;
				}
			}
		}

	}
	
	@Override
	protected void onStop() {
		if(wl.isHeld())
			wl.release();
		if(lock.isHeld())
			lock.release();
		camera.release();
		super.onStop();
	}

	private class RecognitionServerClient extends AsyncTask<Void, Void, Void> {

		private InetSocketAddress server;
		private Result r;

		public RecognitionServerClient(InetSocketAddress addr) {
			this.server = addr;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Query q = queries.take();
				doQuery(q);
			} catch (InterruptedException e) {
				Logger.getLogger(RecognitionServerClient.class.getName()).log(
						Level.SEVERE, null, e);
			} catch (IOException e) {
				Logger.getLogger(RecognitionServerClient.class.getName()).log(
						Level.SEVERE, null, e);
			}
			return null;
		}

		private void doQuery(Query q) throws IOException {
			Socket sock = null;
			try {
				sock = new Socket(server.getAddress(), server.getPort());
				MainActivity.writeMessage(sock.getOutputStream(), q);
				byte[] response = MainActivity.readMessageData(sock
						.getInputStream());
				r = Result.parseFrom(response);
				sock.close();
			} catch (SocketException ex) {
				Logger.getLogger(RecognitionServerClient.class.getName()).log(
						Level.SEVERE, null, ex);
			}
			
		}
		
		@Override
		protected void onPostExecute(Void result) {
			//TODO add some mechanism to dispaly the result
			
			
			Log.i("Result", "Query ID: "+r.getRequestId() );
			Log.i("Result", r.toString());
			ArrayList<String> tags = new ArrayList<String>();
			int index = 0,max=-1,maxIndex=-1;
			for(Tag tag : r.getTagsList()) {
				if(tag.getName() == "Unknown") {
					Log.i("Result", "Tag: Unknown");
					lastResult = "Unknown";
					break;
				}
				if(tag.getCount() >= 10){
					if(Math.max(max, tag.getCount()) != max) {
						max = Math.max(max, tag.getCount());
						maxIndex = index;
					}
					tags.add(tag.getName());
					index++;
				}
			}
			if(maxIndex != -1) {
				Log.i("Result", "Tag max: "+tags.get(maxIndex));
				lastResult = tags.get(maxIndex);
			}
			else{
				lastResult = "Unknown";
			}
			String avgdResult = doAverage(lastResult);
			TextView res = (TextView)findViewById(R.id.textView1);
			res.setText(avgdResult);
			prevRequestComplete = true;
			/*findViewById(R.id.surfaceview).postDelayed(new Runnable() {

				@Override
				public void run() {
					client = new RecognitionServerClient(serverAddr);
					client.execute(new Void[]{});					
				}}, 250);*/
		}
		
		private String doAverage(String currentTag) {
			if(curAvgWindowPos >4)
				curAvgWindowPos = 0;
			last5Results[curAvgWindowPos] = currentTag;
			curAvgWindowPos++;
			HashMap<String, java.util.concurrent.atomic.AtomicInteger> count = new HashMap<String, AtomicInteger>();
			for(String str : last5Results) {
				AtomicInteger intg = count.get(str);
				int val;
				if(intg == null)
					intg = new AtomicInteger(1);
				else
					val = intg.incrementAndGet();
				count.put(str, intg);
			}
			String maxTag = "Unknown";
//			int maxCount = 1;
			for(Map.Entry<String, AtomicInteger> entry : count.entrySet()) {
				String tag = entry.getKey();
				int val = entry.getValue().get();
				if(val>3) {
					maxTag = tag;
					break;
				}
			}
			return maxTag;
		}

	}

	private class ServerDiscovery extends AsyncTask<Void, Void, ServiceInfo> implements ServiceListener {

		public final long TIMEOUT = 60000;

		private JmDNS jmDNS;
		private String serviceType;
		public ServiceInfo info;
		Logger logger;
		android.net.wifi.WifiManager wifi;
		private  final Object lock1;

		public ServerDiscovery(android.net.wifi.WifiManager wifi) {
			serviceType = MainActivity.mDNS_SERVICE_TYPE;
			this.wifi = wifi;
			lock1 = new Object();
		}

		@Override
		protected ServiceInfo doInBackground(Void... params) {
			try {
				lock = wifi.createMulticastLock("mylockthereturn");
				lock.setReferenceCounted(true);
				lock.acquire();
				InetAddress addr = getFirstNonLocalAddress();
				jmDNS = null;
				if (addr == null) {
					return null;
				}

				jmDNS = JmDNS.create(addr);
				logger = Logger.getLogger(ServerDiscovery.class.getName());
				logger.log(Level.INFO, "Bind address: " + addr.getHostAddress());
				// jmDNS.addServiceListener(serviceType, this);
				logger.log(Level.INFO, "Listener added");
				logger.log(Level.INFO, "Searching");
				info = jmDNS.getServiceInfo(mDNS_SERVICE_TYPE, "ImageLookupServer",
						TIMEOUT / 2);
				Log.i("ServiceListerner", (info != null)?info.toString():"not found");
				if(info == null) {
				jmDNS.addServiceListener(mDNS_SERVICE_TYPE, this);
				synchronized(lock1) {
					try {
						lock1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
				/*if (info == null)
					info = jmDNS.getServiceInfo(serviceType, "ImageLookupServer",
							TIMEOUT / 2);*/
				/*if (info == null)
					info = jmDNS.getServiceInfo(serviceType, "ImageLookupServer",
							TIMEOUT / 3);*/
				logger.log(Level.INFO, "Search complete");
				// logger.log(Level.INFO, ""+info+" "+lock.isHeld());
				try {
					// jmDNS.removeServiceListener(serviceType, this);
					jmDNS.close();
					lock.release();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return info;
		}
		
		@Override
		protected void onPostExecute(ServiceInfo result) {
			if(info != null) {
				Toast t = Toast.makeText(getApplicationContext(), "Server found", Toast.LENGTH_SHORT);
				t.show();
				serverAddr = new InetSocketAddress(info.getInet4Addresses()[0].getHostAddress(), info.getPort());
				serverFound = true;
				client = new RecognitionServerClient(serverAddr);
				client.execute(new Void[]{});
			}
		}

		@Override
		public void serviceAdded(ServiceEvent arg0) {
			 Log.i("ServiceEventListener", "Service added " + arg0.getType());
			
		}

		@Override
		public void serviceRemoved(ServiceEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void serviceResolved(ServiceEvent arg0) {
			Log.i("ServiceEventListener","Service Resolved "+ arg0.getType());
			info = arg0.getInfo();
			synchronized(lock1) {
				lock1.notifyAll();
			}
			
		}

		
	}
	public static InetAddress getFirstNonLocalAddress() throws SocketException {
		InetAddress addr = null;
		Enumeration<NetworkInterface> interfaces = NetworkInterface
				.getNetworkInterfaces();
		for (NetworkInterface net_inf : Collections.list(interfaces)) {
			for (InetAddress address : Collections.list(net_inf
					.getInetAddresses())) {
				if (!(address.isLinkLocalAddress()
						|| address.isLoopbackAddress() || (address instanceof java.net.Inet6Address))) {
					addr = address;
				}
			}
		}
		if (addr == null) {
			try {
				addr = InetAddress.getByName("127.0.0.1");
			} catch (UnknownHostException ex) {
				Logger.getLogger(MainActivity.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
		return addr;
	}

	public static void writeMessage(OutputStream out, Message msg)
			throws IOException {
		byte[] data = msg.toByteArray();
		int len = data.length;

		out.write(len);
		out.write(len >> 8);
		out.write(len >> 16);
		out.write(len >> 24);

		out.write(data);

		out.flush();
	}

	public static byte readMessageData(InputStream in)[] throws IOException {
		int len = 0;

		len = in.read();
		len |= (in.read()) << 8;
		len |= (in.read()) << 16;
		len |= (in.read()) << 24;

		byte[] data = new byte[len];
		int bytes_read = 0;
		while (bytes_read != len) {
			bytes_read += in.read(data, bytes_read, len - bytes_read);
		}
		return data;
	}

}
