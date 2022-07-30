package com.BookShoppingCart.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.BookShoppingCart.model.Books;
@Repository
public interface BookRepo extends JpaRepository<Books, Long> {
	@Override
	Page<Books> findAll(Pageable pageable);

	@Query("Select pro FROM Books pro WHERE pro.category_id=:cat_id")
	List<Books> getByCategoryId(@Param("cat_id")String cat_id);


}
