package com.services.eventfinder;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.services.eventfinder.utils.ConfigFileReaderUtil;
import com.services.events.WorldDataGenerator;
import com.services.models.Event;
import com.services.models.Location;
public class EventFinderTests {
	public final static int MAX_LOCATIONS = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_LOCATIONS"));
	public final static int MAX_EVENTS_TO_DISPLAY = Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_EVENTS_TO_DISPLAY"));

	@Test
	 public void generateEventTest() {
		 WorldDataGenerator worldData = new WorldDataGenerator();
		Event event = worldData.generateEvent();
		Assert.assertNotNull(event.getTickets().size());
		Assert.assertNotNull(event.getEventNumber());
	 }
	
	@Test
	public void populateLocationPointsTest() {
		WorldDataGenerator worldData = new WorldDataGenerator();
		Map<Location,Event> locationMap =worldData.populateLocationPoints();
		locationMap.forEach((location,event)->{
			Assert.assertNotNull(location.getX_cordinate());
			Assert.assertNotNull(location.getY_cordinate());
		});
	Assert.assertEquals(locationMap.size(), MAX_LOCATIONS);
	}
	
	@Test 
	public void getMinimumDistEventsTest() {
		WorldDataGenerator worldData = new WorldDataGenerator();
		double x = 5;
		double y = 8;
		List<Event> events = worldData.getMinimumDistEvents(x, y);
		events.forEach((event)->{
			Assert.assertNotNull(event.getTickets());
			Assert.assertNotNull(event.getEventNumber());
		});
		Assert.assertEquals(events.size(), MAX_EVENTS_TO_DISPLAY);
	}
	
	@Test
	public void locationPointsRangeTest() {
		WorldDataGenerator worldData  =  new WorldDataGenerator();
		Map<Location,Event> locationMap = worldData.populateLocationPoints();
		locationMap.forEach((location,event)->{
			Assert.assertTrue(location.getX_cordinate()<=10);
			Assert.assertTrue(location.getY_cordinate()<=10);
			Assert.assertTrue(location.getX_cordinate()>=-10);
			Assert.assertTrue(location.getY_cordinate()>=-10);
		});
		
	}
}
