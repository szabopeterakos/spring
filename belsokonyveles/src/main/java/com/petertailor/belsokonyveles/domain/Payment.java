package com.petertailor.belsokonyveles.domain;

public enum Payment {

    KP("KÉSZPÉNZ"), U("UTALÁS");

    private String name;

    Payment() {
    }

    Payment(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
