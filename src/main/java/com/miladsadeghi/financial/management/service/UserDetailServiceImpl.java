package com.miladsadeghi.financial.management.service;

import com.miladsadeghi.financial.management.entity.User;
import com.miladsadeghi.financial.management.entity.UserRole;
import com.miladsadeghi.financial.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepo;
    @Autowired
    public UserDetailServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }



    public void registerUser(User user) {
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
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));
    }


    public List<User> findAll() {
        return userRepo.findAll();
    }

    private Set<GrantedAuthority> getAuthorities(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
    }
}
