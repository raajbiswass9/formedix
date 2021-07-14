package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class MainServiceImpl implements MainService{


    @Override
    public ArrayList<Rates> getRates(Integer date_id) throws CustomException {
        return null;
    }
}
