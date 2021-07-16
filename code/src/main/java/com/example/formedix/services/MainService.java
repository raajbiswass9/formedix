package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import java.util.Map;

public interface MainService {
    Map<String, Double> getRates(String date) throws CustomException;

    double convertCurrency(String dates, String source, String target, String amount) throws CustomException;

    double getExchangeRateOfCurrency(String start_dates, String end_date, String curency_name, String type) throws CustomException;

}
