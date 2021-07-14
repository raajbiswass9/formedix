package com.example.formedix.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="rates", indexes = @Index(name="idx_date_currency", columnList = "date_id, currency_id"))
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "date_id", updatable = false, nullable = false)
    private Integer date_id;

    @Column(name = "currency_id", updatable = false, nullable = false)
    private Integer currency_id;

    @Column(name = "exchange_rate", updatable = false, nullable = true)
    private float exchange_rate;

    public Rates(Integer id, Integer date_id, Integer currency_id, float exchange_rate) {
        this.id = id;
        this.date_id = date_id;
        this.currency_id = currency_id;
        this.exchange_rate = exchange_rate;
    }

    public Rates() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDate_id() {
        return date_id;
    }

    public void setDate_id(Integer date_id) {
        this.date_id = date_id;
    }

    public Integer getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Integer currency_id) {
        this.currency_id = currency_id;
    }

    public float getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(float exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
