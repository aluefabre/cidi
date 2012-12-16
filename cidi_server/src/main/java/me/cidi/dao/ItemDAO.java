package me.cidi.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import me.cidi.model.Item;

public class ItemDAO extends SqlMapClientDaoSupport {

	public List<Item> listItem() {
		return (List<Item>)getSqlMapClientTemplate().queryForList("listItem");
	}

	public Item getItemById(Long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (Item)getSqlMapClientTemplate().queryForObject("getItemById", map);
	}

	public void updateItem(Long id, String writer, String body) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("writer", writer);
		map.put("body", body);
		getSqlMapClientTemplate().update("updateItem", map );
	}

	public Long createItem(Item item) {
		return (Long)getSqlMapClientTemplate().insert("createItem", item);
	}

	public List<Item> searchItem(Double latitude, Double longitude) {
		return (List<Item>)getSqlMapClientTemplate().queryForList("searchItem");
	}

	public void deleteItem(Long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		getSqlMapClientTemplate().delete("deleteItem", map );
	}

}
