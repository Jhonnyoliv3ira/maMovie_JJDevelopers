package com.maMovie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maMovie.domain.Comment;
import com.maMovie.repository.CommentRepository;

@Service
public class CommentServices {

	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> findAll(){
		return commentRepository.findAll();
	}
	
	public Comment findById(String id) {
		Optional<Comment> obj = commentRepository.findById(id);
		return obj.get();
	}
	
	public Comment insert(Comment obj) {
		return commentRepository.insert(obj);
	}
	
	public Comment update(Comment obj) {
		Comment newObj = findById(obj.getId());
		updateData(newObj, obj);
		return commentRepository.save(newObj);
	}

	private void updateData(Comment newObj, Comment obj) {
		newObj.setText(obj.getText());
		newObj.setDate(obj.getDate());	
	}
	
	public void delete(String id) {
		commentRepository.deleteById(id);
	}
	
}
