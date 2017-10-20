package com.services.eventfinder;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.services.eventfinder.utils.ConfigFileReaderUtil;
import com.services.events.WorldDataGenerator;
import com.services.models.Event;
import com.services.models.Location;
public class EventFinderTests {
	public final static int MAX_NO_TICKETS =Integer.parseInt(ConfigFileReaderUtil.propertyFileReader().getProperty("MAX_NO_TICKETS"));

	
	 
	 
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
		Assert.assertNotNull(locationMap.keySet());
	}
}
