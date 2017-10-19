package com.services.events;

import java.util.ArrayList;
import java.util.List;

public class Event {
	private int distance;
	List<Ticket> tickets= new ArrayList<Ticket>();

	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
