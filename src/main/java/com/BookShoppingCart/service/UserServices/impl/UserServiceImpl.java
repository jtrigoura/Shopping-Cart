package com.BookShoppingCart.service.UserServices.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.BookShoppingCart.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookShoppingCart.model.User;
import com.BookShoppingCart.service.UserServices.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User findByMobile(String mobile) throws Exception {
        return userRepo.findByMobile(mobile).orElseThrow(() -> new Exception("User Not found.."));
    }

    @Override
    public User getUserDetailById(long userId) throws Exception {

        return userRepo.findById(userId).orElseThrow(() -> new Exception("User Not found.."));
    }

    @Override
    public User signUpUser(HashMap<String, String> signupRequest) throws Exception {
        try {
            if (userRepo.findByMobile(signupRequest.get("mobile")).isPresent()) {
                throw new Exception("User is already registered with Mobile No.");
            }
            User user = new User();
            user.setName(signupRequest.get("name"));
            user.setEmail(signupRequest.get("email"));
            user.setMobile(signupRequest.get("mobile"));
            user.setPassword(signupRequest.get("password"));
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            user.setCreated_at(ts.toString());
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    public boolean checkExistedUser(long id) {
        if(userRepo.existsById((long) id)) {
            return true;
        }
        return false;
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }
    public User saveUser(User user) {
        return userRepo.save(user);
    }
}
