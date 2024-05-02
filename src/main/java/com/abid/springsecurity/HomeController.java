package com.abid.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	 @RequestMapping("/")
	 public String getFirstPage()
	 {
		 return "index.jsp";
	 }
	 
	 

}
