package org.sas04225.cameraapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.google.protobuf.ByteString;
import com.google.protobuf.ByteString.Output;


public class MainActivity extends Activity implements SurfaceHolder.Callback, Camera.PreviewCallback{
	Camera camera;
	SurfaceView surfaceview;
	SurfaceHolder surfaceholder;
	boolean preview = false;
	int previewFormat;
	private Timer t0;
	private volatile boolean grabFrame;
	TimerTask t0_task;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonStartCameraPreview = (Button)findViewById(R.id.button1);
	       Button buttonStopCameraPreview = (Button)findViewById(R.id.button2);
	       grabFrame = false;
	       t0 = new Timer("FrameGrab");
	       t0_task = new TimerTask(){

			@Override
			public void run() {
				grabFrame = true;
				
			}};
	       surfaceview = (SurfaceView)findViewById(R.id.surfaceview);
	       surfaceholder = surfaceview.getHolder();
	       surfaceholder.addCallback(this);
	       surfaceholder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	      buttonStartCameraPreview.setOnClickListener(new Button.OnClickListener(){
	    	  

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!preview){
					camera= Camera.open();
					int rotation = 90; //90 degrees clockwise
					camera.setDisplayOrientation(rotation);
					if (camera != null){
						try{
							camera.setPreviewDisplay(surfaceholder);
						       camera.startPreview();
						       previewFormat = camera.getParameters().getPreviewFormat();
						       preview = true;
						       t0.scheduleAtFixedRate(t0_task, 1000, 1000);
						}catch (IOException e){
							e.printStackTrace();
							
						}
					}
				}
			}
	    	  
	      });
	      buttonStopCameraPreview.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(camera != null && preview){
				     camera.stopPreview();
				     camera.release();
				     camera = null;
				     preview=false;
				     t0.cancel();
				     t0.purge();
			}
	    	  
	      }});
	}

	
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main);
	//	return true;
	//}

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
		if(grabFrame) {
		Size s = camera.getParameters().getPreviewSize();
	    if (previewFormat == ImageFormat.NV21)
	    {
	        Rect rect = new Rect(0, 0, s.width, s.height); 
	        YuvImage img = new YuvImage(data, ImageFormat.NV21, s.width, s.height, null);
	        try 
	        {
	        	Output b_output = ByteString.newOutput();
	            img.compressToJpeg(rect, 95, b_output);
	            b_output.flush();
	            b_output.close();
	        }catch (IOException e) 
	        {
	            e.printStackTrace();
	        }finally{
	        	grabFrame = false;
	        }
	    }
		}
		
	}

}
