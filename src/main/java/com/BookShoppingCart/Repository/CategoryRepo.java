package com.BookShoppingCart.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.BookShoppingCart.model.Category;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo  extends JpaRepository<Category, Long> {
    @Override
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(long id);

}
