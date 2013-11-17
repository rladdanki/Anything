package com.example.mapdemo;
import android.location.Location;

public class Locations
{
	Location location;
	String name;
	
	public Locations(String n, double lat, double lon)
	{
		name = n;
		location = new Location("String");
		location.setLongitude(lon);
		location.setLatitude(lat);
	
	}
	public Location getLocation()
	{
		return location;
	}
	public String getName()
	{
		return name;
	}

}
