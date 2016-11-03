package com.blog.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getValue(String key) {
		Properties properties = new Properties();
		InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/com/blog/blog.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
}
