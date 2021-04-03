package com.mohey.pma.controllers;

import com.mohey.pma.dao.UserAccountRepository;
import com.mohey.pma.entities.UserAccount;
import com.mohey.pma.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/register")
    public String register(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);

        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveAccountUser(Model model, UserAccount userAccount){
        userAccountService.save(userAccount);
       return  "redirect:/";
    }

}
