package com.chikitania.api;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ruth on 23/09/2019.
 */

@Entity
@Table(name = "event_stock")
public class EventStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product product;

    @Column(name = "quantity", nullable = false)
    public Integer quantity;

    @Column(name = "balance", nullable = false)
    public Integer balance;

    @Column(name = "type", nullable = false)
    public Integer type;

    @Column(name = "date", nullable = false)
    public Date date;

    public EventStock() {
    }

    public EventStock(Product product, Integer quantity, Integer balance, Integer type, Date date) {
        this.product = product;
        this.quantity = quantity;
        this.balance = balance;
        this.type = type;
        this.date = date;
    }
}
