package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RatesRepositoryImpl implements RatesRepository{

    private final RatesRepositoryBase ratesRepositoryBase;

    public RatesRepositoryImpl(RatesRepositoryBase ratesRepositoryBase) {
        this.ratesRepositoryBase = ratesRepositoryBase;
    }

    /**
     * Get exchange rates of all the currencies for a given date(date_id)
     * @param date_id
     * @return result(currency name and their exchange rates)
     * @throws CustomException
     */
    @Override
    public List<String[]> getRatesByDate(Integer date_id) throws CustomException {
        try{
            List<String[]> result = ratesRepositoryBase.findRatesByDateId(date_id);
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

    /**
     * Get exchange_rate value by combination of date_id and currency_id
     * @param date_id
     * @param currency_id
     * @return result(exchange_rate)
     * @throws CustomException
     */
    @Override
    public double getRateByDateAndCurrency(Integer date_id, Integer currency_id) throws CustomException {
        try{
            Rates result = ratesRepositoryBase.findRatesByDateCurrency(date_id,currency_id);
            if(result != null){
                return result.getExchange_rate();
            }else{
                throw new CustomException("Exchange rate not found for the currency.");
            }
        }catch(Exception e){
            throw new CustomException("Exchange rate not found for the currency.");
        }
    }

    /**
     * Get exhange_rate(highest/average) value for a given timeframe and currency_id
     * @param start_date_id
     * @param end_date_id
     * @param currency_id
     * @param exchange_rate_type
     * @return result(exchange_rate(highest/average))
     * @throws CustomException
     */
    @Override
    public double getExchangeRateValue(Integer start_date_id, Integer end_date_id, Integer currency_id, String exchange_rate_type) throws CustomException {
        try{
            double result;
            if(exchange_rate_type == "highest"){
                result = ratesRepositoryBase.findHighestExchangeRate(start_date_id,end_date_id,currency_id);
            }else{
                result = ratesRepositoryBase.findAverageExchangeRate(start_date_id,end_date_id,currency_id);
            }

            if(result > 0){
                return result;
            }else{
                throw new CustomException("Exchange rate not found for the currency.");
            }
        }catch(Exception e){
            throw new CustomException("Exchange rate not found for the currency.");
        }
    }
}
