package com.example.databaseh2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class DatabaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/hi")
    public String information(){
        return "Welcome Home!!!";
    }

    @GetMapping("/datafromdb")
    public String informationfromDB(){

        String countryName = jdbcTemplate.queryForObject("Select name from countries where id =1",String.class);

        return countryName;

    }
    @GetMapping("/updateindb")
    public String updateInformation(){
         jdbcTemplate.update("Update countries set name = 'INDIA' where id = 1 ");

        return "updated";

    }

    @GetMapping("/datafromdb2")
    public String informationfromDB2(){
        String countryName = jdbcTemplate.queryForObject("Select name from countries where id = ? ",new Object[]{1} , String.class);

        return countryName;

    }

    @GetMapping("/datafromdb3")
    public String listInformation(){
        List<Map<String, Object>> countryName = jdbcTemplate.queryForList("Select name from countries");

        System.out.println(countryName.get(0).get("NAME"));

        return countryName.get(0).get("NAME").toString();

    }


}
