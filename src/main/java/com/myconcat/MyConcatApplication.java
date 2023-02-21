package com.myconcat;

import com.myconcat.entity.Contact;
import com.myconcat.service.ContactService;
import com.myconcat.service.ContactServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class MyConcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyConcatApplication.class, args);
    }

}
