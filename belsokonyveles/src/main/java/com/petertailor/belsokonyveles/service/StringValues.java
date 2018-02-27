package com.petertailor.belsokonyveles.service;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

public class StringValues {

    private String deadline = "";
    private String releaseDate = "";
    private String partner = "";
    private String voucherNumber = "";
    private String amount = "";
    private String paymentType = "";
    private String notes = "";
    private String paymant = "";

    public StringValues() {
    }

    public StringValues(String deadline, String releaseDate, String partner, String voucherNumber, String amount, String paymentType, String notes, String paymant) {
        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.partner = partner;
        this.voucherNumber = voucherNumber;
        this.amount = amount;
        this.paymentType = paymentType;
        this.notes = notes;
        this.paymant = paymant;
    }

    public StringValues(String deadline, String releaseDate, String partner, String amount, String paymentType, String paymant) {

        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.partner = partner;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymant = paymant;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymant() {
        return paymant;
    }

    public void setPaymant(String paymant) {
        this.paymant = paymant;
    }

    @Override
    public String toString() {
        return "StringValues{" +
                "deadline='" + deadline + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", partner='" + partner + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", notes='" + notes + '\'' +
                ", paymant='" + paymant + '\'' +
                '}';
    }
}
