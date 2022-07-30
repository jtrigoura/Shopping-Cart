package com.BookShoppingCart.service.Category;

import com.BookShoppingCart.Repository.CategoryRepo;
import com.BookShoppingCart.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicelmpl {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAllCategory() {

        return categoryRepo.findAll();
    }
    public Category saveCategory(Category category){
        return categoryRepo.save(category);
    }

    public Optional<Category> getCategoryById(long id) {
        return categoryRepo.findById(id);
    }

    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteCategoryById(long id) {
        categoryRepo.deleteById(id);
    }

}
