package com.miladsadeghi.financial.management.service;

import com.miladsadeghi.financial.management.entity.User;
import com.miladsadeghi.financial.management.model.CustomUserDetails;
import com.miladsadeghi.financial.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepo;
    @Autowired
    public UserDetailServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public void registerNewUser(User user) {
        User userByUserName = userRepo.getByUsername(user.getUsername());
        if (userByUserName !=null) {
            throw new IllegalArgumentException("Username is Already");
        }
        userRepo.save(user);
    }

    public User getByUsername(String username) {
        User user=new User();
        User byUsername = userRepo.getByUsername(user.getUsername());

        return  byUsername;
    }

    public User findById(long id){
        return userRepo.findById(id).get();
    }


    public void updateUser(User user){
        userRepo.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }
}
