package com.mohey.pma.controllers;

import com.mohey.pma.dao.UserAccountRepository;
import com.mohey.pma.entities.UserAccount;
import com.mohey.pma.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    public String saveAccountUser(@Valid UserAccount userAccount, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "security/register";
        }
        try{
            userAccountService.save(userAccount);
        }catch (Exception e){
            e.printStackTrace();
        }

       return  "redirect:/";
    }

}
