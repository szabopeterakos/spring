package com.petertailor.belsokonyveles.domain;

import javax.persistence.Entity;

public enum Paymant {

    KP("KÉSZPÉNZ"), U("UTALÁS");

    private String name;

    Paymant() {
    }

    Paymant(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
