package com.petertailor.belsokonyveles.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Partner {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "partner")
    private List<Bill> billList;

    public Partner(String name) {
        this.name = name;
    }

    public Partner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " "+ name;
    }
}

