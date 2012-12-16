package me.cidi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.cidi.model.User;
import me.cidi.model.User;
import me.cidi.service.UserService;
import me.cidi.util.MappingUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

public class UserController extends MultiActionController{
	UserService userService;

	private static final Logger log = Logger.getLogger(UserController.class.getName());
		
	public ModelAndView listUser(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		List<User> users = userService.listUser();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users", users);
		return new ModelAndView("listUser", "model", model);	
	}


	public ModelAndView listUserJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<User> users = userService.listUser();
		Gson gson = new Gson();
		String json = gson.toJson(users);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView editUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = MappingUtil.parseLong(request, "userId");
		User user = userService.getUserById(userId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		return new ModelAndView("editUser", "model", model);	
	}
	
	public ModelAndView editUserJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = MappingUtil.parseLong(request, "userId");
		User user = userService.getUserById(userId);
		Gson gson = new Gson();
		String json = gson.toJson(user);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView editUserSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = MappingUtil.parseLong(request, "userId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		userService.updateUser(userId, name, password);
		return new ModelAndView(new RedirectView("editUser.htm?userId="+userId));
	}
	
	public ModelAndView addUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("addUser", "model", model);	
	}
	
	public ModelAndView addUserSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Long userId = userService.createUser(name, password);
		return new ModelAndView(new RedirectView("listUser.htm"));
	}
	
	public ModelAndView deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = MappingUtil.parseLong( request, "userId");
		User user = userService.getUserById(userId);
		userService.deleteUser(userId);
		return new ModelAndView(new RedirectView("listUser.htm"));
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}