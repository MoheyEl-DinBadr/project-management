package com.mohey.pma.dao;

import com.mohey.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    @Override
    public List<UserAccount> findAll();

    public UserAccount findByEmail(@Param("email") String email);

    public UserAccount findByUserName(@Param("userName") String userName);
}
