package com.petertailor.belsokonyveles.controller;

import com.petertailor.belsokonyveles.domain.Bill;
import com.petertailor.belsokonyveles.domain.QueryString;
import com.petertailor.belsokonyveles.domain.StringValues;
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
        m.addAttribute("stringValues",new StringValues());
        return "addbook";
    }

    @PostMapping("/post")
    public String getPostRequest(@ModelAttribute StringValues stringValues,Model m){ // model visszafelé is szállit
        billService.saveBill(stringValues);

        //reset
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
    public String query(Model m){
        m.addAttribute("queryString",new QueryString());

        //TEST
        m.addAttribute("queriedBills",billService.findAllBill());

        return "querybook";
    }

    @PostMapping("/sentQuery")
    public String queryAnswer(@ModelAttribute QueryString qs, Model m){
        //billService.selectQuery(qs);
        m.addAttribute("queriedBills",billService.querySelector(qs));
        return "querybook";
    }

    @RequestMapping("/")
    public String main(){
        return "login";
    }

}
