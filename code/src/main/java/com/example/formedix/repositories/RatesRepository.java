package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;

import java.util.Date;
import java.util.List;

public interface RatesRepository {

    List<Rates> getRatesByDate(Integer date_id) throws CustomException;
}
