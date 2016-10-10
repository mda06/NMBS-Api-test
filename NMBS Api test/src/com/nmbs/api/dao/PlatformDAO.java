package com.nmbs.api.dao;

import org.json.JSONObject;

import com.nmbs.api.model.PlatformInfo;

public class PlatformDAO {
	public static PlatformInfo getPlatformInfoWithJSON(JSONObject obj) {
		String name = obj.getString("name");
		String normal = obj.getString("normal");
		return new PlatformInfo(name, normal);
	}
}
