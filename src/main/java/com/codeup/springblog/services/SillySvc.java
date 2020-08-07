package com.codeup.springblog.services;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SillySvc {

    private AdRepository adsDao;
    private UserRepository userDao;

    public SillySvc(AdRepository adsDao, UserRepository userDao){
        this.adsDao = adsDao;
        this.userDao = userDao;
    }

    public int totalPostCharacters(){
        Iterable<Ad> ads = adsDao.findAll();
        Iterable<User> users = userDao.findAll();

        int total = 0;

        for(Ad ad : ads){
            total += ad.getDescription().length();
        }

        for(User user : users){
            total += user.getUsername().length();
        }
        return total;
    }
}
