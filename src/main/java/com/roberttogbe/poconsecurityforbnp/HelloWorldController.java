package com.roberttogbe.poconsecurityforbnp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */


@RestController
public class HelloWorldController {

    @RequestMapping("/home")
    public String index() {
        return "Greetings From BNP Paris Bas !!!!!";
    }
}
