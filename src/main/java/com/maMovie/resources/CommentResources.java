package com.maMovie.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maMovie.domain.Comment;
import com.maMovie.services.CommentServices;

@RestController
@RequestMapping(value = "/comments")
public class CommentResources {

	@Autowired
	private CommentServices commentServices;
	
	@GetMapping
	public ResponseEntity<List<Comment>> findAll(){
		List<Comment> list = commentServices.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comment> findById(@PathVariable String id){
		Comment obj = commentServices.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Comment> insert(@PathVariable Comment obj){
		obj = commentServices.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Comment> delete(@PathVariable String id){
		commentServices.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/{id}")
	public ResponseEntity<Comment> update(@RequestBody Comment obj, String id){
		commentServices.update(obj);
		return ResponseEntity.ok().body(obj);
	}
}
