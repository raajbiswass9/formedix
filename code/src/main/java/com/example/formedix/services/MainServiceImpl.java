package com.example.formedix.services;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import com.example.formedix.repositories.DateRepository;
import com.example.formedix.repositories.DateRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Service
public class MainServiceImpl implements MainService{
    @Autowired
    DateRepository dateRepository;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public ArrayList<Rates> getRates(String dates) throws CustomException {
        v_date.verify("Date", dates);
        Date c_dates = convertDate(dates);
        Integer date_id = dateRepository.findDatesId(c_dates);

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
            try {
                convertDate(value);
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

    /**
     * Convert date(String to Date)
     * @param dates(String)
     * @return date(Date)
     */
    public Date convertDate(String dates){
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
}
