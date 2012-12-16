package me.cidi.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;


public class MappingUtil {
	private static final Logger log = Logger.getLogger(MappingUtil.class.getName());
	
	public static Long parseLong(HttpServletRequest request, String para) {
		String value = request.getParameter(para);
		if(value!=null){
			try {
				Long result = Long.parseLong(value);
				return result;
			} catch (NumberFormatException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return null;
	}
	
	public static Double parseDouble(HttpServletRequest request, String para) {
		String value = request.getParameter(para);
		if(value!=null){
			try {
				Double result = Double.parseDouble(value);
				return result;
			} catch (NumberFormatException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return null;
	}
	
	
	public static Integer parseInt(HttpServletRequest request, String para) {
		String value = request.getParameter(para);
		if(value!=null){
			try {
				Integer result = Integer.parseInt(value);
				return result;
			} catch (NumberFormatException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return null;
	}
	
	public static Boolean parseBoolean(HttpServletRequest request, String para) {
		String value = request.getParameter(para);
		if(value!=null){
			try {
				Boolean result = Boolean.parseBoolean(value);
				return result;
			} catch (NumberFormatException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return null;
	}
}
