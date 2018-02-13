package com.secodProject.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secondProject.domain.Article;

@Controller
public class SimplyController {

	@RequestMapping("/")
	public String simply(Model m) {
		m.addAttribute("pageTitle", "simply.mn");
		//m.addAttribute("articles", fill());
		return "simply";
	}

	
	
}
