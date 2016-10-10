package com.nmbs.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONObject;

import com.nmbs.api.model.DepartureInfo;
import com.nmbs.api.model.Station;
import com.nmbs.api.model.StationCache;
import com.nmbs.api.model.Vehicle;
import com.nmbs.api.model.Via;

public class ViaDAO {
	public static List<Via> getVias(JSONObject obj) {
		List<Via> vias = new ArrayList<Via>();
		if(obj.has("vias")) {
			JSONObject objVias = obj.getJSONObject("vias");
			objVias.getJSONArray("via").forEach(new Consumer<Object>() {
				@Override
				public void accept(Object t) {
					JSONObject obj = (JSONObject)t;
					vias.add(getVia(obj));
				}
			});
		}
		return vias;
	}
	
	public static Via getVia(JSONObject obj) {
		int id = obj.getInt("id");
		JSONObject stationObj = obj.getJSONObject("stationinfo");
		Station station = StationCache.getInstance().getStationWithID(stationObj.getString("@id"));
		Vehicle vehicle = new Vehicle("NOT_USED", obj.getString("vehicle"));
		String direction = obj.getJSONObject("direction").getString("name");
		DepartureInfo arrInfo = DepartureInfoDAO.getDepartureInfo(obj.getJSONObject("arrival"));
		DepartureInfo depInfo = DepartureInfoDAO.getDepartureInfo(obj.getJSONObject("departure"));
		return new Via(id, arrInfo, depInfo, station, vehicle, direction);
	}
}
