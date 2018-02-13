package com.secodProject.simplyProject.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.secondProject.simplyProject.articles.Article;

@Controller
public class SimplyController {

	@RequestMapping("/")
	public String simply(Model m) {
		m.addAttribute("pageTitle", "simply.mn");
		m.addAttribute("articles", getArticles());
		return "simply";
	}

	
	//initialize
	private ArrayList<Article> getArticles(){
		ArrayList<Article> current = new ArrayList<>();
		
		Article a1 = new Article("John Snow is alive?",new Date().toString(),"it is just a face content","Tom Denem"); //(String title, String date, String content, String author)
		Article a2 = new Article("IronMan",new Date().toString(),"it is just a face content","Tom Denem");
		Article a3 = new Article("Lord Of the Rings",new Date().toString(),"<p>it is just a <b>face</b> content</p>","Tom Denem");
		
		current.add(a1);
		current.add(a2);
		current.add(a3);
		
		return current;
	}
	
	
}
