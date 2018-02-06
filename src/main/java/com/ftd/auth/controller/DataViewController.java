package com.ftd.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.ftd.auth.data.User;
import com.ftd.auth.data.UserRepository;


@Controller
public class DataViewController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/data")
    public String getData(Model model) {
        List<User> userList = userRepo.findAll();
        model.addAttribute("userList", userList);
        return "data";
    }

    @CrossOrigin
    @GetMapping("/sample")
    public String getSample(Model model) {
        return "sample";
    }

}