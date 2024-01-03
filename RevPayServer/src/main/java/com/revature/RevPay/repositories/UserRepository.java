package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    public User getByUsername(String username);
    public User getByEmail(String email);

    public User getByPhoneNumber(String phone);


    public User deleteByUsername(String username);


}
