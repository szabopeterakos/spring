package com.peter.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGeneral {

	@ExceptionHandler
	public String ExceptionHandler(Exception e,Model m) {
		m.addAttribute("exMessage", e.getMessage());
		return "exception";
	}
	
}
