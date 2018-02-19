package com.petertailor.belsokonyveles.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PaymentType {

    @Id
    @GeneratedValue
    private Long id;

    private String typeName;


    @OneToMany(mappedBy = "paymentType")
    private List<Bill> bills;

    public PaymentType(String typeName, List<Bill> bills) {
        this.typeName = typeName;
        this.bills = bills;
    }

    public PaymentType(String typeName) {
        this.typeName = typeName;
    }

    public PaymentType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
