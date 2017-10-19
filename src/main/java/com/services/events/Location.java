package com.services.events;

public class Location {
	private double x_cordinate; 
	private double y_cordinate;
	private Event ev = new Event();
	
	public double getX_cordinate() {
		return x_cordinate;
	}
	public void setX_cordinate(double x_cordinate) {
		this.x_cordinate = x_cordinate;
	}
	public double getY_cordinate() {
		return y_cordinate;
	}
	public void setY_cordinate(double y_cordinate) {
		this.y_cordinate = y_cordinate;
	}
	
	public void setEvent(Event event) {
		this.ev = event;
	}
	
	public Event getEvent() {
		return ev;
	}
	
}
