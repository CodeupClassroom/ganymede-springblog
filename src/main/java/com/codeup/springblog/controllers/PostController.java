package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String index(Model model){
        ArrayList<Post> myPosts = new ArrayList<>();
        myPosts.add(new Post(2,"Title 2", "wqeoiruqowieuroiwueroiuqwer"));
        myPosts.add(new Post(3,"Title 3", "Ganymeeeeeeeeeeede"));
        myPosts.add(new Post(4,"Title 4", "wqeoiruqowieuroiwueroiuqwer"));

        model.addAttribute("posts", myPosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model){
        Post myPost = new Post(id,"Test Title","Hello World!");
        model.addAttribute("title", myPost.getTitle());
        model.addAttribute("body", myPost.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "Here is the form to create a post!";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insert(){
        return "Post has been created!";
    }


}
