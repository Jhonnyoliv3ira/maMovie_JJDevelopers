package com.maMovie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maMovie.domain.Post;
import com.maMovie.repository.PostRepository;

@Service
public class PostServices {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.get();
	}
	
	public Post insert(Post obj) {
		return postRepository.save(obj);
	}
	
	public void delete(String id) {
		 postRepository.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		updateData(newObj, obj);
		return postRepository.save(newObj);
	}

	private void updateData(Post newObj, Post obj) {
		newObj.setBody(obj.getBody());
		newObj.setInstant(obj.getInstant());
		newObj.setTitle(obj.getTitle());
	}
	
}
