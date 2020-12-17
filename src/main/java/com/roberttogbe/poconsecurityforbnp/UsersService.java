package com.roberttogbe.poconsecurityforbnp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */


@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersDBQuery usersDBQuery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersPojo usersPojo = null;
        try {
            usersPojo = usersDBQuery.getUserDetails(username);
            UsersHelper userDetail = new UsersHelper(usersPojo);
            return userDetail;
        }catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        }
    }
}
