package com.services.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import com.services.models.Event;
import com.services.models.Location;



public class WorldDataGenerator {
	
	public final static int MAX_EVENTS = 10;
	public final static int MIN_EVENTS =0;
	public final static int MAX_NO_TICKETS =50;
	public final static int MIN_NO_TICKETS =0;
	public final static double MAX_TICKET_PRICE = 1000;
	public final static double MIN_TICKET_PRICE = 50;
	public final static double MIN_X_RANGE = -10;
	public final static double MAX_X_RANGE = 10;
	public final static double MAX_Y_RANGE = 10;
	public final static double MIN_Y_RANGE = -10;
	public final static int MAX_LOCATIONS = 441;

	private static int getEventNumber(int minEvents,int maxEvents) {
		int[] numberRange = IntStream.range(0, MAX_LOCATIONS+1).toArray();
		int randomIndex = new Random().nextInt(numberRange.length);
		 return numberRange[randomIndex];
		
	}
	
	private static List<Double> generateEventTickets(){
		List<Double> tickets = new ArrayList<Double>();
		Random  random = new Random();
		int ticketsPerEvent = random.nextInt(MAX_NO_TICKETS -MIN_NO_TICKETS+ 1) + MIN_NO_TICKETS;
		IntStream.range(0, ticketsPerEvent).forEach(i->{
			double price=MIN_TICKET_PRICE + Math.random() * (MAX_TICKET_PRICE - MIN_TICKET_PRICE);
			tickets.add(i,price);
		});
		
		return tickets;
	}
	
	private static Event generateEvent() {
		Event event = new Event();
		event.setEventNumber(getEventNumber(MIN_EVENTS,MAX_EVENTS));
		event.setTickets(generateEventTickets());
		return event;
	}
	
	public static Map<Location,Event> generateSeedData() {
		
		Map<Location,Event> worldData = new HashMap<>();
		IntStream.range(0, MAX_LOCATIONS+1).forEach(i->{
			Location loc = new Location();
			double randomx = MIN_X_RANGE + Math.random() * (MAX_X_RANGE - MIN_X_RANGE);
			double randomy = MIN_Y_RANGE + Math.random() * (MAX_Y_RANGE - MIN_Y_RANGE);
			loc.setX_cordinate(randomx);
			loc.setY_cordinate(randomy);
			worldData.put(loc, generateEvent());
		});
		return worldData;
	}
	
	private static Map<Location,Event> populateDistances(double x,double y){
		Map<Location,Event> worldData = generateSeedData();
		worldData.forEach((location,event)->{
			double distance = getDistance(x, location.getX_cordinate(), y, location.getY_cordinate());
			event.setDistance(distance);
		});
		return worldData;
	}
	
	private static void getMinimumDistEvents(double x,double y) {
		Map<Location,Event> worldData = populateDistances(x, y);
		List<Event> minimDistEvents = new ArrayList<>();
		
		worldData.forEach((location,event)->{
			System.out.println("Value of the world data events ====="+event.getDistance() + " and event Id =="+event.getEventNumber());
			minimDistEvents.add(event);
			
		});
		
		minimDistEvents.sort((e1, e2)->e1.getDistance().compareTo(e2.getDistance()));
		minimDistEvents.stream().limit(5).forEach(eve->{
			System.out.println(eve.getDistance()+ " and event Id :  "+eve.getEventNumber() + "  and prices ="+Collections.min(eve.getTickets()));
		});

		
	}
	
	private static double getDistance(double x1,double x0, double y1, double y0) {
		return Math.abs(x1-x0) + Math.abs(y1-y0);
	}
	
	

	public static void main(String[] args) throws IOException {
		double x  = 19.5; double y = 3.5;
		getMinimumDistEvents(x, y);
		
	}
}



