package com.BookShoppingCart.service.ProductService;

import java.util.List;
import java.util.Optional;

import com.BookShoppingCart.Repository.CategoryRepo;
import com.BookShoppingCart.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookShoppingCart.model.Category;
import com.BookShoppingCart.model.Books;

@Service
public class BookServiceslmpl {

	@Autowired
	BookRepo bookRepo;
	@Autowired
    CategoryRepo cateRepo;
	
	public List<Books>getAllProducts(){
		return bookRepo.findAll();
	}
	public List<Books>getProductsByCategory(String product_id){
		return bookRepo.getByCategoryId(product_id);
	}
	
	public List<Category>getAllCategory(){
		return cateRepo.findAll();
	}

	public Books saveProduct(Books product){
		return bookRepo.save(product);
	}

	public Optional<Books> getProductById(long id) {
		return bookRepo.findById(id);
	}

	public Books updateProduct(Books product) {
		return bookRepo.save(product);
	}

	public void deleteProductById(long id) {
		bookRepo.deleteById(id);
	}
}
