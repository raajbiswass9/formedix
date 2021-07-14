package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


@Service
public class MainServiceImpl implements MainService{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ArrayList<Rates> getRates(String date) throws CustomException {
        v_date.verify(date, "date");

        return null;
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
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(value.trim());
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
}
