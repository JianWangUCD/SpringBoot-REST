package com.jian.controller;

import java.net.URI;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jian.model.Book;
import com.jian.service.BookService;

//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/authors/{authorName}/books")
	public List<Book> getAllBooks(@PathVariable String authorName) {
		
		List<Book> books = bookService.getAllBooks(authorName);
		
		return books;
	}

	//search by id
	@GetMapping("/authors/{authorName}/books/{id}")
	public Book getBook(@PathVariable String authorName, @PathVariable long id) {
		
		Book book = bookService.getBook(authorName, id);
		return book;
	}
	
	//search by author name
	@GetMapping("/authors/{authorName}")
	public List<Book> getBook(@PathVariable String authorName) {
		
		List<Book> books = bookService.getBook(authorName);
		return books;
	}
	
	//search by book name
	@GetMapping("/authors/{authorName}/{bookName}/books")
	public Book getBook(@PathVariable String authorName, @PathVariable String bookName) {
		
		Book book = bookService.getBook(authorName, bookName);
		return book;
	}

	@DeleteMapping("/authors/{authorName}/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable String authorName, @PathVariable long id) {

		bookService.deleteBook(authorName, id);

		ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();
		return responseEntity;
	}

	@PutMapping("/authors/{authorName}/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable String authorName, @PathVariable long id,
			@RequestBody Book book) {

		book.setAuthorName(authorName);
		
		Book bookUpdated = bookService.updateBook(authorName, id, book);
		
		ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(bookUpdated, HttpStatus.OK);

		return responseEntity;
	}

	@PostMapping("/authors/{authorName}/books")
	public ResponseEntity<Void> createBook(@PathVariable String authorName, @RequestBody Book book) {
		
		book.setAuthorName(authorName);

		Book createdBook = bookService.createBook(authorName, book);
		
		if (createdBook == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdBook.getId())
				.toUri(); 

		return ResponseEntity.created(uri).build();
	}

}