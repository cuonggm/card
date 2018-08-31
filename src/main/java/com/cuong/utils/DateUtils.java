package com.cuong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
		return (date == null) ? "null" : dateFormat.format(date);
	}

	private DateUtils() {

	}

}
