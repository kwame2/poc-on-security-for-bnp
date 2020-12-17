package com.roberttogbe.poconsecurityforbnp;

import org.springframework.security.core.userdetails.User;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */

// cette classe étend la classe User qui est une classe fournie par spring security.
// cette classe permet d'authentifier le user

public class UsersHelper extends User{

    private static final long serialVersionUID = 1L;

    public UsersHelper(UsersPojo user) {
        super(
                user.getUsername(),
                user.getPassword(),
                user.getListOfgrantedAuthorities()

        );
    }
}
