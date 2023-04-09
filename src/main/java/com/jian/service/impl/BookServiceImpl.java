package com.jian.service.impl;

import java.util.List;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.exception.BookNotFoundException;
import com.jian.model.Book;
import com.jian.repository.BookRepository;
import com.jian.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
    @Autowired 
    private BookRepository bookRepository;

   	@Override
	public List<Book> getAllBooks(String authorName) {
   		logger.trace("Entered getAllBooks method");
   		
   		List<Book> books = bookRepository.findAll();

		return books;
	}

	@Override
	public Book getBook(String authorName, long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}
	
	@Override
	public List<Book> getBook(String authorName) {
		return bookRepository.findByAuthorName(authorName);
				//.orElseThrow(() -> new LessonNotFoundException(instructorName));
	}

	@Override
	public Book getBook(String authorName, String bookName) {
		return bookRepository.findByBookName(bookName);
	}

	@Override
	public void deleteBook(String authorName, long id) {
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent()) {
			bookRepository.deleteById(id);
		} else {
			throw new BookNotFoundException(id);
		}
		
	}

	@Override
	public Book updateBook(String authorName, long id, Book book) {
		Book bookUpdated = bookRepository.save(book);
		return bookUpdated;
	}

	@Override
	public Book createBook(String authorName, Book book) {
		Book createdBook = bookRepository.save(book);
		return createdBook;
		
	}
    
}
