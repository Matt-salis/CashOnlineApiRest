package com.example.CashOnline.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paging {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int page;

    private int size;

    private int total;

    public Paging(int page, int size, int total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }
}
