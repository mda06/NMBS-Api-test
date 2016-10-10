package com.nmbs.api.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
	public static Date timeStampToDate(long timeStamp) {
		//Time * 1000 because it's in ms
		Timestamp stamp = new Timestamp(timeStamp * 1000);
		return new Date(stamp.getTime());
	}
}
