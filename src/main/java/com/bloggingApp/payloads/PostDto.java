package com.bloggingApp.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private Integer postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private UserDto user;

	private CategoryDto category;

	private Set<CommentDto> comments = new HashSet<>();

}
