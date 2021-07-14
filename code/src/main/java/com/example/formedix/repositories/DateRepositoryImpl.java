package com.example.formedix.repositories;

import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Dates;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class DateRepositoryImpl implements DateRepository {

    private final DateRepositoryBase dateRepositoryBase;

    public DateRepositoryImpl(DateRepositoryBase dateRepositoryBase) {
        this.dateRepositoryBase = dateRepositoryBase;
    }

    @Override
    public Integer findDatesId(Date dates) throws CustomException {
        try{
            Dates result = dateRepositoryBase.findAllByDates(dates);
            if(result != null){
                return result.getId();
            }else{
                throw new CustomException("Data not found for date: "+dates.toString());
            }
        }catch (Exception e){
            throw new CustomException("Internal server problem. Unable to get data.");
        }
    }
}
