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
                throw new CustomException("No data found.");
            }
        }catch(Exception e){
//            throw new CustomException(e.getMessage());
            throw new CustomException("No data found.");
        }

    }
}
