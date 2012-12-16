package me.cidi.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import me.cidi.model.User;

public class UserDAO extends SqlMapClientDaoSupport {

	public List<User> listUser() {
		return (List<User>)getSqlMapClientTemplate().queryForList("listUser");
	}

	public User getUserById(Long userId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", userId);
		return (User)getSqlMapClientTemplate().queryForObject("getUserById", map);
	}

	public void updateUser(Long id, String name, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("password", password);
		map.put("id", id);
		getSqlMapClientTemplate().update("updateUser", map );
	}

	public Long createUser(User user) {
		return (Long)getSqlMapClientTemplate().insert("createUser", user);
	}

	public void deleteUser(Long userId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", userId);
		getSqlMapClientTemplate().delete("deleteUser", map );
	}

	public void createDefaultUser() {
		getSqlMapClientTemplate().insert("createDefaultUser");
	}

}
