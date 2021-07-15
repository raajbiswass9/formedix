package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import com.example.formedix.repositories.DateRepository;
import com.example.formedix.repositories.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class MainServiceImpl implements MainService{
    @Autowired
    DateRepository dateRepository;

    @Autowired
    RatesRepository ratesRepository;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Get exchange rates for given date
     * @param dates
     * @return response(exchange_rates)
     * @throws CustomException
     */
    @Override
    public Map<String, Float> getRates(String dates) throws CustomException {
        v_date.verify("Date", dates); //Verify date format
        Date c_dates = convertStringToDate(dates);

        Map<String, Float> response = new HashMap<>();
        Integer date_id = dateRepository.findDatesId(c_dates); //Get date_id
        List<Rates> rates = ratesRepository.getRatesByDate(date_id); //get exchange rates of date_id

        if(!rates.isEmpty()){
            rates.forEach(r-> response.put(r.getCurrency().getName(),r.getExchange_rate())); //update and add rates data in response
        }

        return response;
    }


    /**
     * Validate payload
     * @param type, value
     * @return
     */
    PayloadValidation v_date = (String type,String value)->{
        if(value == ""){
            throw new CustomException(type+" not provided.");
        }

        //Verify Date
        if(type == "Date"){
            try {
                convertStringToDate(value);
            } catch (Exception e) {
                throw new CustomException("Invalid date format provided.");
            }
        }

        //Verify Amount
        if(type == "Amount"){
            try{
                float f = Float.parseFloat(value);
            }catch(Exception e){
                throw new CustomException("Invalid amount provided.");
            }
        }
    };

    /**
     * Convert date(String to Date)
     * @param dates(String)
     * @return str_to_date(Date)
     */
    public Date convertStringToDate(String dates){
        Date str_to_date;
        dateFormat.setLenient(false);
        try {
            str_to_date = dateFormat.parse(dates.trim());
            System.out.println("Correct date format");
            return str_to_date;
        } catch (Exception e) {
            System.out.println("IN-Correct date format");
            throw new CustomException("Invalid date format provided.");
        }
    }
}
