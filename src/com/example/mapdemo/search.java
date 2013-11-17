package com.example.mapdemo;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class search extends FragmentActivity {

	private GoogleMap mMap;

	ArrayList<Event> list = BasicMapDemoActivity.eventlist;
	ArrayList<Event> newList = new ArrayList<Event>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		newList.clear();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		// setUpMapIfNeeded();
		
		Button searchHere = (Button) findViewById(R.id.btnsSubmit);

		EditText etname = (EditText) findViewById(R.id.etsName);
		EditText etorg = (EditText) findViewById(R.id.etsOrg);
		EditText ettime = (EditText) findViewById(R.id.etsTime);
		EditText etdate = (EditText) findViewById(R.id.etsDate);
		EditText etfee = (EditText) findViewById(R.id.etsFee);
		EditText etlocation = (EditText) findViewById(R.id.etsLocation);

		String name = etname.getText().toString();
		String org = etorg.getText().toString();
		String time = ettime.getText().toString();
		String date = etdate.getText().toString();
		String fee = etfee.getText().toString();
		String location = etlocation.getText().toString();
		
		for (int i = 0; i < list.size(); i ++) {
			if (name != null || (name.length() != 0)) {
				if(list.get(i).eventName.toLowerCase().toString().startsWith("f"))
//				if(name.matches(".*" + list.get(i).eventName.toString() + ".*"))
				newList.add(list.get(i));
			}
		}

		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.searchBar) {
					Intent search = new Intent(v.getContext(), search.class);
					startActivity(search);
					finish();
				}
				
				if (v.getId() == R.id.createBar) {
					Intent c = new Intent(v.getContext(), NewEvent.class);
					startActivity(c);
					finish();
				}
				
				if (v.getId() == R.id.mapBar) {
					Intent b = new Intent(v.getContext(), BasicMapDemoActivity.class);
					startActivity(b);
					finish();
				}

				if (v.getId() == R.id.btnsSubmit) {
					setContentView(R.layout.basic_demo);
					setUpMapIfNeeded();
					for (int i = 0; i < newList.size(); i++) {
						Marker myMarks;
						myMarks = mMap
								.addMarker(new MarkerOptions()
										.visible(true)
										.title(newList.get(i).eventName)
										.snippet(
												newList.get(i).eventDescription
														+ "\n"
														+ newList.get(i).eventOrginization
														+ "\n"
														+ newList.get(i).eventDate)
										.position(
												new LatLng(
														newList.get(i).eventLocation.location
																.getLatitude(),
														newList.get(i).eventLocation.location
																.getLongitude()))
										.draggable(false)
										.icon(BitmapDescriptorFactory
												.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
					}

					Context context = getApplicationContext();
					CharSequence text = "Event Added";
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			}
		};

		searchHere.setOnClickListener(listener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}

		}
		Bundle b = getIntent().getExtras();
		if (b != null) {
			double longitude = b.getDouble("long");
			double latitude = b.getDouble("lat");
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(CameraPosition.fromLatLngZoom(
							new LatLng(latitude, longitude), 10)));
		}
	}

	/**
	 * This is where we can add markers or lines, add listeners or move the
	 * camera. In this case, we just add a marker near Africa.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap}
	 * is not null.
	 */
	private void setUpMap() {
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(
				"Marker"));
	}

}
