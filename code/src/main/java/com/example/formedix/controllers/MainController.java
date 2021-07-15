package com.example.formedix.controllers;


import com.example.formedix.models.Rates;
import com.example.formedix.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1")
public class MainController {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    MainService mainService;

    /**
     * Get Reference Rate for all currencies on a given date
     * @param date
     * @return reference rate
     */
    @RequestMapping(value = "/reference_rate", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getReferenceRate(@RequestParam(value = "date", required = true) String date){
        response.clear();
        try {
            Map<String, Float> result = mainService.getRates(date);
            response.put("status:","success");
            response.put("response:", result);

        }catch(Exception e) {
            response.put("status:","fail");
            response.put("message:",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
