package com.mohey.pma.dao;

import com.mohey.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    @Override
    public List<UserAccount> findAll();
}
