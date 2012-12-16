package me.cidi.service;

import java.util.Date;
import java.util.List;

import me.cidi.dao.UserDAO;
import me.cidi.model.User;

public class UserService {

	UserDAO userDAO;
	
	public List<User> listUser() {
		return userDAO.listUser();
	}

	public User getUserById(Long userId) {
		return userDAO.getUserById(userId);
	}

	public void updateUser(Long id, String name, String password) {
		userDAO.updateUser(id, name, password);
	}

	public Long createUser(String name, String password) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setGmtCreate(new Date());
		return userDAO.createUser(user);
	}

	public void deleteUser(Long userId) {
		userDAO.deleteUser(userId);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void createDefaultUser() {
		userDAO.createDefaultUser();
	}
}
