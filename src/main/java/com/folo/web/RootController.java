package com.folo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiang on 12/27/2016.
 */
//@RestController
@Controller
public class RootController {

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String getHtmlFrame() {
        return "index";
    }
}
