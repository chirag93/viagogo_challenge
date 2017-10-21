package com.services.events;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.services.models.Event;
/**
 * This class is responsible for taking user inputs and displaying all the details of the available event
 * @author Chirag Basavaraj
 */
public class EventFinder {
	
	private static Double getSmallestTicket(List<Double> tickets) {
		Double smallestTicket = 0.0;
		if(!tickets.isEmpty()) {
			smallestTicket = Collections.min(tickets);
		}
		return smallestTicket;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in); 
		System.out.println("Enter Coordinates (x,y): ");
		reader.useDelimiter("\\D");

		double x=0.0;
		double y = 0.0;
		try {
		 x = reader.nextDouble();
		 y = reader.nextDouble();
		}catch(InputMismatchException e) {
			System.out.println("Please enter the valid Coordinates and try Again!");
			System.exit(1);
		}
		reader.close(); 
        DecimalFormat df = new DecimalFormat("#.##");
		WorldDataGenerator world = new WorldDataGenerator();
		List<Event> events = world.getMinimumDistEvents(x, y);
		
		events.forEach(event->{
			System.out.println("Event "+event.getEventNumber()+" - "+"$"+df.format(getSmallestTicket(event.getTickets()))+" , Distance "+df.format(event.getDistance()));
		});
	}
}
