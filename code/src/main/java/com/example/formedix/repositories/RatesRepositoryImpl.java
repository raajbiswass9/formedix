package com.example.formedix.repositories;


import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RatesRepositoryImpl implements RatesRepository{

    private final RatesRepositoryBase ratesRepositoryBase;

    public RatesRepositoryImpl(RatesRepositoryBase ratesRepositoryBase) {
        this.ratesRepositoryBase = ratesRepositoryBase;
    }

    @Override
    public List<Rates> getRatesByDate(Integer date_id) throws CustomException {
        try{
            List<Rates> result = ratesRepositoryBase.findRatesByDateId(date_id);
            if(!result.isEmpty()){
                return result;
            }else{
                throw new CustomException("Data not found.");
            }
        }catch(Exception e){
//            throw new CustomException(e.getMessage());
            throw new CustomException("Data not found.");
        }
    }

    @Override
    public Rates getRateByDateAndCurrency(Integer date_id, Integer currency_id) throws CustomException {
        try{
            Rates result = ratesRepositoryBase.findRatesByDateCurrency(date_id,currency_id);
            if(result != null){
                return result;
            }else{
                throw new CustomException("Exchange rate not found for the currency.");
            }
        }catch(Exception e){
            throw new CustomException("Exchange rate not found for the currency.");
        }
    }
}
