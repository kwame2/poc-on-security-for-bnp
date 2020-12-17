package com.roberttogbe.poconsecurityforbnp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */

// cette classe permet de lire dans la base les détails du user et va les envoyer à UsersHelper pour authentification
// Dans cette classe j'ai écrit une query pour "fetcher" le détail d'un user
// Pour GrantedAuthority, j'ai choisi la valeur: ROLE_SYSTEMADMIN
// ce qui signifie que ce user a comme role System Admin

@Repository
public class UsersDBQuery {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsersPojo getUserDetails(String username) {

        Collection<GrantedAuthority> lisOfgrantedAuthorities = new ArrayList<>();
        String userSQLQuery = "SELECT * from USERS where USERNAME = ?";
        List<UsersPojo> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
                (ResultSet rs, int rowNum) -> {
                    UsersPojo user = new UsersPojo();
                    user.setUsername(username);
                    user.setPassword(rs.getString("PASSWORD"));
                    return user;
                } );
        if(list.size() > 0) {

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");

            lisOfgrantedAuthorities.add(grantedAuthority);
            list.get(0).setListOfgrantedAuthorities(lisOfgrantedAuthorities);
            return list.get(0);
        }
        return null;
    }
}
