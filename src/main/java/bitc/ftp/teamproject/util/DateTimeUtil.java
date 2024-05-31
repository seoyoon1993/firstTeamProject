package bitc.ftp.teamproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
	//sql날짜 => 문자열날짜
	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	//문자열날짜 => sql날짜
	public static Date convertStringToDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date utilDate = sdf.parse(dateString);
		return new Date(utilDate.getTime());
	}
}

