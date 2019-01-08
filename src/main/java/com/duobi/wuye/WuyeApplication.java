package com.duobi.wuye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WuyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WuyeApplication.class, args);
	}
}
