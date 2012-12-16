package me.cidi.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import me.cidi.model.Comment;

public class CommentDAO extends SqlMapClientDaoSupport {

	public List<Comment> listComment(Long itemId) {
		return (List<Comment>)getSqlMapClientTemplate().queryForList("listComment", itemId);
	}

	public Comment getCommentById(Long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (Comment)getSqlMapClientTemplate().queryForObject("getCommentById", map);
	}

	public void updateComment(Long id, String writer, String body) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("writer", writer);
		map.put("body", body);
		getSqlMapClientTemplate().update("updateComment", map );
	}

	public Long createComment(Comment comment) {
		return (Long)getSqlMapClientTemplate().insert("createComment", comment);
	}

	public void deleteComment(Long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		getSqlMapClientTemplate().delete("deleteComment", map);
	}

}
