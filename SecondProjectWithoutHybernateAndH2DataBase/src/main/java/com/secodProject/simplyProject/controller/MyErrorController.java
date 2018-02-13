package com.secodProject.simplyProject.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class MyErrorController implements ErrorController{
	
	private final String ERR_PATH = "/error";
	
	private ErrorAttributes errorAttribs;
	
	@Autowired
	public void setErrorAttributes(ErrorAttributes ea) {
		this.errorAttribs = ea;
	}
	
	
	@RequestMapping(ERR_PATH)
	public String error(Model m, HttpServletRequest httpsr) { // get httprequest
		RequestAttributes ra = new ServletRequestAttributes(httpsr); // just attributes but every attributes
		Map<String, Object> map = this.errorAttribs.getErrorAttributes(ra, true); // just error attribs , with stack trace
		
		
		m.addAttribute("path", map.get("path"));
		m.addAttribute("time",map.get("timestamp"));
		m.addAttribute("error",map.get("error"));
		m.addAttribute("message",map.get("message"));
		m.addAttribute("status",map.get("status"));
		
		return "error";
	}

	@Override
	public String getErrorPath() {
		return ERR_PATH;
	}
	
	

}
