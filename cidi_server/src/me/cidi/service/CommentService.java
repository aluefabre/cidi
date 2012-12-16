package me.cidi.service;

import java.util.Date;
import java.util.List;

import me.cidi.dao.CommentDAO;
import me.cidi.model.Comment;

public class CommentService {

	CommentDAO commentDAO;
	
	public List<Comment> listComment(Long itemId) {
		return commentDAO.listComment(itemId);
	}

	public Comment getCommentById(Long commentId) {
		return commentDAO.getCommentById(commentId);
	}

	public void updateComment(Long commentId, String writer, String body) {
		commentDAO.updateComment(commentId, writer, body);
	}

	public Long createComment(Long itemId, String writer, Long userId, String body) {
		Comment comment = new Comment();
		comment.setBody(body);
		comment.setGmtCreate(new Date());
		comment.setWriter(writer);
		comment.setItemId(itemId);
		comment.setUserId(userId);
		return commentDAO.createComment(comment);
	}

	public void deleteComment(Long commentId) {
		commentDAO.deleteComment(commentId);
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
}
