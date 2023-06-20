package com.bloggingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.entities.Category;
import com.bloggingApp.entities.Post;
import com.bloggingApp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);

}
