package com.example.formedix.models;

import javax.persistence.*;


@Entity
@Table(name="currency", indexes = @Index(name="idx_name", columnList = "name"))
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    public Currency(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Currency() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
