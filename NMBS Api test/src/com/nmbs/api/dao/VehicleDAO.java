package com.nmbs.api.dao;

import org.json.JSONObject;

import com.nmbs.api.model.Vehicle;

public class VehicleDAO {
	public static Vehicle getVehicleFromJSONObject(JSONObject obj) {
		String id = obj.getString("@id");
		String formattedID = obj.getString("name");
		return new Vehicle(id, formattedID);
	}
}
