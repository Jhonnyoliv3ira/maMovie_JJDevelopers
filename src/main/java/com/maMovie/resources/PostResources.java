package com.maMovie.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maMovie.domain.Post;
import com.maMovie.services.PostServices;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {

	@Autowired
	private PostServices postServices;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = postServices.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable  String id){
		Post obj = postServices.findById(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@PostMapping
	public ResponseEntity<Post> insert(@PathVariable Post obj){
		obj = postServices.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
}
