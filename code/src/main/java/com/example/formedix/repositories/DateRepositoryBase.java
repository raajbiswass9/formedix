package com.example.formedix.repositories;

import com.example.formedix.models.Dates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface DateRepositoryBase  extends JpaRepository<Dates, Integer> {
    Dates findAllByDates(Date date);
}
