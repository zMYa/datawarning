package com.feihe.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * 读取属性文件工具类
 * @author 若水
 *
 */
public class PropertiesUtil {
	
	/**
	 * 根据key获取value
	 * @param arg key
	 * @return
	 */
	public static String loadProperties(String path,String arg){
		
		Properties prop = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if(key.equals(arg)){
					String value = prop.getProperty(key);
					return value;
				}
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
	}
}
