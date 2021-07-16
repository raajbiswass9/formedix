package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import com.example.formedix.repositories.CurrencyRepository;
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

    @Autowired
    CurrencyRepository currencyRepository;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Integer date_id;
    private Integer source_currency_id;
    private Integer target_currency_id;
    private Integer start_date_id;
    private Integer end_date_id;
    private final String string_regex = "[a-zA-Z]+";


    /**
     * Get all exchange rates for given date
     * @param dates
     * @return response(exchange_rates)
     * @throws CustomException
     */
    @Override
    public Map<String, Double> getRates(String dates) throws CustomException {
        validate.verify("Date", dates); //Verify date format

        Map<String, Double> response = new HashMap<>();
        Integer date_id = getDateId(convertStringToDate(dates));
        List<String[]> rates = getExchangeRatesByDateId(date_id);

        if(!rates.isEmpty()){
            rates.forEach(obj -> response.put(obj[1], round(Double.valueOf(obj[0])) ));//update and add rates data in response
        }

        return response;
    }

    /**
     * Convert currency on a given date(Ex: GBP to USD)
     * @param dates
     * @param source
     * @param target
     * @param amount
     * @return result(converted currency)
     * @throws CustomException
     */
    @Override
    public double convertCurrency(String dates, String source, String target, String amount) throws CustomException {
        //Payloads validation
        validate.verify("Date", dates);
        validate.verify("Source", source);
        validate.verify("Target", target);
        validate.verify("Amount", amount);

        date_id = getDateId(convertStringToDate(dates));
        source_currency_id = getCurrencyId(source);
        target_currency_id = getCurrencyId(target);

        double source_exchange_rate = getExchangeRatesByDateAndCurrency(date_id,source_currency_id);
        double target_exchange_rate = getExchangeRatesByDateAndCurrency(date_id,target_currency_id);

        double result = ((1/source_exchange_rate) / (1/target_exchange_rate)) * Double.valueOf(amount); //Calculate source to target currency value

        return round(result);
    }

    /**
     * Get Highest or Average exchange rates for a currency on a given date range.
     * @param start_dates
     * @param end_date
     * @param curency_name
     * @param exchange_rate_type
     * @return
     * @throws CustomException
     */
    @Override
    public double getExchangeRateOfCurrency(String start_dates, String end_date, String curency_name, String exchange_rate_type) throws CustomException {
        if((exchange_rate_type.matches("average|highest") == false)){
            throw new CustomException("Invalid exchange rate type provided.");
        }

        validate.verify("Date", start_dates);
        validate.verify("Date", end_date);
        validate.verify("Currency_name", curency_name);

        start_date_id = getDateId(convertStringToDate(start_dates));
        end_date_id = getDateId(convertStringToDate(end_date));
        source_currency_id = getCurrencyId(curency_name);

        double result = getExchangeRates(start_date_id, end_date_id, source_currency_id, exchange_rate_type);

        return result;
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
        if(type == "Source" || type == "Target" || type == "Currency_name"){
            if(!Pattern.matches(string_regex,value)){
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
            return str_to_date;
        } catch (Exception e) {
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
    public List<String[]> getExchangeRatesByDateId(Integer date_id){
        return ratesRepository.getRatesByDate(date_id);
    }

    /**
     * Get currency id by currency name
     * @param currency_name
     * @return id(currency id)
     */
    public Integer getCurrencyId(String currency_name){
        return currencyRepository.getCurrencyId(currency_name);
    }

    /**
     * Get exchange rate by date and currency
     * @param date_id
     * @param currency_id
     * @return exchange_rate
     */
    public double getExchangeRatesByDateAndCurrency(Integer date_id, Integer currency_id){
        Rates result = ratesRepository.getRateByDateAndCurrency(date_id, currency_id);
        return round(result.getExchange_rate());
    }

    /**
     * Get exchange rates for a currency on a given date range.
     * @param start_date_id
     * @param end_date_id
     * @param source_currency_id
     * @param exchange_rate_type
     * @return exchange_rate (hgighest OR average)
     */
    public double getExchangeRates(Integer start_date_id, Integer end_date_id, Integer source_currency_id, String exchange_rate_type){
        return round(ratesRepository.getExchangeRateValue(start_date_id, end_date_id, source_currency_id,exchange_rate_type));
    }

    /**
     * Convert to 3 decimal point value (Example 12.34567 to 12.345)
     * @param value
     * @return 3 decimal point value (Example 12.345)
     */
    public static double round(double value) {
        long factor = (long) Math.pow(10, 3);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
