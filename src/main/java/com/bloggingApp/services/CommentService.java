package com.bloggingApp.services;

import com.bloggingApp.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDo, Integer postId);

	void delete(Integer commentId);

}
