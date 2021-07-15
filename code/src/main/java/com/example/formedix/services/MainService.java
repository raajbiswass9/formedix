package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Currency;
import com.example.formedix.models.Rates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MainService {
    Map<String, Float> getRates(String date) throws CustomException;
}
