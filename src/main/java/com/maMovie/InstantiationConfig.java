package com.maMovie;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.maMovie.domain.Comment;
import com.maMovie.domain.Post;
import com.maMovie.domain.User;
import com.maMovie.repository.CommentRepository;
import com.maMovie.repository.PostRepository;
import com.maMovie.repository.UserRepository;

@Configuration
public class InstantiationConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		commentRepository.deleteAll();
		
		User user = new User(null, "Joseraldo", "degaus@gmail.com", "D3gs", "123456");
		User user2 = new User(null, "Sun", "Sun@gmail.com", "sunbr", "654321");
		
		userRepository.saveAll(Arrays.asList(user, user2));
		
		
		Post post1 = new Post(null, Instant.now(), "Embusca do vale encantado", "Passa o beck", user2);
		Post post2 = new Post(null, Instant.now(), "Embusca do vale encantado", "Passa o beck", user2);
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		user.getPosts().add(post1);
		userRepository.save(user);
		
		Comment cm = new Comment(null, "Eu sou foda vidao", Instant.now(), post2, user2);
		
		commentRepository.save(cm);
		
		post2.getComments().add(cm);
		
		postRepository.save(post2);
	
	
	}

}
