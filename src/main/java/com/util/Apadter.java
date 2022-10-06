package com.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Apadter {
	private Apadter() {
	}

	public static LocalDateTime getLocalDateTimeNow() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String time = LocalDateTime.now().format(df);
		return LocalDateTime.parse(time,df);
	}
	
	public static LocalDate stringToLocalDate(String str) {
		return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static LocalDateTime stringToLocalDateTime(String str) {
		return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static LocalDateTime dateToLocalDateTime(java.util.Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static java.util.Date localDateTimeToDate(LocalDateTime localDateTime) {
		return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static java.util.Date localDateToDate(LocalDate localDate) {
		return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate dateToLocalDate(java.util.Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
