package com.example.CashOnline.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Double Total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    public Loans(){}

    public long getId() {
        return id;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Loans(Double total, User userId) {
        Total = total;
        this.userId = userId;
    }
}
