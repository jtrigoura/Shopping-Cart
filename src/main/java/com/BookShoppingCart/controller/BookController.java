package com.BookShoppingCart.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.BookShoppingCart.Repository.BookRepo;
import com.BookShoppingCart.controller.RequestPojo.SearchForm;
import com.BookShoppingCart.service.ProductService.BookServiceslmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.BookShoppingCart.model.Category;
import com.BookShoppingCart.model.Books;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("api/product")
public class BookController {
    @Autowired
    BookServiceslmpl productServiceslmpl;
    @Autowired
    BookRepo bookRepo;

    @RequestMapping("getAll")
    public List<Books> getAllPRoducts() {


        return productServiceslmpl.getAllProducts();
    }

    @RequestMapping("getAllCategory")
    public List<Category> getAllCategory() {
        return productServiceslmpl.getAllCategory();
    }

    @RequestMapping("getProductsByCategory")
    public List<Books> getProductsByCategory(@RequestBody HashMap<String, String> request) {
        String category_id = request.get("cat_id");
        return productServiceslmpl.getProductsByCategory(category_id);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> addProducts(@RequestBody Books product) {
        try {
            Books returnedCategory = productServiceslmpl.saveProduct(product);
            return new ResponseEntity<>(Arrays.asList(returnedCategory, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new String("error" + e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getFindById(@PathVariable long id) {
        try {

            Optional<Books> optProduct = productServiceslmpl.getProductById(id);

            if (optProduct.isPresent()) {
                return new ResponseEntity(new String ("successful"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/bookById/{id}")
    public Optional<Books> productById(@PathVariable(value = "id") Long id) {
        return productServiceslmpl.getProductById(id);
    }


    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Books _product, @PathVariable long id) {
        try {

            Books product = productServiceslmpl.getProductById(id).get();

            //set new values for products
            product.setName(_product.getName());
            product.setPrice(_product.getPrice());
            product.setDescription(_product.getDescription());
            product.setQuatityavi(_product.getQuatityavi());
            product.setAdded_on(_product.getAdded_on());
            product.setCategory_id(_product.getCategory_id());
            product.setRatings(_product.getRatings());
            product.setFavourite(_product.getFavourite());
            product.setSeller(_product.getSeller());

            // save the change to database
            productServiceslmpl.updateProduct(product);

            return new ResponseEntity<>(Arrays.asList(product, ""), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new String("lỗi"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable long id) {
        try {
            // checking the existed of a Customer with id
            productServiceslmpl.deleteProductById(id);
            return new ResponseEntity(new String(" Xóa thành công"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/searchAllColumn")
    public ResponseEntity<?> showEditForm(@RequestBody SearchForm searchString) {
        List<Books> products = bookRepo.findAll();
//		products = products.stream().filter(
//				item -> item.getId().toString().contains(searchString.getSearchString())
//						|| item.getName().contains(searchString.getSearchString())
//						|| item.getPrice().contains(searchString.getSearchString())
//						|| item.getCategory_id().contains(searchString.getSearchString())
//		).collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
