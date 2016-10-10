package com.nmbs.api.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nmbs.api.model.Connection;
import com.nmbs.api.model.Departure;
import com.nmbs.api.model.Station;
import com.nmbs.api.util.NetUtil;

public class ConnectionDAO {
	private static final String CONNECTIONS_URL = "https://api.irail.be/connections/?to=";
	
	//TODO: Make a new function with optional
	//(OPTIONAL:) &date={dmy}&time=2359&timeSel=arrive or depart
	public static List<Connection> getConnections(Station from, Station to) throws Exception {
		List<Connection> connections = new ArrayList<Connection>();
		try {
			String finalURl = CONNECTIONS_URL + to.getFormattedID() + "&from=" + from.getFormattedID() + "&format=json";
			String curlUrl = NetUtil.curlURL(finalURl);

			JSONObject jBase = new JSONObject(curlUrl);
			if(jBase.has("error")) {
				throw new Exception("Server of NMBS is down");
			}
			
			JSONArray arrCon = jBase.getJSONArray("connection");
			arrCon.forEach(new Consumer<Object>() {
				@Override
				public void accept(Object t) {
					JSONObject obj = (JSONObject)t;
					Connection c = getConnection(obj);
					connections.add(c);
				}
			});
		} catch (IOException io) {
			System.err.println("Error in StationDAO.loadCache()");
			io.printStackTrace();
		}
		return connections;
	}
	
	private static Connection getConnection(JSONObject obj) {
		int id = obj.getInt("id");
		int duration = obj.getInt("duration");
		Departure departure = DepartureDAO.getDeparture(obj.getJSONObject("departure"));
		Departure arrival = DepartureDAO.getDeparture(obj.getJSONObject("arrival"));
		return new Connection(id, duration, departure, arrival, ViaDAO.getVias(obj));
	}
}
