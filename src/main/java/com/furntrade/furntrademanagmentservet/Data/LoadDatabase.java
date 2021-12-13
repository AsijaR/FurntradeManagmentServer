package com.furntrade.furntrademanagmentservet.Data;

import com.furntrade.furntrademanagmentservet.Models.Customer;
import com.furntrade.furntrademanagmentservet.Models.Order;
import com.furntrade.furntrademanagmentservet.Models.Product;
import com.furntrade.furntrademanagmentservet.Repositories.CustomerRepository;
import com.furntrade.furntrademanagmentservet.Repositories.OrderRepository;
import com.furntrade.furntrademanagmentservet.Repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {

        return args -> {
            Customer c1=new Customer("Conforama","Novi Pazar","Novi Pazar","Srbija",36300,"Asija Ramovic","asija@gmail.com");
            Customer c2=new Customer("LCW","Sava Kovacevic 7","Beograd","Srbija",11000);
            log.info("Ubacujem Customer 1 " + customerRepository.save(c1));
            log.info("Ubacujem Customer 2 " + customerRepository.save(c2));

            Product p1=new Product("galileo","model","black","cotton",427);
            Product p2=new Product("ella","model","green","cotton",348);
            Product p3=new Product("sara","model","yellow","cotton",402);
            Product p4=new Product("paris","model","black","cotton",368);
            log.info("Ubacujem Product 1 " + productRepository.save(p1));
            log.info("Ubacujem Product 2 " + productRepository.save(p2));
            log.info("Ubacujem Product 3 " + productRepository.save(p3));
            log.info("Ubacujem Product 4 " + productRepository.save(p4));


            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            Date customUtilDate = dateFormatter.parse("30-01-2020");
            Order o1=new Order(c1,customUtilDate,"beleska 1","beleska 2");
            o1.addProduct(p1,7);
            o1.addProduct(p2,4);
            o1.addProduct(p3,2);
            Order o2=new Order(c2,customUtilDate,"beleska 8","beleska 8");
            o2.addProduct(p3,10);
            o2.addProduct(p4,15);
            log.info("Ubacujem Order 1 " + orderRepository.save(o1));
            log.info("Ubacujem Order 2 " + orderRepository.save(o2));


        };
    }
}
