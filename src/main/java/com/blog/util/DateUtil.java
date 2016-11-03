package com.blog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String formatStrToSQL(String str,String type,String formatType) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(type);
		Date date=sdf.parse(str);
		sdf=new SimpleDateFormat(formatType);
		return "%"+sdf.format(date)+"%";
	}
	
	public static String formatDateToStr(Date date,String type){
		SimpleDateFormat sdf=new SimpleDateFormat(type);
		return sdf.format(date);
	}
	
	public static String getCurrentDateStr(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
}
