package com.BookShoppingCart.service.CartService;

import java.util.List;

import com.BookShoppingCart.Repository.AddToCartRepo;
import com.BookShoppingCart.Repository.CheckoutRepo;
import com.BookShoppingCart.service.ProductService.BookServiceslmpl;
import com.BookShoppingCart.service.UserServices.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookShoppingCart.model.AddtoCart;
import com.BookShoppingCart.model.CheckoutCart;

@Service
public class CartSerivceImpl implements CartService {

	@Autowired
    AddToCartRepo addCartRepo;
	@Autowired
    CheckoutRepo checkOutRepo;
	@Autowired
	BookServiceslmpl proServices;
	@Autowired
    UserServiceImpl useServices;
    private static final Logger logger = LoggerFactory.getLogger(CartSerivceImpl.class);

	@Override
	public List<AddtoCart> addCartbyUserIdAndProductId(long productId, long userId,String name,int qty,double price) throws Exception {
		try {
			if(addCartRepo.getCartByProductIdAnduserId(userId, productId).isPresent()){
				throw new Exception("Product already exists.");
			}
			AddtoCart obj = new AddtoCart();
			obj.setBook(proServices.getProductById(productId).get());
			obj.setQty(qty);
			obj.setUser(useServices.getUserDetailById(userId));
			obj.setName(name);
			obj.setPrice(price);
			addCartRepo.save(obj);		
			return this.getCartByUserId(userId);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public List<AddtoCart> getCartByUserId(long userId) {
		return addCartRepo.getCartByuserId(userId);
	}

	@Override
	public List<AddtoCart> removeCartByUserId(long cartId, long userId) {
		addCartRepo.deleteCartByIdAndUserId(userId, cartId);
		return this.getCartByUserId(userId);
	}

	@Override
	public void updateQtyByCartId(long cartId, int qty, double price) throws Exception {
		addCartRepo.updateQtyByCartId(cartId,price,qty);
	}

	@Override
	public Boolean checkTotalAmountAgainstCart(double totalAmount,long userId) {
		double total_amount =addCartRepo.getTotalAmountByUserId(userId);
		if(total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
		return false;
	}
	@Override
	public List<CheckoutCart> getAllCheckoutList() {
		return checkOutRepo.findAll();
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
		return checkOutRepo.getByuserId(userId);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUser_id();
			if(tmp.size() >0) {
				checkOutRepo.saveAll(tmp);
				this.removeAllCartByUserId(user_id);
				return this.getAllCheckoutByUserId(user_id);
			}	
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout"+e.getMessage());
		}
		
	}

	@Override
	public List<AddtoCart> removeAllCartByUserId(long userId) {
		addCartRepo.deleteAllCartByUserId(userId);
		return null;
	}

}
