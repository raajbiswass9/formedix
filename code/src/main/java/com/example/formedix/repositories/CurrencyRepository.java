package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;

public interface CurrencyRepository {
    Integer getCurrencyId(String currency_name) throws CustomException;
}
