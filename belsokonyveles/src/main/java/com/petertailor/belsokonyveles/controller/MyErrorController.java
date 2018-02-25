package com.petertailor.belsokonyveles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MyErrorController implements ErrorController{

    private final String ERR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes; // error attributes

    @RequestMapping(ERR_PATH)
    public String error(Model m, HttpServletRequest req){ // all request
        RequestAttributes reqAttrib =  new ServletRequestAttributes(req); // all request attributes
        Map<String,Object> kvError = this.errorAttributes.getErrorAttributes(reqAttrib,true); // just error request attrib, and add stack trace
        m.addAttribute("eTimeStamp",kvError.get("timestamp"));
        m.addAttribute("eStatus",status(kvError.get("status")));
        m.addAttribute("ePath",kvError.get("path"));
        m.addAttribute("eError",kvError.get("error"));
        m.addAttribute("eMessage",kvError.get("message"));
        return "error";
    }

    private String status(Object status){
        String statusCode = status.toString();
        switch (statusCode){
            case "404":
                return "A keresett oldal nem található";
            default:
                return statusCode;
        }
    }

    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }

}
