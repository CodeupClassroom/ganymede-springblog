package com.codeup.springblog.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingSvc {

    public String goodMorning(String name){
        return "Good Morning " + name + "!";
    }

    public String goodAfternoon(String name){
        return "Good Afternoon " + name + "!";
    }

    public String goodEvening(String name){
        return "Good Evening " + name + "!";
    }
}
