package com.example.formedix.repositories;


import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RatesRepositoryBase  extends JpaRepository<Rates, Integer> {

    @Query(
            value = "SELECT rates.exchange_rate, currency.name " +
                    "FROM rates JOIN currency on rates.currency_id = currency.id " +
                    "WHERE rates.date_id = :date_id",
            nativeQuery = true
    )
    List<String[]> findRatesByDateId(@Param("date_id")int date_id) throws CustomException;

    @Query(
            value = "SELECT rates.* FROM rates WHERE rates.date_id = :date_id AND rates.currency_id = :currency_id",
            nativeQuery = true
    )
    Rates findRatesByDateCurrency(@Param("date_id")int date_id, @Param("currency_id")int currency_id) throws CustomException;


    @Query(
            value = "SELECT MAX(rates.exchange_rate) FROM rates WHERE (rates.currency_id = :currency_id) AND (rates.date_id BETWEEN :start_date_id AND  :end_date_id)",
            nativeQuery = true
    )
    double findHighestExchangeRate(@Param("start_date_id")int start_date_id, @Param("end_date_id")int end_date_id, @Param("currency_id")int currency_id) throws CustomException;


    @Query(
            value = "SELECT AVG(rates.exchange_rate) FROM rates WHERE (rates.currency_id = :currency_id) AND (rates.date_id BETWEEN :start_date_id AND  :end_date_id)",
            nativeQuery = true
    )
    double findAverageExchangeRate(@Param("start_date_id")int start_date_id, @Param("end_date_id")int end_date_id, @Param("currency_id")int currency_id) throws CustomException;





}
