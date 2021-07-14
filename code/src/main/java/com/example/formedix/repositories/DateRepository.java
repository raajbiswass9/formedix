package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Dates;
import com.example.formedix.models.Rates;

import java.util.Date;

public interface DateRepository {
    Integer findDatesId(Date date) throws CustomException;
}
