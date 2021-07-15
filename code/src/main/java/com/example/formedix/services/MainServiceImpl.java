package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import com.example.formedix.repositories.DateRepository;
import com.example.formedix.repositories.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


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
        validate.verify("Date", dates); //Verify date format

        Map<String, Float> response = new HashMap<>();
        Integer date_id = getDateId(convertStringToDate(dates));
        List<Rates> rates = getExchangeRatesByDateId(date_id);

        if(!rates.isEmpty()){
            rates.forEach(r-> response.put(r.getCurrency().getName(),r.getExchange_rate())); //update and add rates data in response
        }

        return response;
    }


    @Override
    public double convertCurrency(String dates, String source, String target, String amount) throws CustomException {
        //Payloads validation
        validate.verify("Date", dates);
        validate.verify("Source", source);
        validate.verify("Target", target);
        validate.verify("Amount", amount);


        //Get date id
            //true:
                //verify Source & Target and get IDs
                    //true:
                        // verify if source & target id exists in given date
                            //true:
                                //convert currency
                            //false:
                                //throw(source or target not found on given date)
                    //false:
                        // throw(source or target not found)
            //false:
                // throw(date does not exists)

        Integer date_id = getDateId(convertStringToDate(dates));; //Get date_id
        Integer source_currency_id = dateRepository.findDatesId(convertStringToDate(dates)); //Get date_id
        Integer target_currency_id = dateRepository.findDatesId(convertStringToDate(dates)); //Get date_id


        double amt = Double.valueOf(amount);


        return amt;
    }



    /**
     * Validate payload
     * @param type, value
     * @return
     */
    PayloadValidation validate = (String type,String value)->{
        if(value == ""){
            throw new CustomException(type+" not provided.");
        }

        //Verify Source or Target
        if(type == "Source" || type == "Target"){
            if(!Pattern.matches("[a-zA-Z]+",value)){
                throw new CustomException("Invalid "+ type + " provided.");
            }
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
                Double.valueOf(value);
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

    /**
     * Get id of a date
     * @param dates
     * @return date_id
     */
    public Integer getDateId(Date dates){
        return dateRepository.findDatesId(dates);
    }

    /**
     * Get list of all the rates available for a given date_id
     * @param date_id
     * @return List<Rates>
     */
    public List<Rates> getExchangeRatesByDateId(Integer date_id){
        return ratesRepository.getRatesByDate(date_id);
    }
}
