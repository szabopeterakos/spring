package com.elsoproject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elsoproject.domain.Story;
import com.elsoproject.repository.StoryRepository;
import com.elsoproject.service.StoryService;

import javassist.bytecode.Descriptor.Iterator;

@Controller
public class HomeController {
	
	private StoryService storyService;
	
	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}

	@RequestMapping("/")
	public String stories(Model model, Locale locale) {
		model.addAttribute("pageTitle", "Minden napra egy SFJ sztori!");
		model.addAttribute("stories", storyService.getStories());
		System.out.println(String.format("Request received. Language: %s, Country: %s %n", locale.getLanguage(),
				locale.getDisplayCountry()));
		return "stories";
	}


}
