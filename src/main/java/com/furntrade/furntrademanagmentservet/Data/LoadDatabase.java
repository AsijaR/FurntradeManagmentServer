package com.furntrade.furntrademanagmentservet.Data;

import com.furntrade.furntrademanagmentservet.Models.*;
import com.furntrade.furntrademanagmentservet.Repositories.*;
import com.furntrade.furntrademanagmentservet.Service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, IUserService userService, OrderRepository orderRepository, CustomerRepository customerRepository) {

        return args -> {
//            userService.saveRole(new AppRole(null,"admin"));
//            userService.saveRole(new AppRole(null,"employee"));
//            userService.saveUser(new AppUser(null,"asa","asija","asija","","fefe","1234",new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"neko","asija","asija","","neko","1234",new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"jasko","Jasmin","Husovic","Pazar","","1234",new ArrayList<>()));
//            userService.addRoleToUser("asa","admin");
//            userService.addRoleToUser("asa","employee");
//            userService.addRoleToUser("jasko","employee");
//            AppUser user=new AppUser();
//            user.setUsername("asa");
//            user.setPassword("1234");
//            AppRole roles=new AppRole();
//            roles.setName("APP_ADMIN");
//
//            log.info("Ubacujem Customer 1 " + appUserRepository.save(user));
//            log.info("Ubacujem Customer 1 " + roleRepository.save(roles));

//            Customer c1=new Customer("Conforama","Novi Pazar","Novi Pazar","Srbija",36300,"Asija Ramovic","asija@gmail.com");
//            Customer c2=new Customer("LCW","Sava Kovacevic 7","Beograd","Srbija",11000);
//            log.info("Ubacujem Customer 1 " + customerRepository.save(c1));
//            log.info("Ubacujem Customer 2 " + customerRepository.save(c2));
//
//            Product p1=new Product("galileo","model","black","cotton",427);
//            Product p2=new Product("ella","model","green","cotton",348);
//            Product p3=new Product("sara","model","yellow","cotton",402);
//            Product p4=new Product("paris","model","black","cotton",277);
//            Product p5=new Product("milano","model","black","cotton",124);
//            Product p6=new Product("baun","model","black","cotton",358);
//            Product p7=new Product("kasandta","model","black","cotton",420);
//            Product p8=new Product("nela","model","black","cotton",381);
//            log.info("Ubacujem Product 1 " + productRepository.save(p1));
//            log.info("Ubacujem Product 2 " + productRepository.save(p2));
//            log.info("Ubacujem Product 3 " + productRepository.save(p3));
//            log.info("Ubacujem Product 4 " + productRepository.save(p4));
//            log.info("Ubacujem Product 4 " + productRepository.save(p5));
//            log.info("Ubacujem Product 4 " + productRepository.save(p6));
//            log.info("Ubacujem Product 4 " + productRepository.save(p7));
//            log.info("Ubacujem Product 4 " + productRepository.save(p8));
//
//            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
//            Date customUtilDate = dateFormatter.parse("30-01-2020");
//            Order o1=new Order(c1,customUtilDate,"beleska 1","beleska 2");
//            o1.addProduct(p1,7);
//            o1.addProduct(p2,4);
//            o1.addProduct(p3,2);
//
//            Order o2=new Order(c2,customUtilDate,"beleska 3","beleska 8");
//            o2.addProduct(p3,10);
//            o2.addProduct(p4,15);
//
//            Order o3=new Order(c1,customUtilDate,"beleska 4","beleska 8");
//            o3.addProduct(p7,8);
//            o3.addProduct(p3,15);
//
//            Order o4=new Order(c2,customUtilDate,"beleska 5","beleska 8");
//            o4.addProduct(p6,12);
//            o4.addProduct(p7,7);
//
//            Order o5=new Order(c1,customUtilDate,"beleska 7","beleska 8");
//            o5.addProduct(p1,5);
//            o5.addProduct(p8,4);
//            o5.addProduct(p4,6);
//
//            Order o6=new Order(c2,customUtilDate,"beleska 8","beleska 8");
//            o6.addProduct(p5,4);
//
//            log.info("Ubacujem Order 1 " + orderRepository.save(o1));
//            log.info("Ubacujem Order 2 " + orderRepository.save(o2));
//            log.info("Ubacujem Order 3 " + orderRepository.save(o3));
//            log.info("Ubacujem Order 4 " + orderRepository.save(o4));
//            log.info("Ubacujem Order 5 " + orderRepository.save(o5));
//            log.info("Ubacujem Order 6 " + orderRepository.save(o6));
        };
    }
}
