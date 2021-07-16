package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CurrencyRepositoryBase   extends JpaRepository<Currency, Integer> {

    @Query(value = "SELECT * FROM currency WHERE name = ?1 ", nativeQuery = true)
    Currency findCurrencyIdByName(String name) throws CustomException;
}
