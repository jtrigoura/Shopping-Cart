package com.BookShoppingCart.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.BookShoppingCart.model.CheckoutCart;


@Repository
public interface CheckoutRepo  extends JpaRepository<CheckoutCart, Long> {
	@Override
	Page<CheckoutCart> findAll(Pageable pageable);

	@Query("Select checkCart  FROM CheckoutCart checkCart WHERE checkCart.user_id=:user_id")
	List<CheckoutCart> getByuserId(@Param("user_id")Long user_id);
}
