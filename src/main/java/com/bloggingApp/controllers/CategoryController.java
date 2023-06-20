package com.bloggingApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.entities.Category;
import com.bloggingApp.payloads.ApiResponse;
import com.bloggingApp.payloads.CategoryDto;
import com.bloggingApp.services.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto categoryDto1 = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
	 this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new  ApiResponse("category is successfully deleted",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto categoryDto1 = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> cat = this.categoryService.getCategories();
		return ResponseEntity.ok(cat);
	}
	
	

}
