/**
 * Event model consists of all the objects associated with the event - event number, distance, tickets
 * @author Chirg Basavaraj
 *
 */
package com.services.models;

import java.util.ArrayList;
import java.util.List;

public class Event {
	private Double distance;
	List<Double> tickets= new ArrayList<Double>();
	private int eventNumber;
	
	public int getEventNumber() {
		return eventNumber;
	}
	public void setEventNumber(int eventNumber) {
		this.eventNumber = eventNumber;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public List<Double> getTickets() {
		return tickets;
	}
	public void setTickets(List<Double> tickets) {
		this.tickets = tickets;
	}
}
