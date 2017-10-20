package com.services.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import com.services.eventfinder.utils.ConfigFileReaderUtil;
import com.services.models.Event;
import com.services.models.Location;



public class WorldDataGenerator {
	static ConfigFileReaderUtil config = new ConfigFileReaderUtil();
	public final static int MAX_EVENTS = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_EVENTS"));
	public final static int MIN_EVENTS = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MIN_EVENTS"));
	public final static int MAX_NO_TICKETS =Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_NO_TICKETS"));
	public final static int MIN_NO_TICKETS =Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MIN_NO_TICKETS"));
	public final static double MAX_TICKET_PRICE = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_TICKET_PRICE"));
	public final static double MIN_TICKET_PRICE = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MIN_TICKET_PRICE"));
	public final static double MIN_X_RANGE = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MIN_X_RANGE"));
	public final static double MAX_X_RANGE = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_X_RANGE"));;
	public final static double MAX_Y_RANGE = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_Y_RANGE"));;
	public final static double MIN_Y_RANGE = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MIN_Y_RANGE"));;
	public final static int MAX_LOCATIONS = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_LOCATIONS"));;

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
	
	public  Event generateEvent() {
		Event event = new Event();
		event.setEventNumber(getEventNumber(MIN_EVENTS,MAX_EVENTS));
		event.setTickets(generateEventTickets());
		return event;
	}
	
	public  Map<Location,Event> populateLocationPoints() {
		
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
	
	private  Map<Location,Event> populateDistances(double x,double y){
		Map<Location,Event> worldData = populateLocationPoints();
		worldData.forEach((location,event)->{
			double distance = getDistance(x, location.getX_cordinate(), y, location.getY_cordinate());
			event.setDistance(distance);
		});
		return worldData;
	}
	

	
	public List<Event> getMinimumDistEvents(double x,double y) {
		Map<Location,Event> worldData = populateDistances(x, y);
		List<Event> events = new ArrayList<Event>();
		List<Event> minimumDistEvent = new ArrayList<Event>();
		worldData.forEach((location,event)->{
			events.add(event);
			
		});
		
		events.sort((e1, e2)->e1.getDistance().compareTo(e2.getDistance()));
		events.stream().limit(5).forEach(eve->{
			minimumDistEvent.add(eve);
		});
		
		return minimumDistEvent;
		
	}
	
	private static double getDistance(double x1,double x0, double y1, double y0) {
		return Math.abs(x1-x0) + Math.abs(y1-y0);
	}
	
}



