package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;

import java.util.List;

public interface CurrencyRepository {
    Integer getCurrencyId(String currency_name) throws CustomException;

    List<String> getAllCurrencies() throws CustomException;
}
