package me.cidi.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PageVariablesInterceptor extends HandlerInterceptorAdapter {

	/**
	 * This implementation is empty.
	 */
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		Map<String, Object> model = (Map<String, Object>)modelAndView.getModel().get("model");
		if(model==null){
			model = new HashMap<String, Object>();
			modelAndView.addObject("model", model);
		}
		model.put("appContext", "/cidi_server");
	}

}