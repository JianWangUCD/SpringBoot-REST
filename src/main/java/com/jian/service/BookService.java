package com.jian.service;

import java.util.List;

import com.jian.model.Book;

public interface BookService {

	List<Book> getAllBooks(String authorName);
	
	Book getBook(String authorName, long id);
	
	List<Book> getBook(String authorName);
	
	Book getBook(String authorName, String BookName);
	
	void deleteBook(String authorName, long id);
	
	Book updateBook(String authorName, long id, Book book);
	
	Book createBook(String authorName, Book book);
}
