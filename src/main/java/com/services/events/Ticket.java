package com.services.events;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
	private List<Integer> prices = new  ArrayList<Integer>();

	public List<Integer> getPrices() {
		return prices;
	}

	public void setPrices(List<Integer> prices) {
		this.prices = prices;
	}
}
