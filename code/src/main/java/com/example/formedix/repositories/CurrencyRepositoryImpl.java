package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Currency;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyRepositoryImpl implements  CurrencyRepository{


    private final CurrencyRepositoryBase currencyRepositoryBase;

    public CurrencyRepositoryImpl(CurrencyRepositoryBase currencyRepositoryBase) {
        this.currencyRepositoryBase = currencyRepositoryBase;
    }



    @Override
    public Integer getCurrencyId(String currency_name) throws CustomException {
        Currency result = currencyRepositoryBase.findCurrencyIdByName(currency_name);
        if(result != null){
            return result.getId();
        }else{
            throw new CustomException("Currency name not found.");
        }
//        return null;
    }
}
