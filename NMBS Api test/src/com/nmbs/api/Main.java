package com.nmbs.api;

import java.util.List;

import com.nmbs.api.dao.ConnectionDAO;
import com.nmbs.api.dao.StationDAO;
import com.nmbs.api.model.Connection;
import com.nmbs.api.model.Station;
import com.nmbs.api.model.StationCache;

//JSON APi
//https://github.com/stleary/JSON-java
public class Main {
	
	public static void main(String[] args) throws Exception {
		StationDAO.loadCache();
		List<Station> st = StationCache.getInstance().getStations();

		System.out.println("Cache is loaded");
		int i =0;
		for(Connection c : ConnectionDAO.getConnections(st.get(143), st.get(153)))
			System.out.println("N°" + ++i + " " + c + "\n");
	}
}
