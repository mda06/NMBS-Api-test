package com.nmbs.api.dao;

import org.json.JSONObject;

import com.nmbs.api.model.DepartureInfo;
import com.nmbs.api.model.PlatformInfo;

public class DepartureInfoDAO {
	public static DepartureInfo getDepartureInfo(JSONObject obj) {
		int delay = obj.getInt("delay");
		long timeStamp = obj.getLong("time");
		PlatformInfo platform = PlatformDAO.getPlatformInfoWithJSON(obj.getJSONObject("platforminfo"));
		boolean canceled = obj.getString("canceled").equals("1");
		return new DepartureInfo(timeStamp, platform, delay, canceled);
	}
}
