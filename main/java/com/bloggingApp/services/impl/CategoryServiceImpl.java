package com.bloggingApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingApp.entities.Category;
import com.bloggingApp.exceptions.ResourceNotFoundException;
import com.bloggingApp.payloads.CategoryDto;
import com.bloggingApp.repository.CategoryRepo;
import com.bloggingApp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cate = this.modelMapper.map(categoryDto, Category.class);
		Category addCat = this.categoryRepo.save(cate);
		return this.modelMapper.map(addCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category" , "categoryId", categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> allCategory = this.categoryRepo.findAll();
		List<CategoryDto> catDto =  allCategory.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		
		return catDto;
	}

}
