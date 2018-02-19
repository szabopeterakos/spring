package com.petertailor.belsokonyveles.service;

import com.petertailor.belsokonyveles.domain.*;
import com.petertailor.belsokonyveles.repository.BillRepo;
import com.petertailor.belsokonyveles.repository.PartnerRepo;
import com.petertailor.belsokonyveles.repository.PaymentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Service
public class BillService {

    private BillRepo billRepo;
    private PartnerRepo partnerRepo;
    private PaymentTypeRepo paymentTypeRepo;

    @Autowired
    public void setBillRepo(BillRepo billRepo) {
        this.billRepo = billRepo;
    }

    @Autowired
    public void setPartnerRepo(PartnerRepo partnerRepo) {
        this.partnerRepo = partnerRepo;
    }

    @Autowired
    public void setPaymentTypeRepo(PaymentTypeRepo paymentTypeRepo) {
        this.paymentTypeRepo = paymentTypeRepo;
    }

    public Iterable<Bill> findAllBill() {
        return billRepo.findAll();
    }

    public Iterable<Bill> findLast3Bill() {
        return billRepo.findTop3ByOrderByIdDesc();
    }

    private Bill addPartner(Bill b, String parnerName) {
        if (parnerName.length() < 1) {
            throw new IllegalArgumentException("The Partner name must be greater than 1 caracter : " + parnerName);
        }

        parnerName = parnerName.trim();

        Bill c = b;
        if (partnerRepo.findByName(parnerName) == null) {
            Partner notExistJet = new Partner(parnerName.toUpperCase());
            partnerRepo.save(notExistJet);
            c.setPartner(notExistJet);
        } else {
            c.setPartner(partnerRepo.findByName(parnerName.toUpperCase()));
        }
        return c;
    }

    private Bill addPaymentType(Bill b, String paymentTypeName) {

        Bill c = b;

        paymentTypeName = paymentTypeName.trim();

        if (paymentTypeRepo.findByTypeName(paymentTypeName.toUpperCase()) == null) {
            PaymentType notExistJet = new PaymentType(paymentTypeName.toUpperCase());
            paymentTypeRepo.save(notExistJet);
            c.setPaymentType(notExistJet);
        } else {
            c.setPaymentType(paymentTypeRepo.findByTypeName(paymentTypeName.toUpperCase()));
        }

        return c;
    }

    public Bill saveBill(StringValues b) {
        Bill c = new Bill();

        addPaymentType(c, b.getPaymentType()); // if exist find that, if not create one, if name "" throw Exception THE PAYMENT IS UPPERCASE
        addPartner(c, b.getPartner()); // if exist find that, if not create one, if name "" throw Exception IT IS UPPERCASE AND TRIMMED
        c.setDeadline(b.getDeadline()); // ok
        c.setNotes(b.getNotes()); // null is allowanced
        c.setAmount(Long.parseLong(b.getAmount()));
        c.setVoucherNumber(b.getVoucherNumber());
        c.setPaymant(b.getPaymant()); // ok for u U kp KP utalás UTALÁS kézpénz KÉZPÉNZ
        c.setReleaseDate(b.getReleaseDate()); // ok

        // hidden values
        c.setLastModify(new Date()); // ok
        c.setUser("Gabi");

        return billRepo.save(c);
    }

    public Iterable<Bill> querySelector(QueryString qs) {
        if (qs.getTypeQuery().length() == 0 && qs.getParterQuery().length() == 0 && qs.getDateQuery().length() == 0) {
            //no query silt everithing
            return billRepo.findAll();
        } else if (qs.getTypeQuery().length() != 0 && qs.getParterQuery().length() == 0 && qs.getDateQuery().length() == 0) {
            // just type query
            return billRepo.findAllByPaymentTypeTypeName(qs.getTypeQuery().toUpperCase());
        } else if (qs.getTypeQuery().length() == 0 && qs.getParterQuery().length() != 0 && qs.getDateQuery().length() == 0) {
            //just partner query
            return billRepo.findAllByPartnerName(qs.getParterQuery().toUpperCase());
        } else if (qs.getTypeQuery().length() == 0 && qs.getParterQuery().length() == 0 && qs.getDateQuery().length() != 0) {
            //just date query
            Date[] dates = DateIntervalValidator.intervallCreator(qs.getDateQuery());
            return billRepo.findAllByReleaseDateBetween(dates[0], dates[1]);
        } else {
            return null;
        }
    }

}
