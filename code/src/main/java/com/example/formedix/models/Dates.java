package com.example.formedix.models;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="dates", indexes = @Index(name="idx_dates", columnList = "dates"))
public class Dates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "dates", updatable = false, nullable = false)
    @Type(type="date")
    private Date dates;

    public Dates(Integer id, Date dates) {
        this.id = id;
        this.dates = dates;
    }

    public Dates() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}
