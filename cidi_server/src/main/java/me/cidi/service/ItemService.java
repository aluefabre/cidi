package me.cidi.service;

import java.util.Date;
import java.util.List;

import me.cidi.dao.ItemDAO;
import me.cidi.model.Item;

public class ItemService {

	ItemDAO itemDAO;
	
	public List<Item> listItem() {
		return itemDAO.listItem();
	}

	public Item getItemById(Long itemId) {
		return itemDAO.getItemById(itemId);
	}

	public void updateItem(Long itemId, String writer, String body) {
		itemDAO.updateItem(itemId, writer, body);
	}

	public Long createItem(String writer, Long userId, String body, Double latitude, Double longitude, String address) {
		Item item = new Item();
		item.setAddress(address);
		item.setBody(body);
		item.setLatitude(latitude);
		item.setLongitude(longitude);
		item.setGmtCreate(new Date());
		item.setWriter(writer);
		item.setUserId(userId);
		return itemDAO.createItem(item);
	}

	public List<Item> searchItem(Double latitude, Double longitude) {
		return itemDAO.searchItem(latitude, longitude);
	}

	public void deleteItem(Long itemId) {
		itemDAO.deleteItem(itemId);
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
}
