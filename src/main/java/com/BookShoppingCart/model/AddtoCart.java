package com.BookShoppingCart.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "add_to_cart")
public class AddtoCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
	Books book;
	//Long product_id;
	int qty;
	double price;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	User user;
	String name;
	@Column(updatable=false, insertable=false)
	String added_date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAdded_date() {
		return added_date;
	}
	public void setAdded_date(String added_date) {
		this.added_date = added_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Books getBook() {
		return book;
	}
	public void setBook(Books product) {
		this.book = product;
	}

}
