package com.bloggingApp.services;

import java.util.List;

import com.bloggingApp.payloads.PostDto;
import com.bloggingApp.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer PageSize, String sortBy, String sortDir);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);

	List<PostDto> getPostByUser(Integer userId);

	List<PostDto> searchPosts(String keyword);

}
