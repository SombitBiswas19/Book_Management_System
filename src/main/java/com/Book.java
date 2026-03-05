package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BookDetails")
public class Book {
	@Id
	@Column(name="Book_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int book_id;
	@Column(name="Book_title",nullable=false,unique=true)
	private String title;
	@Column(name="Book_author",nullable=false)
	private String author;
	@Column(name="Book_price",nullable=false)
	private double price;
	public Book() {
		super();
	}
	public Book(int book_id, String title, String author, double price) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}
	
	
}
