package com.codeup.springblog.controllers;

import com.codeup.springblog.services.GreetingSvc;
import com.codeup.springblog.services.SillySvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceTestController {
    private GreetingSvc greetingSvc;
    private SillySvc sillySvc;

    public ServiceTestController(GreetingSvc greetingSvc, SillySvc sillySvc){
        this.greetingSvc = greetingSvc;
        this.sillySvc = sillySvc;
    }

    @GetMapping("/silly")
    @ResponseBody
    public int totalLength(){
        return sillySvc.totalPostCharacters();
    }
    @GetMapping("/morning/{name}")
    @ResponseBody
    public String morningGreeting(@PathVariable String name){
        return greetingSvc.goodMorning(name);
    }

    @GetMapping("/afternoon/{name}")
    @ResponseBody
    public String afternoonGreeting(@PathVariable String name){
        return greetingSvc.goodAfternoon(name);
    }

    @GetMapping("/evening/{name}")
    @ResponseBody
    public String eveningGreeting(@PathVariable String name){
        return greetingSvc.goodEvening(name);
    }

}
