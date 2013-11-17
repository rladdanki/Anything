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
import java.util.Date;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 * <p>
 * Notice how we deal with the possibility that the Google Play services APK is not
 * installed/enabled/updated on a user's device.
 */
public class BasicMapDemoActivity extends FragmentActivity {
   
	public static ArrayList<Event> eventlist = new ArrayList<Event>();
   
    private GoogleMap mMap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_demo);
        setUpMapIfNeeded();
        
        Button createEvent = (Button) findViewById(R.id.createBar);
		Button viewMap = (Button) findViewById(R.id.mapBar);
		Button search = (Button) findViewById(R.id.searchBar);
		

		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.createBar:
					Intent create = new Intent(v.getContext(), NewEvent.class);
					startActivity(create);
					finish();
					break;
				case R.id.mapBar:
					Intent map = new Intent(v.getContext(), BasicMapDemoActivity.class);
					startActivity(map);
					finish();
					break;
				case R.id.searchBar:
					Intent search = new Intent(v.getContext(), search.class);
					startActivity(search);
					finish();
					break;
				}
				
			}
			
		};
		
		createEvent.setOnClickListener(listener);
		viewMap.setOnClickListener(listener);
		search.setOnClickListener(listener);
        
        Bundle b = getIntent().getExtras();
        if(b != null){
        double longitude = b.getDouble("long");
        double latitude = b.getDouble("lat");
        Date d = new Date(10, 1, 12, 1,1,1);
        Locations loc = new Locations(b.getString("location"), latitude, longitude);
        Event event1 = new Event(b.getString("name"), "HackDuke", d, Integer.parseInt(b.getString("fee")), "description", b.getString("org"), loc);
        eventlist.add(event1);
        }
        Locations loc1 = new Locations("Chapell", 38, -78);
        Locations loc2 = new Locations("Rice Hall", 37.5, -78.1);
        Locations loc3 = new Locations("Corner", 39, -79);
        
        Event event2 = new Event("Ektaal Performace", "Ektaal", new Date(2012, 11, 12), 5, "Come Out!", "Ektaal", loc1);
        Event event3 = new Event("TED Talk", "EStud", new Date(2013, 02, 14), 10, "Come listen to Bill Gates", "EStud", loc2);
        Event event4 = new Event("Free Bodos", "Bodos", new Date(2013, 11, 12), 0, "Eat Free Bodos!", "UPC", loc3);
        
        eventlist.add(event2);
        eventlist.add(event3);
        eventlist.add(event4);      
        
        
        for(int i = 0; i < eventlist.size(); i++){
         Marker myMarks;
         myMarks = mMap.addMarker(new MarkerOptions()
		.visible(true).title(eventlist.get(i).eventName).snippet(eventlist.get(i).eventDescription + "\n" + eventlist.get(i).eventOrginization + "\n" + eventlist.get(i).eventDate )
		.position(new LatLng(eventlist.get(i).eventLocation.location.getLatitude(), eventlist.get(i).eventLocation.location.getLongitude()))
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

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
            
        }
        Bundle b = getIntent().getExtras();
        if(b != null){
        double longitude = b.getDouble("long");
        double latitude = b.getDouble("lat");
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition
				.fromLatLngZoom(new LatLng(latitude, longitude), 10)));
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
