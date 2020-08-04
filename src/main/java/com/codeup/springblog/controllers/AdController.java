package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    // dependency injection
    private final AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String getAd(@PathVariable long id) {
        return adsDao.getOne(id).toString();
    }


    // return json
    @GetMapping("/ads")
    @ResponseBody
    public List<Ad> getAds() {
        return adsDao.findAll();
    }

    // return a view
    @GetMapping("/ads/view")
    public String getAdsIndex(Model model) {
        model.addAttribute("ads", adsDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/save")
    public String save() {
        Ad adToSave = new Ad();
        adToSave.setTitle("New Ad 1!");
        adToSave.setDescription("This is the new ad description!");
        adsDao.save(adToSave);
        return "redirect:/ads";
    }

}
