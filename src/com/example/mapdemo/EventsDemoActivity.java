/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mapdemo;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This shows how to listen to some {@link GoogleMap} events.
 */
public class EventsDemoActivity extends FragmentActivity implements
		OnMapClickListener, OnMapLongClickListener, OnCameraChangeListener {
	
	private static final LatLng DEFAULT_MARKER_POSITION = new LatLng(60, 50);
	private static final String OTHER_OPTIONS = "options";
	private static final String MARKER_POSITION = "markerPosition";
	private static final String MARKER_INFO = "markerInfo";
	private double latitude;
	private double longitude;

	private GoogleMap mMap;
	private TextView mTapTextView;
	private TextView mCameraTextView;
	private Marker myMark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_demo);

		Button finalBtn = (Button) findViewById(R.id.button1);

		// mTapTextView = (TextView) findViewById(R.id.tap_text);
		// mCameraTextView = (TextView) findViewById(R.id.camera_text);

		setUpMapIfNeeded();
		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (myMark == null) {
					Context context = getApplicationContext();
					CharSequence text = "Please Select a Location!";
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				} else if (v.getId() == R.id.button1) {
					Intent home = new Intent(v.getContext(), NewEvent.class);
					Bundle b = getIntent().getExtras();
					startActivity(home);
					finish();
//					
					if (b != null) {
						b.putDouble("lat", latitude);
						b.putDouble("long", longitude);
						home.putExtras(b);
						startActivity(home);
						finish();
					} else {
						Bundle p = new Bundle();
						p.putDouble("lat", latitude);
						p.putDouble("long", longitude);
						home.putExtras(p);
						startActivity(home);
						finish();
					}
				}

			}
		};

		finalBtn.setOnClickListener(listener);

	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {
		mMap.setOnMapClickListener(this);
		mMap.setOnMapLongClickListener(this);
		mMap.setOnCameraChangeListener(this);
	}

	@Override
	public void onMapClick(LatLng point) {
		// mTapTextView.setText("tapped, point=" + point);

	}

	@Override
	public void onMapLongClick(LatLng point) {
		// mTapTextView.setText("long pressed, point=" + point);
		if (myMark == null) {
			myMark = mMap.addMarker(new MarkerOptions()
					.visible(true)
					.position(new LatLng(point.latitude, point.longitude))
					.draggable(true)
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			latitude = point.latitude;
			longitude = point.longitude;
			Context context = getApplicationContext();
			CharSequence text = "Is this Your Final Location?";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		} else {
			myMark.setPosition(point);
			Context context = getApplicationContext();
			CharSequence text = "Is this Your Final Location?";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			latitude = point.latitude;
			longitude = point.longitude;
		}

	}

	@Override
	public void onCameraChange(final CameraPosition position) {
		// mCameraTextView.setText(position.toString());
	}
}
