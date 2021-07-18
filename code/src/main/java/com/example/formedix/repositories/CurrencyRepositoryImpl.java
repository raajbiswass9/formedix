package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Currency;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRepositoryImpl implements  CurrencyRepository{


    private final CurrencyRepositoryBase currencyRepositoryBase;

    public CurrencyRepositoryImpl(CurrencyRepositoryBase currencyRepositoryBase) {
        this.currencyRepositoryBase = currencyRepositoryBase;
    }


    /**
     * Get id of a currency by their name
     * @param currency_name
     * @return result(id)
     * @throws CustomException
     */
    @Override
    public Integer getCurrencyId(String currency_name) throws CustomException {
        Currency result = currencyRepositoryBase.findCurrencyIdByName(currency_name);
        if(result != null){
            return result.getId();
        }else{
            throw new CustomException("Currency name not found.");
        }
    }


    /**
     * Get all currency names from database
     * @return result(currency name list)
     * @throws CustomException
     */
    @Override
    public List<String> getAllCurrencies() throws CustomException {
        List<String> result = currencyRepositoryBase.findAllCurrencies();
        if(result != null){
            return result;
        }else{
            throw new CustomException("Currencies not found.");
        }
    }
}
