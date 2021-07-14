package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Currency;
import com.example.formedix.models.Rates;

import java.util.ArrayList;

public interface MainService {
    ArrayList<Rates> getRates(Integer date_id) throws CustomException;
}
