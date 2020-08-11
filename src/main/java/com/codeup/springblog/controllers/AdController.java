package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(loggedInUser);
        System.out.println(loggedInUser.getUsername());
        model.addAttribute("ads", adsDao.findAllByOrderByIdDesc());
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

    @GetMapping("/ads/test")
    @ResponseBody
    public String getTestAd() {
        return adsDao.findByTitle("Biodex").toString();
    }


    // ======================= version WITHOUT form model binding
//    @GetMapping("/ads/create")
//    public String showCreateForm() {
//        return "ads/create";
//    }
//
//    @PostMapping("/ads/create")
//    @ResponseBody
//    public String create(
//            @RequestParam(name = "title") String title,
//            @RequestParam(name = "description") String description
//    ) {
//        Ad ad = new Ad();
//        ad.setTitle(title);
//        ad.setDescription(description);
//        // save the ad...
//        return "Ad saved!";
//    }




    @GetMapping("/ads/create")
    public String showCreateForm(Model model){
        //Add an empty ad object to the model, so we can send it to the view
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@ModelAttribute Ad ad){
        //Using the @ModelAttribute, reference the Ad object and save
        adsDao.save(ad);

        //redirect to /ads/view, because that endpoint returns all the ads
        return "redirect:/ads/view";
    }







}
