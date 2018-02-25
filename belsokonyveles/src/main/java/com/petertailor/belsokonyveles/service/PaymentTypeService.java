package com.petertailor.belsokonyveles.service;


import com.petertailor.belsokonyveles.domain.PaymentType;
import com.petertailor.belsokonyveles.repository.PaymentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeService {

    private PaymentTypeRepo paymentTypeRepo;

    @Autowired
    public void setPaymentTypeRepo(PaymentTypeRepo paymentTypeRepo) {
        this.paymentTypeRepo = paymentTypeRepo;
    }

    public List<PaymentType> paymentNamesList(){
        return paymentTypeRepo.findAll();
    }
}
