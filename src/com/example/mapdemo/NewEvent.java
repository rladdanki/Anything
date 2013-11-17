package com.example.mapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.Marker;

public class NewEvent extends Activity implements OnMarkerClickListener,
		OnInfoWindowClickListener, OnMarkerDragListener,
		OnSeekBarChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newevent);

		Button map = (Button) findViewById(R.id.btnLocation);
		Button submit = (Button) findViewById(R.id.btnSubmit);

		
		
		EditText etname = (EditText) findViewById(R.id.etName);
		EditText etorg = (EditText) findViewById(R.id.etOrg);
		EditText ettime = (EditText) findViewById(R.id.etTime);
		EditText etdate = (EditText) findViewById(R.id.etDate);
		EditText etfee = (EditText) findViewById(R.id.etFee);
		EditText etlocation = (EditText) findViewById(R.id.etLocation);
		
		String name = etname.getText().toString();
		String org = etorg.getText().toString();
		String time = ettime.getText().toString();
		String date = etdate.getText().toString();
		String fee = etfee.getText().toString();
		String location = etlocation.getText().toString();
		
		
//		if(etorg.getText().toString() == "" || etorg.getText() == null){
//			Bundle q = getIntent().getExtras();
//			etorg.setText("helloooo");
//		}

		View.OnClickListener btnHit = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (v.getId() == R.id.btnLocation) {
					Intent loc = new Intent(v.getContext(),
							EventsDemoActivity.class);
					startActivity(loc);
					finish();
				}
				if (v.getId() == R.id.btnSubmit) {
					
					EditText etname = (EditText) findViewById(R.id.etName);
					EditText etorg = (EditText) findViewById(R.id.etOrg);
					EditText ettime = (EditText) findViewById(R.id.etTime);
					EditText etdate = (EditText) findViewById(R.id.etDate);
					EditText etfee = (EditText) findViewById(R.id.etFee);
					EditText etlocation = (EditText) findViewById(R.id.etLocation);
					
					String name = etname.getText().toString();
					String org = etorg.getText().toString();
					String time = ettime.getText().toString();
					String date = etdate.getText().toString();
					String fee = etfee.getText().toString();
					String location = etlocation.getText().toString();
					
					Bundle q = getIntent().getExtras();
					double longitude = q.getDouble("long");
					double latitude = q.getDouble("lat");
					
					Bundle b = new Bundle();
					
					b.putString("name", name);
					b.putString("org", org);
					b.putString("time", time);
					b.putString("date", date);
					b.putString("fee", fee);
					//b.putString("desc", description);
					b.putString("loc", location);
					b.putDouble("lat", latitude);
					b.putDouble("long", longitude);
					
					Intent sub = new Intent(v.getContext(),
							BasicMapDemoActivity.class);
					sub.putExtras(b);
					startActivity(sub);
					finish();
				}

			}
		};

		map.setOnClickListener(btnHit);
		submit.setOnClickListener(btnHit);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDrag(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}
}
