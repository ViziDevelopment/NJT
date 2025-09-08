/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.mavenproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author vanja
 */
@SpringBootApplication 
/*
•	@Configuration 
        @EnableAutoConfiguration 
        @ComponentScan 
//@Component, @Service, @Repository, @Controller
*/ 
public class FoodDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);
    }
}
