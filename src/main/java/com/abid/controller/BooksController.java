package com.abid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.abid.dto.Book;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BooksController {

	@GetMapping("/getBooks")
	public List<Book> getBooks(@RequestParam String param) 
	{
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Spring Boot in Action","Abid Zia Khan"));
		books.add(new Book("Our Lagacy in Action","Muhammad Mateen"));
		return books;
	}	
}
