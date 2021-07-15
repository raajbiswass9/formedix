package com.example.formedix.repositories;


import com.example.formedix.exceptions.CustomException;
import com.example.formedix.models.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatesRepositoryBase  extends JpaRepository<Rates, Integer> {



//    @Query(
//            value = "SELECT rates.*, currency.* FROM rates INNER JOIN currency on rates.currency_id = currency.id " +
//                    "WHERE rates.date_id = :date_id ",
//            nativeQuery = true
//    )
    @Query(
            value = "SELECT rates.*, currency.name " +
                    "FROM rates JOIN currency on rates.currency_id = currency.id " +
                    "WHERE rates.date_id = :date_id",
            nativeQuery = true
    )
    public List<Rates> findRatesByDateId(@Param("date_id")int date_id) throws CustomException;
}
