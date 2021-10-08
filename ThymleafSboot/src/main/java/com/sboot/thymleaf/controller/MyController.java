package com.sboot.thymleaf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
 @RequestMapping(value="/about",method = RequestMethod.GET)	
 public String about(Model model) {
	System.out.println("Inside about");
	model.addAttribute("name","Ajay M Ingle");
	 List<String> names = List.of("Ajay","Vijay","Sonali");
	 model.addAttribute("i", names); 
	return "about";
 }
 
@GetMapping("/service") 
 public String service() {
	 return "service";
 }

@RequestMapping("/index")
public String ajay() {
	System.out.println("I ama here ");
	return "index";
}

}


