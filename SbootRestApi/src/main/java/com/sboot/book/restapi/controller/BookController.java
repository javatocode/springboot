package com.sboot.book.restapi.controller;
import java.util.List;
import java.util.Optional;

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

import com.sboot.book.restapi.model.Books;
import com.sboot.book.restapi.services.BookServices;

@RestController
public class BookController {
	
	@Autowired
	private BookServices bookServices;
	
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getBooks() {
		
		List<Books> list = bookServices.getallBooks();
		if(list.size() <=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Books> getBook(@PathVariable("id") int bookid) {
		Books book = bookServices.getBookById(bookid);
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/postbooks")
	public ResponseEntity<Books> addbook(@RequestBody Books book) {
		Books b = null;
		try {
		 b = this.bookServices.addbook(book);
		 return ResponseEntity.of(Optional.of(b));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	// delete book
		@DeleteMapping("/books/{bookid}")
		public ResponseEntity<Void> deleteBook(@PathVariable("bookid") int bookid) {
			try {
				this.bookServices.deletebook(bookid);
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

			}
			
		}
		
		// update book
		@PutMapping("/books/{bookid}")
		// to convert json to string...here
		public ResponseEntity<Books> updatebooks(@RequestBody Books book , @PathVariable("bookid") int bookid) {
			try {
			this.bookServices.updatebooks(book , bookid);
			return ResponseEntity.ok().body(book);
			}
			catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
}
		}
}
