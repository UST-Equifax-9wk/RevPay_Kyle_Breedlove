package com.revature.RevPay.services;

import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EmailService emailService;
    //valid create user returns "success"
    @Test
    void givenValidUserWhenSaveUserThenReturnSuccess(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        String expectedAnswer = "Success";

        String result = userService.createUser(user);

        Assertions.assertEquals(expectedAnswer,result);
    }
    //create user username already exists fails returns "username already exists"
    @Test
    void givenUserWithDuplicateUsernameWhenSaveUserThenReturnUsernameAlreadyExists(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        String expectedAnswer = "Username already exists";

        String result = userService.createUser(user);

        Assertions.assertEquals(expectedAnswer,result);
    }
    //create user no unique email fails returns "email already in use"
    @Test
    void givenUserWithDuplicateEmailWhenSaveUserThenReturnEmailAlreadyInUse(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        String expectedAnswer = "Email already in use";

        String result = userService.createUser(user);

        Assertions.assertEquals(expectedAnswer,result);
    }
    //create user invalid password should fail returns "Not a valid password"
    @Test
    void givenUserWithInvalidPasswordWhenSaveUserThenReturnNotAValidPassword(){
        User user = new User("test","1111","email@.","");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        String expectedAnswer = "Not a valid password";

        String result = userService.createUser(user);

        Assertions.assertEquals(expectedAnswer,result);
    }
    @Test
    void givenUserWithInvalidUsernameWhenSaveUserThenReturnNotAValidUsername(){
        User user = new User("","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        String expectedAnswer = "Not a valid username";

        String result = userService.createUser(user);

        Assertions.assertEquals(expectedAnswer,result);
    }
    @Test
    void givenUserWithInvalidEmailWhenSaveUserThenReturnNotAValidEmail(){
        User user = new User("test","1111",null,"password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        String expectedAnswer = "Not a valid email";

        String result = userService.createUser(user);

        Assertions.assertEquals(expectedAnswer,result);
    }
    //get user by username valid returns user
    @Test
    void givenValidUsernameWhenGetUserByUsernameReturnsUser(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);

        User result = userService.getUserByUsername(user.getUsername());

        Assertions.assertEquals(user,result);
    }
    //get user by username invalid returns null
    @Test
    void givenNotExistingUsernameWhenGetUserByUsernameReturnsNull(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);

        User result = userService.getUserByUsername(user.getUsername());

        Assertions.assertNull(result);
    }
    //get user by email valid returns user
    @Test
    void givenValidEmailWhenGetUserByEmailReturnsUser(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByEmail(user.getEmail())).thenReturn(user);

        User result = userService.getUserByEmail(user.getEmail());

        Assertions.assertEquals(user,result);
    }
    //get user by email invalid returns null
    @Test
    void givenNotExistingEmailWhenGetUserByEmailReturnsNull(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByEmail(user.getEmail())).thenReturn(null);

        User result = userService.getUserByEmail(user.getEmail());

        Assertions.assertNull(result);
    }
    //update user return "success"
    @Test
    void givenFullValidUserWhenUpdateUserReturnsSuccess(){
        User user = new User("test","1111","email@.","password");
        User updatedUser = new User("test","222","email@.","saferpassword",2000.0,false,true);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "Success";

        String result = userService.updateUser(user.getUsername(),updatedUser);

        Assertions.assertEquals(expectedAnswer,result);
    }
    //update user no username returns "does not exist"
    @Test
    void givenNotExistingUsernameWhenUpdateUserReturnsUsernameDoesNotExist(){
        User user = new User("test","1111","email@.","password");
        User updatedUser = new User("test","222","email@.","saferpassword",2000.0,false,true);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "Username does not exist";

        String result = userService.updateUser(user.getUsername(),updatedUser);

        Assertions.assertEquals(expectedAnswer,result);
    }
    //update user with not all fields full returns "Success filled missing information"
    @Test
    void givenIncompleteButValidUserWhenUpdateUserFillsUserWithExistingInfoAndReturnsSuccessFilledMissingInformation(){
        User user = new User("test","1111","email@.","password");
        User updatedUser = new User("test",null,null,"saferpassword",2000.0,false,true);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.getByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "Success filled missing information";

        String result = userService.updateUser(user.getUsername(),updatedUser);

        Assertions.assertEquals(expectedAnswer,result);
    }

    //delete user by username return user
    @Test
    void givenValidUserNameWhenDeleteUserByUsernameThenReturnUser(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.deleteByUsername(user.getUsername())).thenReturn(user);

        User result = userService.deleteUserByUsername(user.getUsername());

        Assertions.assertEquals(user,result);
    }
    //delete user by username no username returns null
    @Test
    void givenInvalidUserNameWhenDeleteUserByUsernameThenReturnNull(){
        User user = new User("test","1111","email@.","password");
        when(userRepository.getByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.deleteByUsername(user.getUsername())).thenReturn(null);

        User result = userService.deleteUserByUsername(user.getUsername());

        Assertions.assertNull(result);
    }

    @Test
    void givenValidUserAndNewBalanceWhenUpdateBalanceReturnSuccess(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        double newBalance = 1500;
        User updatedUser = new User("test","1111","email@.","password",1500.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "Success";

        String result = userService.updateBalance(user.getUsername(),newBalance);

        Assertions.assertEquals(expectedAnswer,result);
    }
    @Test
    void givenInvalidUserAndNewBalanceWhenUpdateBalanceReturnNoUserExists(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        double newBalance = 1500;
        User updatedUser = new User("test","1111","email@.","password",1500.0,false,false);
        when(userRepository.getByUsername("")).thenReturn(null);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "No user exists";

        String result = userService.updateBalance(user.getUsername(),newBalance);

        Assertions.assertEquals(expectedAnswer,result);
    }
    @Test
    void givenValidUserAndValidNewPasswordWhenUpdatePasswordReturnSuccess(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        String newPassword = "abcdefg1991";
        User updatedUser = new User("test","1111","email@.",newPassword,2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        String expectedAnswer = "Success";

        String result = userService.updatePassword(user.getUsername(),newPassword);

        Assertions.assertEquals(expectedAnswer,result);
    }
    @Test
    void givenInvalidUserAndValidNewPasswordWhenUpdatePasswordReturnNoUserExists(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        String newPassword = "abcdefg1991";
        User updatedUser = new User("test","1111","email@.",newPassword,2000.0,false,false);
        when(userRepository.getByUsername("")).thenReturn(null);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "No user exists";

        String result = userService.updatePassword(user.getUsername(),newPassword);

        Assertions.assertEquals(expectedAnswer,result);
    }

    @Test
    void givenValidUserAndInvalidPasswordWhenUpdatePasswordReturnInvalidPassword(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        String newPassword = "";
        User updatedUser = new User("test","1111","email@.",newPassword,2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        String expectedAnswer = "Invalid password";

        String result = userService.updatePassword(user.getUsername(),newPassword);

        Assertions.assertEquals(expectedAnswer,result);
    }

    //successfull login
    @Test
    void givenValidLoginInformationWhenLoginReturnTrue(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);

        boolean result = userService.userLogin(user.getUsername(),user.getPassword());

        Assertions.assertTrue(result);
    }
    //login missing username
    @Test
    void givenMissingUsernameWhenLoginReturnFalse(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);

        boolean result = userService.userLogin(null,user.getPassword());

        Assertions.assertFalse(result);
    }
    //login missing password
    @Test
    void givenMissingPasswordWhenLoginReturnFalse(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);

        boolean result = userService.userLogin(user.getUsername(),null);

        Assertions.assertFalse(result);
    }
    //login no matching username
    @Test
    void givenNoMatchingUsernameWhenLoginReturnFalse(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);
        String wrongUsername = "notAUsername";
        when(userRepository.getByUsername(wrongUsername)).thenReturn(null);

        boolean result = userService.userLogin(wrongUsername,user.getPassword());

        Assertions.assertFalse(result);
    }
    //login wrong password
    @Test
    void givenIncorrectPasswordWhenLoginReturnFalse(){
        User user = new User("test","1111","email@.","password",2000.0,false,false);
        when(userRepository.getByUsername(user.getUsername())).thenReturn(user);

        boolean result = userService.userLogin(user.getUsername(),"wrongpassword");

        Assertions.assertFalse(result);
    }
}