package com.revature.RevPay.services;

import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import static com.revature.RevPay.RevPayApplication.logger;


@Service
@Transactional(Transactional.TxType.REQUIRED)
public class UserService {
    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService){
        this.userRepository=userRepository;
        this.emailService=emailService;
    }

    public String createUser(User user){
        if(user.getUsername()==null || user.getUsername().length()<User.getMinimumUsernameLength()) return "Not a valid username";
        if(user.getEmail()==null || !(user.getEmail().contains("@") && user.getEmail().contains("."))) return "Not a valid email";
        if(user.getPhoneNumber()==null) return "Not a valid phone number";
        if(user.getPassword()==null || user.getPassword().length()<User.getMinimumPasswordLength()) return "Not a valid password";
        if(getUserByUsername(user.getUsername())!=null) return "Username already exists";
        if(getUserByEmail(user.getEmail())!=null)return "Email already in use";
        if(getUserByPhone(user.getPhoneNumber())!=null)return "Phone number already in use";
        user.setPassword(hash(user.getPassword()));
        userRepository.save(user);
        emailService.createAccountEmail(user);
        logger.info("NEW USER CREATED - "+user.getUsername());
        return"Success";
    }

    public User getUserByUsername(String username){
        return userRepository.getByUsername(username);

    }
    public User getUserByPhone(String phone){
        return userRepository.getByPhoneNumber(phone);
    }
    public User getUserByEmail(String email){
        return userRepository.getByEmail(email);
    }

    public String updateUser(String username, User newValues){
        User original = getUserByUsername(username);
        if(original==null) return "Username does not exist";

        if(newValues.getEmail()!=null) { //new values contains an email
            if(!getUserByEmail(newValues.getEmail()).equals(original)){
                //new values contains an email already in user by a user other than 'original'
                return "Email already in use";
            }
        }
        if(newValues.getPassword()==null || newValues.getPassword().length()<User.getMinimumPasswordLength()) return "Not a vaild password";
        boolean filled = false;
        //fill null values
        if(newValues.getUsername()==null){
            newValues.setUsername(username);
            filled=true;
        }
        if(newValues.getPhoneNumber()==null){
            newValues.setPhoneNumber(original.getPhoneNumber());
            filled=true;
        }
        if(newValues.getEmail()==null){
            newValues.setEmail(original.getEmail());
            filled=true;
        }
        if(newValues.getPassword()==null) {
            newValues.setPassword(original.getPassword());
            filled=true;
        }
        if(newValues.getBalance()==null) {
            newValues.setBalance(original.getBalance());
            filled=true;
        }
        if(newValues.isAdmin()==null){
            newValues.setAdmin(original.isAdmin());
            filled=true;
        }
        if(newValues.isBusiness()==null){
            newValues.setBusiness(original.isBusiness());
            filled=true;

        }
        newValues.setPassword(hash(newValues.getPassword()));
        userRepository.save(newValues);
        if(filled)return "Success filled missing information";
        return "Success";
    }

    public User deleteUserByUsername(String username){
        if(getUserByUsername(username)==null)return null;
        return userRepository.deleteByUsername(username);
    }


    public String updateBalance(String username, double newBalance){
        User user = getUserByUsername(username);
        if(user==null)return "No user exists";
        user.setBalance(newBalance);
        userRepository.save(user);
        logger.info("BALANCE UPDATE - "+username+" balance set to "+newBalance);
        return "Success";
    }

    public String updatePassword(String username, String newPassword){
        if(newPassword.length()<User.getMinimumPasswordLength())return"Invalid password";
        User user =getUserByUsername(username);
        if(user == null)return"No user exists";
        String newHash = hash(newPassword);
        user.setPassword(newHash);
        userRepository.save(user);
        emailService.changePasswordEmail(user);
        logger.info("UPDATED PASSWORD from user: "+username);
        return "Success";
    }

    public boolean userLogin(String username, String password){
        if(username==null || password==null) return false;
        User user = getUserByUsername(username);
        if(user==null)return false;
        boolean res= BCrypt.checkpw(password,user.getPassword());
        if(res)logger.info("USER LOGGED IN "+username);
        return res;
    }

    public String hash(String text){
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(text,salt);
    }


}
