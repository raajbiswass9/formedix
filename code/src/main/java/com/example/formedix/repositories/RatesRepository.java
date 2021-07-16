package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;

import java.util.Date;
import java.util.List;

public interface RatesRepository {

    List<Rates> getRatesByDate(Integer date_id) throws CustomException;

    Rates getRateByDateAndCurrency(Integer date_id, Integer currency_id) throws CustomException;

    double getExchangeRateValue(Integer start_date_id, Integer end_date_id, Integer currency_id, String exchange_rate_type) throws CustomException;


}
