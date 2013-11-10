package com.bldev.geominders.util;

public class Places {

	private String placeName;
	private double lat;
	private double lon;
	private int radio;
	private ACTIONS[] actions;
	

	public void setActions(ACTIONS[] actions) {
		this.actions = actions;
	}

	public ACTIONS[] getActions() {
		return actions;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

}
