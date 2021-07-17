package com.example.formedix.controllers;

import com.example.formedix.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1")
public class MainController {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    MainService mainService;

    /**
     * Get Reference Rate for all currencies on a given date
     * @param date
     * @return response(reference rate)
     */
    @RequestMapping(value = "/reference_rate", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getReferenceRate(@RequestParam(value = "date", required = true) String date){
        response.clear();
        try {
            Map<String, Double> result = mainService.getRates(date);
            response.put("status","success");
            response.put("response", result);

        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Convert Currency
     * @param date
     * @param source
     * @param target
     * @param amount
     * @return response(converted currency)
     */
    @RequestMapping(value = "/convertCurrency", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> convertCurrency(@RequestParam(value = "date", required = true) String date,
                                                               @RequestParam(value = "source", required = true) String source,
                                                               @RequestParam(value = "target", required = true) String target,
                                                               @RequestParam(value = "amount", required = true) String amount){
        response.clear();
        try {
            double result = mainService.convertCurrency(date, source, target, amount);
            response.put("status","success");
            response.put("source",source);
            response.put("target",target);
            response.put("value:",result);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Get exchange rate(highest or average) of a currency of a date range
     * @param exchange_rate_type
     * @param start_date
     * @param end_date
     * @param currency
     * @return
     */
    @RequestMapping(value = "/exchangeRateByType/{exchange_rate_type}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getExchangeRateByType(@PathVariable String exchange_rate_type,
                                                                   @RequestParam(value = "start_date", required = true) String start_date,
                                                                   @RequestParam(value = "end_date", required = true) String end_date,
                                                                   @RequestParam(value = "currency", required = true) String currency){
        response.clear();
        try {
            double result;
            result = mainService.getExchangeRateOfCurrency(start_date, end_date, currency, exchange_rate_type);
            response.put("status","success");
            response.put(exchange_rate_type+"_exchange_rate:",result);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get currency name list
     * @return response(currencies)
     */
    @RequestMapping(value = "/currencies", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getCurrencyList(){
        response.clear();
        try {
            List<String> result = mainService.getCurrencies();
            response.put("status","success");
            response.put("currencies:",result);
        }catch(Exception e) {
            response.put("status","fail");
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
