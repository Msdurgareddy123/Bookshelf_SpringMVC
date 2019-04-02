package com.vedantu.controllers;

import com.vedantu.daos.BookMongoDAO;
import com.vedantu.models.BookMongo;
import com.vedantu.requests.BookReq;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping("book")
public class Book {

  

	@Autowired
	private BookMongoDAO bookMongoDAO;

	// Add new Books details..

	@RequestMapping(value = "/bookadd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addbooks(@RequestBody BookReq bookrequest) throws Exception {
	
		if(bookrequest.getTitle()!= null && bookrequest.getAuthor()!= null && bookrequest.getImage()!= null && bookrequest.getShelf()!= null ) {
			
			BookMongo add_book = new BookMongo();
			add_book.setTitle(bookrequest.getTitle());
			add_book.setAuthor(bookrequest.getAuthor());
			add_book.setImage(bookrequest.getImage());
			add_book.setShelf(bookrequest.getShelf());
			bookMongoDAO.create(add_book);
			return "Successfully updated"+add_book;
		}
		else {
			return "please fill the book details";
		}
	}
	
	// Book details updating...
	
	@RequestMapping(value = "/bookupdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String UpdateBoosks(@RequestBody BookReq bookrequest) throws Exception {
		
		
		BookMongo update_book = bookMongoDAO.getById(bookrequest.getId());
		
		if(update_book != null) {
			update_book.setTitle(bookrequest.getTitle());
			update_book.setAuthor(bookrequest.getAuthor());
			update_book.setImage(bookrequest.getImage());
			update_book.setShelf(bookrequest.getShelf());
			bookMongoDAO.create(update_book);
			return "Successfully updated"+update_book;
		}
		else {
			return "Please check id once";
		}
		
	}

	//Getting all book details...
	
	@RequestMapping(value = "/getBooks", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<BookMongo> getParam() throws Exception {
	Collection<BookMongo> book_info = bookMongoDAO.getAll();
	return book_info;
	}
	
	
	//Deleting books details...
	
	@RequestMapping(value = "/bookdelete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteParam(@RequestBody BookReq param) throws Exception {

		BookMongo e2 = bookMongoDAO.getById(param.getId());
		
		if(e2 != null) {
			bookMongoDAO.delete(e2, null);
	
			return "Successfully deleted";
		}
		else {
			return "Please Check Book id once";
		}
	}
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public String getParamById() throws Exception {
		return "RESPONSE";
	}
}
