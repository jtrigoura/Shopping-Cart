package com.BookShoppingCart.service.Category;

import com.BookShoppingCart.model.Category;

public interface CategoryService {
    Category findById(long categoryId) throws Exception;
    Category getCategoryDetailById(long categoryId) throws Exception;
}
