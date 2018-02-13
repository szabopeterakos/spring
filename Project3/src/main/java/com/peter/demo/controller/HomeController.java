package com.peter.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peter.demo.service.StoryService;

@Controller
public class HomeController { // várja a requestet
	
	private StoryService myStoryService;// 3. amit a service fog tartalmazni
	@Autowired
	public void setMyStoryService(StoryService myStoryService) { // 4. ezt beinjektáljuk de csak egyet, singleton 
		this.myStoryService = myStoryService;
	}

	//home
	@RequestMapping("/") // 1. elkapja az egyik kérést
	public String index(Model m){
		m.addAttribute("time", LocalDateTime.now());
		m.addAttribute("stories", myStoryService.getStories()); // 2. visszajuttatja a választ a modellen keresztül
		
		return "home";
	}
	
	//inner chriteria
	@RequestMapping("/mainStory") // 1. elkapja az egyik kérést
	public String story(Model m){
		m.addAttribute("time", LocalDateTime.now());
		m.addAttribute("mainStory", myStoryService.getStory()); // 2. visszajuttatja a választ a modellen keresztül
		
		return "mainStory"; 
	}
	
	//id search outer chriteria
	@RequestMapping("/id/{id}")
	public String searchByStoryId(@PathVariable(value="id") String id, Model m) {
		if(id == null) {
			throw new IllegalArgumentException("Nincs ilyen user");
		}
		
		m.addAttribute("mainStory", myStoryService.getbyID(id));
		
		return "findStory";
	}
	
	//exception elkapás lekezelés ha nem ExceptionGenerallal oldom meg
	
//	@ExceptionHandler(IllegalArgumentException.class)
//	public String ExceptionHandler(HttpServletRequest ra, Exception e,Model m) {
//		m.addAttribute("exMessage", e.getMessage());
//		return "exception";
//	}
	


	
	
	
}
