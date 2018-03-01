package com.petertailor.belsokonyveles.service;

import com.petertailor.belsokonyveles.domain.*;
import com.petertailor.belsokonyveles.repository.BillRepo;
import com.petertailor.belsokonyveles.repository.PartnerRepo;
import com.petertailor.belsokonyveles.repository.PaymentTypeRepo;
import com.petertailor.belsokonyveles.utilities.AddBookParams;
import com.petertailor.belsokonyveles.utilities.DateIntervalValidator;
import com.petertailor.belsokonyveles.utilities.QueryString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Bill> querySelector(QueryString qs) {
        Date[] dates = null;

        String type = qs.getTypeQuery().toUpperCase();
        String partner = qs.getParterQuery().toUpperCase();
        String dateString = qs.getDateQuery();

        // date string validation against parse exception
        if (dateString.length() != 0)
            dates = DateIntervalValidator.intervallCreator(dateString);
        int queryTypeBits = 0;
        //0 - query all,
        //100 - type,
        //10 - partner,
        //1 - date,
        //110 - type and partner,
        //11 - partner and date,
        //101 - type and date
        //111 - type and partner and date

        if (type.length() != 0) {
            queryTypeBits += 100;
        }
        if (partner.length() != 0) {
            queryTypeBits += 10;
        }
        if (dateString.length() != 0) {
            queryTypeBits += 1;
        }

        if (queryTypeBits == 0) { //0 - query all
            return billRepo.findAll();
        } else if (queryTypeBits == 100) { //100 - type
            return billRepo.findAllByPaymentTypeTypeName(type);
        } else if (queryTypeBits == 10) { //10 - partner
            return billRepo.findAllByPartnerName(partner);
        } else if (queryTypeBits == 1) { //1 - date
            return billRepo.findAllByReleaseDateBetween(dates[0], dates[1]);
        } else if (queryTypeBits == 110) { //110 - type and partner
            return billRepo.findAllByPaymentTypeTypeNameAndPartnerName(type, partner);
        } else if (queryTypeBits == 11) { //11 - partner and date
            return billRepo.findAllByPartnerNameAndReleaseDateBetween(partner, dates[0], dates[1]);
        } else if (queryTypeBits == 101) { //101 - type and date
            return billRepo.findAllByPaymentTypeTypeNameAndReleaseDateBetween(type, dates[0], dates[1]);
        } else if (queryTypeBits == 111) { //111 - type and partner and date
            return billRepo.findAllByPaymentTypeTypeNameAndPartnerNameAndReleaseDateBetween(type, partner, dates[0], dates[1]);
        } else {
            throw new IllegalArgumentException("rosszul számoltam ki a lehetőségeket 😞");
        }
    }

    private Bill addPartner(Bill b, String parnerName) {
        if (parnerName.length() < 2) {
            throw new IllegalArgumentException("A Partner megnevezés esetben legalább 2 karakter szükséges: " + parnerName);
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
        if (paymentTypeName.trim().length() < 2) {
            throw new IllegalArgumentException("A jelleg nincs kiválasztva, a jellegnek 1 karakternél hosszabbnak kell lennie");
        }

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

    public Bill saveBill(AddBookParams b) {
        Bill c = new Bill();

        addPaymentType(c, b.getPaymentType()); // if exist find that, if not create one, if name "" throw Exception THE PAYMENT IS UPPERCASE
        addPartner(c, b.getPartner()); // if exist find that, if not create one, if name "" throw Exception IT IS UPPERCASE AND TRIMMED
        c.setDeadline(b.getDeadline()); // ok
        c.setNotes(b.getNotes()); // null is allowanced


        // amount checking
        if (b.getAmount().trim().length() < 2) {
            throw new IllegalArgumentException("Az összeget nem lehet üresen hagyni, illetve csak számot tartalmazhat");
        }
        try {
            Long.parseLong(b.getAmount());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Az összeg csak számokat tartalmazhat");
        }

        c.setAmount(Long.parseLong(b.getAmount()));
        c.setVoucherNumber(b.getVoucherNumber());
        c.setPaymant(b.getPayment()); // ok for u U kp KP utalás UTALÁS kézpénz KÉZPÉNZ
        c.setReleaseDate(b.getReleaseDate()); // ok

        // hidden values - modification date
        c.setLastModify(new Date()); // ok
        // hidden value - modifier
        c.setUser("username");

        return billRepo.save(c);
    }


    public Long sumBillsAmount(Iterable<Bill> bills) {
        Long sum = 0L;

        for (Bill c : bills) {
            sum += c.getAmount();
        }

        return sum;
    }

}
