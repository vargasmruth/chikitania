package com.chikitania.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Ruth on 23/09/2019.
 */

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "date", nullable = false)
    public Date date;

    @Column(name = "price", nullable = false)
    public double price;

    @Column(name = "state", nullable = false)
    public boolean state;

    public Product() {
        this.date = new Date();
        this.state = true;
    }

    public Product(String name, Date date, double price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }
}
