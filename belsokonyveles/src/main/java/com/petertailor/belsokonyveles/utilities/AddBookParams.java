package com.petertailor.belsokonyveles.utilities;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

public class AddBookParams {

    private String deadline = "";
    private String releaseDate = "";
    private String partner = "";
    private String voucherNumber = "";
    private String amount = "";
    private String paymentType = "";
    private String notes = "";
    private String payment = "";

    public AddBookParams() {
    }

    public AddBookParams(String deadline, String releaseDate, String partner, String voucherNumber, String amount, String paymentType, String notes, String paymant) {
        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.partner = partner;
        this.voucherNumber = voucherNumber;
        this.amount = amount;
        this.paymentType = paymentType;
        this.notes = notes;
        this.payment = paymant;
    }

    public AddBookParams(String deadline, String releaseDate, String partner, String amount, String paymentType, String paymant) {

        this.deadline = deadline;
        this.releaseDate = releaseDate;
        this.partner = partner;
        this.amount = amount;
        this.paymentType = paymentType;
        this.payment = paymant;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String paymant) {
        this.payment = paymant;
    }

    @Override
    public String toString() {
        return "AddBookParams{" +
                "deadline='" + deadline + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", partner='" + partner + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", notes='" + notes + '\'' +
                ", paymant='" + payment + '\'' +
                '}';
    }
}
