package me.cidi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.cidi.model.Comment;
import me.cidi.model.User;
import me.cidi.service.CommentService;
import me.cidi.util.MappingUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

public class CommentController extends MultiActionController{
	CommentService commentService;

	private static final Logger log = Logger.getLogger(CommentController.class.getName());
		
	public ModelAndView listComment(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Long itemId = MappingUtil.parseLong(request, "itemId");
		List<Comment> comments = commentService.listComment(itemId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("comments", comments);
		return new ModelAndView("listComment", "model", model);	
	}

	public ModelAndView listCommentJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long itemId = MappingUtil.parseLong(request, "itemId");
		List<Comment> comments = commentService.listComment(itemId);
		Gson gson = new Gson();
		String json = gson.toJson(comments);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView editComment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long commentId = MappingUtil.parseLong(request, "commentId");
		Comment comment = commentService.getCommentById(commentId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("comment", comment);
		return new ModelAndView("editComment", "model", model);	
	}
	
	public ModelAndView editCommentJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long commentId = MappingUtil.parseLong(request, "commentId");
		Comment comment = commentService.getCommentById(commentId);
		Gson gson = new Gson();
		String json = gson.toJson(comment);
		return new ModelAndView("jsonView", "json", json);	
	}
	
	public ModelAndView editCommentSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long commentId = MappingUtil.parseLong(request, "commentId");
		String writer = request.getParameter("writer");
		String body = request.getParameter("body");
		commentService.updateComment(commentId, writer, body);
		return new ModelAndView(new RedirectView("editComment.htm?commentId="+commentId));
	}
	
	public ModelAndView addComment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Long itemId = MappingUtil.parseLong(request, "itemId");
		model.put("itemId", itemId);
		return new ModelAndView("addComment", "model", model);	
	}
	
	public ModelAndView addCommentSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String body = request.getParameter("body");
		String writer = request.getParameter("writer");
		Long itemId = MappingUtil.parseLong(request, "itemId");
		User user = UserInterceptor.currentUser.get();
		Long commentId = commentService.createComment(itemId, writer, user.getId(), body);
		return new ModelAndView(new RedirectView("../item/editItem.htm?itemId="+itemId));
	}
	
	public ModelAndView deleteComment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long commentId = MappingUtil.parseLong( request, "commentId");
		Comment comment = commentService.getCommentById(commentId);
		commentService.deleteComment(commentId);
		return new ModelAndView(new RedirectView("../item/editItem.htm?itemId="+comment.getItemId()));
	}
	
	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

}