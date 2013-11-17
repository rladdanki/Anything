package com.example.mapdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

public class Menu extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		Button createEvent = (Button) findViewById(R.id.createEvent);
		Button viewEvent = (Button) findViewById(R.id.viewAllEvents);

		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(v.getId() == R.id.createEvent){
					Intent home = new Intent(v.getContext(), NewEvent.class);
					startActivity(home);
					finish();
				}
				if(v.getId() == R.id.viewAllEvents){
					Intent home = new Intent(v.getContext(), BasicMapDemoActivity.class);
					startActivity(home);
					finish();
				}
			}
		};
		
		createEvent.setOnClickListener(listener);
		viewEvent.setOnClickListener(listener);
		
	}

}
