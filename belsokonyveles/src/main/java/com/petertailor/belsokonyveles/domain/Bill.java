package com.petertailor.belsokonyveles.domain;

import com.petertailor.belsokonyveles.service.DateCreater;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bill {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "DATE")
    private Date deadline;

    @Column(columnDefinition = "DATE")
    private Date releaseDate;

    @ManyToOne
    private Partner partner;
    private String voucherNumber;
    private long amount;

    @ManyToOne
    private PaymentType paymentType;
    private String notes;

    @Column
    private Paymant paymant;

    @Column(columnDefinition = "DATE")
    private Date lastModify;
    private String user;

    public Bill() {
    }

    public Bill(String voucherNumber, long amount) {
        this.voucherNumber = voucherNumber;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDeadline(String deadline) {
        Date c = null;
        try {
            c = DateCreater.dateParser(deadline);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A dátum formátuma nem megfelelő");
        }
        this.deadline = c;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        Date c = null;
        try {
            c = DateCreater.dateParser(releaseDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("A dátum formátuma nem megfelelő");
        }
        this.releaseDate = c;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Paymant getPaymant() {
        return paymant;
    }

    public void setPaymant(Paymant paymant) {
        this.paymant = paymant;
    }

    public void setPaymant(String paymant) {
        for (Paymant c : Paymant.values()) {
            if (c.getName().equals(paymant.toUpperCase())) {
                this.paymant = c;
            }
        }
        if (paymant.toLowerCase().equals("u")) {
            this.paymant = Paymant.U;
        } else if (paymant.toLowerCase().equals("kp")) {
            this.paymant = Paymant.KP;
        } else {
            throw new IllegalArgumentException("this value is not allowanced : " + paymant);
        }
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Bill(Date deadline, Date releaseDate, Partner partner, String voucherNumber, long amount, PaymentType paymentType, String notes, Paymant paymant, Date lastModify, String user) {

        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.partner = partner;
        this.voucherNumber = voucherNumber;
        this.amount = amount;
        this.paymentType = paymentType;
        this.notes = notes;
        this.paymant = paymant;
        this.lastModify = lastModify;
        this.user = user;
    }
}
