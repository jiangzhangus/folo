package com.folo;

/**
 * Created by jiang on 12/23/2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class FoloApplication{

    private static final Logger log = LoggerFactory.getLogger(FoloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FoloApplication.class, args);
    }
}

