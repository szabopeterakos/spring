package com.secodProject.simplyProject.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyAdminCntroller {
	
	@RequestMapping("/admin/{id}")
	public String admin(@PathVariable(value="id") String id) { // have to indicate this is the id from path of requestMapping
		if(!id.equals("MesterChiefWizardBossAdmin")) {
			throw new IllegalArgumentException("YOU ARE NOT MY MASTER GET OUT");
		}
		
		return "admin";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request, Exception e, Model m) {
		m.addAttribute("exceptionMessage", e.getMessage());
		m.addAttribute("exceptionTime", new Date().toString());
		return "exceptions"; 
	}
	
}
