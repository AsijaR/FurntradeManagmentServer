package com.furntrade.furntrademanagmentservet;

import com.furntrade.furntrademanagmentservet.Models.AppRole;
import com.furntrade.furntrademanagmentservet.Models.AppUser;
import com.furntrade.furntrademanagmentservet.Service.IUserService;
import com.furntrade.furntrademanagmentservet.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class FurntradeManagmentServetApplication {

    public static void main(String[] args) {
        SpringApplication.run(FurntradeManagmentServetApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/login/**").allowedOrigins("http://localhost:3000/");
//            }
//        };
//    }

//    CommandLineRunner run(IUserService userService){
//        return args -> {
//            userService.saveRole(new AppRole(null,"admin"));
//            userService.saveRole(new AppRole(null,"employee"));
//            userService.saveUser(new AppUser(null,"asa","asija","asija","","fefe","1234",new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"neko","asija","asija","","neko","1234",new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"jasko","Jasmin","Husovic","Pazar","","1234",new ArrayList<>()));
//            userService.addRoleToUser("asa","admin");
//            userService.addRoleToUser("asa","employee");
//            userService.addRoleToUser("jasko","employee");
//        };
//    }
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/products").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
//
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
