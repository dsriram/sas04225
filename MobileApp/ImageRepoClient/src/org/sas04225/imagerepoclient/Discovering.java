package org.sas04225.imagerepoclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Discovering extends Activity {

	public final String SERVICE_TYPE = "_imageRepoServer._raw._tcp.local";
	private BroadcastReceiver brx;
	private TextView ip_status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discovering);
		ip_status = (TextView) findViewById(R.id.textView2);
		android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) getSystemService(android.content.Context.WIFI_SERVICE);
		if ((wifi.isWifiEnabled()) && (wifi.getConnectionInfo() != null)
				&& (wifi.getConnectionInfo().getIpAddress() != 0)) {
			ip_status.setText("Your IP address: "
					+ getIpAddr(wifi.getConnectionInfo().getIpAddress()));
			brx = new BroadcastReceiver(SERVICE_TYPE, wifi);
			brx.execute();
		} else {
			ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
			progressBar1.setVisibility(View.INVISIBLE);
			TextView textView1 = (TextView) findViewById(R.id.textView1);
			textView1.setText("Wifi not enabled");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_discovering, menu);
		return true;
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	private class BroadcastReceiver extends AsyncTask<Void, Void, ServiceInfo> {

		public final long TIMEOUT = 60000;

		private MulticastLock lock;
		private JmDNS jmDNS;
		private String serviceType;
		public ServiceInfo info;
		Logger logger;
		android.net.wifi.WifiManager wifi;

		public BroadcastReceiver(String type, android.net.wifi.WifiManager wifi) {
			serviceType = type;
			this.wifi = wifi;
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
				logger = Logger.getLogger("BroadcastReceiver");
				logger.log(Level.INFO, "Bind address: " + addr.getHostAddress());
				// jmDNS.addServiceListener(serviceType, this);
				logger.log(Level.INFO, "Listener added");
				logger.log(Level.INFO, "Searching");
				info = jmDNS.getServiceInfo(serviceType, "ImageRepoServer",
						TIMEOUT / 2);
				if (info == null)
					info = jmDNS.getServiceInfo(serviceType, "ImageRepoServer",
							TIMEOUT / 2);
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
			super.onPostExecute(result);
			if (result == null) {
				ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
				progressBar1.setIndeterminate(false);
				TextView textView1 = (TextView) findViewById(R.id.textView1);
				if (jmDNS != null)
					textView1.setText("Unable to find the server :(");
				else {
					textView1.setText("Wifi not connected");
				}
			} else {
				Toast t = Toast.makeText(
						getApplicationContext(),
						"Server found @ "
								+ brx.info.getInet4Addresses()[0]
										.getHostAddress() + " "
								+ brx.info.getPort(), Toast.LENGTH_LONG);
				t.show();
				Context ctx = getApplicationContext();
				Intent intent = new Intent(ctx, MainActivity.class);
				intent.putExtra("serverIP",
						brx.info.getInet4Addresses()[0].getHostAddress());
				intent.putExtra("serverPort", brx.info.getPort());
				startActivity(intent);
			}

		}

		public InetAddress getFirstNonLocalAddress() throws SocketException {
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
			return addr;
		}

	}

	public String getIpAddr(int ip) {
		String ipString = String.format("%d.%d.%d.%d", (ip & 0xff),
				(ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
		return ipString;
	}
}
