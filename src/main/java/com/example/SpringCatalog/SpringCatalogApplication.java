package com.example.SpringCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SpringCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCatalogApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "add_product";
	}

}
