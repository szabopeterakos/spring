package com.petertailor.belsokonyveles.service;


import com.petertailor.belsokonyveles.domain.Bill;
import com.petertailor.belsokonyveles.domain.Partner;
import com.petertailor.belsokonyveles.domain.Paymant;
import com.petertailor.belsokonyveles.domain.PaymentType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BillServiceTest {

    BillService billService = new BillService();
    List<Bill> billList = new ArrayList<>();

    @Before
    public void init() {
        Partner partner1 = new Partner("TESLA");
        billList.add(new Bill(new Date(),new Date(),partner1,"KKHH32",300L, new PaymentType("PT"),"notes", Paymant.KP,new Date(),"USERNAME"));
        billList.add(new Bill(new Date(),new Date(),partner1,"KKHH32",300L, new PaymentType("PT"),"notes", Paymant.KP,new Date(),"USERNAME"));
    }

    @Test
    public void testBillwithNull() {
        billService.sumBillsAmount(new ArrayList<>());
    }

    @Test
    public void testSum() {
        Long sum = billService.sumBillsAmount(billList);
        assertEquals(sum,new Long(600));
    }

}
