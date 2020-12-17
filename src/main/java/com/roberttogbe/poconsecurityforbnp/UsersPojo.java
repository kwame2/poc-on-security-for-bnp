package com.roberttogbe.poconsecurityforbnp;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */


// Cette classe permet d'enregistrer des infos concernant les utilisateurs

public class UsersPojo {

    private String username;
    private String password;
    // ce champ représente la liste de GrantedAuthority
    private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<GrantedAuthority> getListOfgrantedAuthorities() {
        return listOfgrantedAuthorities;
    }

    public void setListOfgrantedAuthorities(Collection<GrantedAuthority> listOfgrantedAuthorities) {
        this.listOfgrantedAuthorities = listOfgrantedAuthorities;
    }

}
