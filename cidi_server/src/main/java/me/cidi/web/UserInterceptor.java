package me.cidi.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.cidi.model.User;
import me.cidi.service.ItemService;
import me.cidi.service.UserService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {
	
	public static ThreadLocal<User> currentUser = new ThreadLocal<User>();
	
	UserService userService;
	
	/**
	 * This implementation always returns <code>true</code>.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
		String userIdStr = getCookieValue(request, "cidi_user");
		if(userIdStr==null){
			userIdStr = "1";
			User user = userService.getUserById(1L);
			if(user==null){
				userService.createDefaultUser();
			}
		}
		Long userId = Long.parseLong(userIdStr);
		User user = userService.getUserById(userId);
		currentUser.set(user);
		return true;
	}

	private String getCookieValue(HttpServletRequest request, String cookieName) {
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
    		for (int i = 0; i < cookies.length; i++) {
    			Cookie cookie = cookies[i];
    			if (cookieName.equals(cookie.getName())) {
    				return cookie.getValue();
    			}
    		}
    	}
    
    	return null;
    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}