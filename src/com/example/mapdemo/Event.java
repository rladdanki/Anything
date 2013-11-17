package com.example.mapdemo;
import android.location.Location;


import java.util.*;

public class Event {
	String eventName;
	String hostName; 
	String eventOrginization;
	String eventDescription;
	int eventPrice; 
	Date eventDate;
	Locations eventLocation;
	
	
	public Event(String name, String host, Date d, int price, String description, String org, Locations l)
	{
		eventName = name;
		hostName = host;
		eventDescription = description;
		eventDate = d;
		eventPrice = price; 
		eventOrginization = org;
		eventLocation = l;
	}
}
