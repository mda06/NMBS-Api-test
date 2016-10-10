package com.nmbs.api.model;

import java.util.ArrayList;
import java.util.List;

public class StationCache {
	private List<Station> stations;
	private static StationCache instance;
	
	private StationCache() {
		stations = new ArrayList<Station>();
	}
	
	public static StationCache getInstance() {
		if(instance == null) instance = new StationCache();
		return instance;
	}
	
	public void addStation(Station station) {
		stations.add(station);
	}
	
	public List<Station> getStations() {
		return stations;
	}
	
	public Station getStationWithID(String id) {
		for(Station st : stations) {
			if(st.getId().equals(id))
				return st;
		}
		return null;
	}
}
