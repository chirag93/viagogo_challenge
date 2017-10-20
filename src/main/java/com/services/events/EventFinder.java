package com.services.events;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.services.models.Event;

public class EventFinder {
	
	private static Double getSmallestTicket(List<Double> tickets) {
		Double smallestTicket = 0.0;
		if(!tickets.isEmpty()) {
			smallestTicket = Collections.min(tickets);
		}
		return smallestTicket;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter Coordinates: ");
		reader.useDelimiter("\\D");
		double x = reader.nextDouble();
		double y = reader.nextDouble();
		reader.close(); 
        DecimalFormat df = new DecimalFormat("#.##");
		WorldDataGenerator world = new WorldDataGenerator();
		List<Event> events = world.getMinimumDistEvents(x, y);
		
		events.forEach(event->{
			
			System.out.println("Event "+event.getEventNumber()+" - "+"$"+df.format(getSmallestTicket(event.getTickets()))+" , Distance "+df.format(event.getDistance()));
		});
	}
}
