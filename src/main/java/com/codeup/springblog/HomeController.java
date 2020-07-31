package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
//    @ResponseBody
    public String returnLandingMessage() {
//    this returns home.html
        return "home";
    }

    @PostMapping("/")
//    this method will pull the name attribute from the submitted form
    public String returnCohort(@RequestParam(name = "cohort") String cohort, Model model){
//    the name attribute is sent back to home.html
        model.addAttribute("cohort", cohort);
        return "home";
    }
}
