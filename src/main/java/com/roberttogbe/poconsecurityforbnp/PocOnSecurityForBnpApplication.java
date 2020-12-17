package com.roberttogbe.poconsecurityforbnp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */



@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class PocOnSecurityForBnpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocOnSecurityForBnpApplication.class, args);
	}

}
