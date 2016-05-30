package com.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Applikasi {

	 public static void main(String[] args) {
	        SpringApplication.run(Applikasi.class, args);
	    }
}
