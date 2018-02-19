package com.petertailor.belsokonyveles.controller;

import com.petertailor.belsokonyveles.domain.Bill;
import com.petertailor.belsokonyveles.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private BillService billService;

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping("/add")
    public String add(Model m){
        m.addAttribute("bills",billService.findLast3Bill());
        // add empty pojo
        m.addAttribute("bill",new Bill());
        return "addbook";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/query")
    public String query(){
        return "querybook";
    }

    @RequestMapping("/")
    public String main(){
        return "login";
    }

    @PostMapping("/post")
    public String getPostRequest(@ModelAttribute Bill bill,Model m){ // model visszafelé is szállit
        billService.saveBill(bill);

        //reset
        m.addAttribute("bills",billService.findLast3Bill());
        // add empty pojo
        m.addAttribute("bill",new Bill());
        return "addbook";
    }

}
