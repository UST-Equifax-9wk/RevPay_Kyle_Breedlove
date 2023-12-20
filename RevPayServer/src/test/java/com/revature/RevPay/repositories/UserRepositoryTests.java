package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.web.JsonPath;
import org.springframework.orm.jpa.JpaSystemException;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;
    //Insert invalid user tests (repeats, nulls, should be in service tests)
    @Test
    void insertValidUser(){
        User valid = new User("KyleBreedlove","1111111111","email@email","pass",0.0,true,false);
        userRepository.save(valid);
        Assertions.assertEquals(entityManager.find(User.class,valid.getUsername()),valid);
    }

    @Test
    void updateUser(){
        User user = new User("kylebreedlove","111","email","pass",0.0,false,false);
        userRepository.save(user);
        User updated = new User("kylebreedlove", "22222","second","newPass",0.0,false,false);
        userRepository.save(updated);
        Assertions.assertEquals(updated,entityManager.find(User.class,user.getUsername()));
    }

    @Test
    void removeUser(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        userRepository.delete(user);
        Assertions.assertNull(entityManager.find(User.class,user.getUsername()));
    }

    @Test
    void getValidUserByUsername(){
        User testUser = new User("testUser","1111","email","password",0.0,false,false);
        userRepository.save(testUser);
        User resultUser = userRepository.getByUsername(testUser.getUsername());
        Assertions.assertEquals(testUser,resultUser);
    }
    @Test
    void getValidUserByEmail(){
        User testUser = new User("testUser","1111","email","password",0.0,false,false);
        userRepository.save(testUser);
        User resultUser = userRepository.getByEmail(testUser.getEmail());
        Assertions.assertEquals(testUser,resultUser);
    }

    @Test
    void getInvalidUserByUsername(){
        User resultUser = userRepository.getByUsername("");
        Assertions.assertNull(resultUser);
    }
    @Test
    void getInvalidUserByEmail(){
        User resultUser = userRepository.getByEmail("");
        Assertions.assertNull(resultUser);
    }


}
