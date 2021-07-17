package com.example.formedix.services;

import com.example.formedix.repositories.CurrencyRepository;
import com.example.formedix.repositories.DateRepository;
import com.example.formedix.repositories.RatesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MainServiceImplTest {
    @MockBean
    DateRepository dateRepository;

    @MockBean
    RatesRepository ratesRepository;

    @MockBean
    CurrencyRepository currencyRepository;

    @Autowired
    MainServiceImpl mainServiceImpl;

    //Assign dates, currency data into variables.
    String date_a = "2021-06-17";
    String date_b = "2021-07-15";
    Integer date_a_id  = 1;
    Integer date_b_id = 21;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date_a_test = dateFormat.parse(date_a);
    Date date_b_test = dateFormat.parse(date_b);

    String currency_name_a = "USD";
    Integer currency_id_a = 1;
    String currency_name_b = "GBP";
    Integer currency_id_b = 8;

    MainServiceImplTest() throws ParseException {
    }


    /**
     * TEST METHOD: getRates
     * Notes: to verify getRates return exchange rates or not.
     * @throws Exception
     */
    @Test
    void testGetRates() throws Exception {
        when(dateRepository.findDatesId(date_a_test)).thenReturn(date_a_id);

        String[] fake_currency_data = new String[2];
        fake_currency_data[0] = "1.194";
        fake_currency_data[1] = "USD";

        List<String[]> result = new ArrayList<>();
        result.add(0,fake_currency_data);

        when(ratesRepository.getRatesByDate(date_a_id)).thenReturn(result);
        assertEquals(1,mainServiceImpl.getRates(date_a).size());
    }

    /**
     * TEST METHOD: convertCurrency
     * Notes: to verify convertCurrency method return valid converted currency amount or not.
     * @throws Exception
     */
    @Test
    void testConvertCurrency() throws Exception {
        when(dateRepository.findDatesId(date_a_test)).thenReturn(date_a_id);
        when(currencyRepository.getCurrencyId(currency_name_a)).thenReturn(currency_id_a);
        when(currencyRepository.getCurrencyId(currency_name_b)).thenReturn(currency_id_b);
        when(ratesRepository.getRateByDateAndCurrency(date_a_id,currency_id_a)).thenReturn(1.1937);
        when(ratesRepository.getRateByDateAndCurrency(date_a_id,currency_id_b)).thenReturn(0.85525);

        assertEquals(0.716,mainServiceImpl.convertCurrency(date_a,currency_name_a, currency_name_b, "1"));
    }

    /**
     * TEST METHOD: getExchangeRateOfCurrency
     * Notes: to verify getExchangeRateOfCurrency return highest exchange rate or not.
     * @throws Exception
     */
    @Test
    void testHighestExchangeRateOfCurrency() throws Exception {
        when(dateRepository.findDatesId(date_a_test)).thenReturn(date_a_id);
        when(dateRepository.findDatesId(date_b_test)).thenReturn(date_b_id);
        when(currencyRepository.getCurrencyId(currency_name_a)).thenReturn(currency_id_a);
        when(ratesRepository.getExchangeRateValue(date_a_id,date_b_id,currency_id_a,"highest")).thenReturn(1.188);

        assertEquals(1.188,mainServiceImpl.getExchangeRateOfCurrency(date_a,date_b, currency_name_a, "highest"));
    }


    /**
     * TEST METHOD: getExchangeRateOfCurrency
     * Notes: to verify getExchangeRateOfCurrency return average exchange rate or not.
     * @throws Exception
     */
    @Test
    void testAverageExchangeRateOfCurrency() throws Exception {
        when(dateRepository.findDatesId(date_a_test)).thenReturn(date_a_id);
        when(dateRepository.findDatesId(date_b_test)).thenReturn(date_b_id);
        when(currencyRepository.getCurrencyId(currency_name_a)).thenReturn(currency_id_a);
        when(ratesRepository.getExchangeRateValue(date_a_id,date_b_id,currency_id_a,"average")).thenReturn(1.188);

        assertEquals(1.188,mainServiceImpl.getExchangeRateOfCurrency(date_a,date_b, currency_name_a, "average"));
    }
}