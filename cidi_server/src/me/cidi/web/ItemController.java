package me.cidi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.cidi.model.Comment;
import me.cidi.model.Item;
import me.cidi.model.User;
import me.cidi.service.CommentService;
import me.cidi.service.ItemService;
import me.cidi.util.MappingUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

public class ItemController extends MultiActionController{
	ItemService itemService;
	CommentService commentService;
	
	private static final Logger log = Logger.getLogger(ItemController.class.getName());
		
	public ModelAndView listItem(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		List<Item> items = itemService.listItem();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("items", items);
		return new ModelAndView("listItem", "model", model);	
	}

	public ModelAndView listItemJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Item> items = itemService.listItem();
		Gson gson = new Gson();
		String json = gson.toJson(items);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView editItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = MappingUtil.parseLong(request, "itemId");
		Item item = itemService.getItemById(itemId);
		List<Comment> comments = commentService.listComment(itemId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", item);
		model.put("comments", comments);
		return new ModelAndView("editItem", "model", model);	
	}
	
	public ModelAndView editItemJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = MappingUtil.parseLong(request, "itemId");
		Item item = itemService.getItemById(itemId);
		Gson gson = new Gson();
		String json = gson.toJson(item);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView editItemSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = editItemSubmitInner(request);
		return new ModelAndView(new RedirectView("editItem.htm?itemId="+itemId));
	}

	public ModelAndView editItemSubmitJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = editItemSubmitInner(request);
		return new ModelAndView("jsonView", "json", "true");	
	}
	
	private Long editItemSubmitInner(HttpServletRequest request) {
		Long itemId = MappingUtil.parseLong(request, "itemId");
		String writer = request.getParameter("writer");
		String body = request.getParameter("body");
		itemService.updateItem(itemId, writer, body);
		return itemId;
	}
	
	public ModelAndView addItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("addItem", "model", model);	
	}
	
	public ModelAndView addItemSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		addItemSubmitInner(request);
		return new ModelAndView(new RedirectView("listItem.htm"));
	}
	
	public ModelAndView addItemSubmitJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = addItemSubmitInner(request);
		return new ModelAndView("jsonView", "json", itemId);	
	}

	private Long addItemSubmitInner(HttpServletRequest request) {
		Double latitude = MappingUtil.parseDouble(request, "latitude");
		Double longitude = MappingUtil.parseDouble(request, "longitude");
		String body = request.getParameter("body");
		String writer = request.getParameter("writer");
		String address = request.getParameter("address");
		User user = UserInterceptor.currentUser.get();
		Long itemId = itemService.createItem(writer, user.getId(), body, latitude, longitude, address);
		return itemId;
	}
	
	public ModelAndView searchItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("searchItem", "model", model);	
	}
	

	public ModelAndView searchItemSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Double latitude = MappingUtil.parseDouble(request, "latitude");
		Double longitude = MappingUtil.parseDouble(request, "longitude");
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<Item> items = itemService.searchItem(latitude, longitude);
		model.put("items", items);
		
		return new ModelAndView("searchItem", "model", model);	
	}
	
	public ModelAndView searchItemBasicJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Double latitude = MappingUtil.parseDouble(request, "latitude");
		Double longitude = MappingUtil.parseDouble(request, "longitude");
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<Item> items = itemService.searchItem(latitude, longitude);

		Gson gson = new Gson();
		String json = gson.toJson(items);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView deleteItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = MappingUtil.parseLong( request, "itemId");
		Item item = itemService.getItemById(itemId);
		itemService.deleteItem(itemId);
		return new ModelAndView(new RedirectView("listItem.htm"));
	}
	
	public ModelAndView deleteItemJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = MappingUtil.parseLong( request, "itemId");
		Item item = itemService.getItemById(itemId);
		itemService.deleteItem(itemId);
		return new ModelAndView("jsonView", "json", "true");	
	}
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public CommentService getCommentService() {
		return commentService;
	}


	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

}