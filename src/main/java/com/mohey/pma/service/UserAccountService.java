package com.mohey.pma.service;

import com.mohey.pma.dao.UserAccountRepository;
import com.mohey.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<UserAccount> findAll(){
        return userAccountRepo.findAll();
    }

    public void save(UserAccount userAccount){
        String encodedPassword = passwordEncoder.encode(userAccount.getPassword());
        userAccount.setPassword(encodedPassword);
        this.userAccountRepo.save(userAccount);
    }
}
