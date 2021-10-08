package com.sboot.book.restapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sboot.book.restapi.dao.BookRepository;
import com.sboot.book.restapi.model.Books;

@Service
public class BookServices {
	
	@Autowired
	private BookRepository bookRepository;
//	private static List<Books> list = new ArrayList<>();
//	static {
//		list.add(new Books(1,"ajaja","djfjjf"));
//		list.add(new Books(2,"qqqqq","aaaaaa"));
//		list.add(new Books(3,"wwwww","ooiiit"));
//		list.add(new Books(4,"rrrrr","fhjgjgh"));
//	}
	
	// get all books
	public List<Books> getallBooks(){
		List<Books> list = (List<Books>)this.bookRepository.findAll();
		return list;
	}
	
	// get single book
	
	public Books getBookById(int id) {
		Books book = null;
		try {
		// book = list.stream().filter(e->e.getBookid() == id).findFirst().get();
			book = this.bookRepository.findById(id);}
		catch (Exception e){
			e.printStackTrace();
		}
		return book;
	}
	// add books
	@Transactional
	public Books addbook(Books b) {
		Books res = bookRepository.save(b);
		// list.add(b);
		return res;
	}
	
	@Transactional
	public void deletebook(int bookid) {
		// TODO Auto-generated method stub
//		list = list.stream().filter(book -> 
//			book.getBookid()!= bookid).collect(Collectors.toList());
		
		bookRepository.deleteById(bookid);
	}

	@Transactional
	public void updatebooks(Books book, int bookid) {
		// TODO Auto-generated method stub
//		list = list.stream().map(b->{
//			if(b.getBookid() == bookid)
//				{b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());}
//			return b;
//		}).collect(Collectors.toList());
		book.setBookid(bookid);
		bookRepository.save(book);
	}
	
	
}
