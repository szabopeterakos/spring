package com.petertailor.belsokonyveles.controller;

import com.petertailor.belsokonyveles.domain.Bill;
import com.petertailor.belsokonyveles.service.*;
import com.petertailor.belsokonyveles.utilities.AddBookParams;
import com.petertailor.belsokonyveles.utilities.ConvertToExcel;
import com.petertailor.belsokonyveles.utilities.QueryString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    private BillService billService;
    private PaymentTypeService paymentTypeService;
    private PartnerService partnerService;
    private ConvertToExcel convertToExcel;
    private Iterable<Bill> currentBillList;

    @Autowired
    public void setPartnerService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @Autowired
    public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @Autowired
    public void setConvertToExcel(ConvertToExcel convertToExcel) {
        this.convertToExcel = convertToExcel;
    }

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping("/add")
    public String add(Model m) {
        m.addAttribute("bills", billService.findLast3Bill());
        // add empty pojo
        m.addAttribute("addBookParams", new AddBookParams());

        //start select.option
        m.addAttribute("partners",partnerService.partnerList());
        m.addAttribute("types", paymentTypeService.paymentNamesList());


        return "addbook";
    }

    @PostMapping("/post")
    public String getPostRequest(@ModelAttribute AddBookParams addBookParams, Model m) {
        billService.saveBill(addBookParams);

        //start select.option
        m.addAttribute("partners",partnerService.partnerList());
        m.addAttribute("types", paymentTypeService.paymentNamesList());

        //reset
        m.addAttribute("bills", billService.findLast3Bill());
        // add empty pojo
        m.addAttribute("bill", new Bill());
        return "addbook";
    }

    @RequestMapping("/login")
    public String login() {
        return "alogin";
    }

    @RequestMapping("/query")
    public String query(Model m) {
        // new object inject
        m.addAttribute("queryString", new QueryString());

        //start
        m.addAttribute("queriedBills", billService.findLast3Bill());
        //start select.option
        m.addAttribute("types", paymentTypeService.paymentNamesList());
        m.addAttribute("partners",partnerService.partnerList());

        // current query saving
        currentBillList = billService.findAllBill();

        return "querybook";
    }

    @PostMapping("/sentQuery")
    public String queryAnswer(@ModelAttribute QueryString qs, Model m) {
        //billService.selectQuery(qs);
        m.addAttribute("queriedBills", billService.querySelector(qs));

        //current query save
        currentBillList = billService.querySelector(qs);

        //current sum
        Long currentSum = billService.sumBillsAmount(currentBillList);

        //start select.option
        m.addAttribute("types", paymentTypeService.paymentNamesList());
        m.addAttribute("partners",partnerService.partnerList());
        m.addAttribute("amountSum",currentSum+" Ft");

        return "querybook";
    }

    @RequestMapping(value = "downloadFile", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws IOException {
        convertToExcel.convertToExcel(currentBillList);

        response.setContentType("application/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=" + new SimpleDateFormat("yyMMdd-hhmm-").format(new Date()) + "bookShelf" + ".xlsx");
        InputStream inputStream = new FileInputStream(new File("src/main/resources/bookShelf.xlsx"));
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }

    @RequestMapping("/")
    public String main() {
        return "alogin";
    }

}
