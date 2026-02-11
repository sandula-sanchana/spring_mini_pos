package edu.ijse.spring_mini_pos.controller;

import edu.ijse.spring_mini_pos.service.CustomerService;
import edu.ijse.spring_mini_pos.service.impl.CustomerServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Marks this class as a REST controller that handles HTTP requests
// convert class in to a rest controller that returns data as response (json)
@RequestMapping("app/v1/customer") // Class-level annotation used to define the base URL
// for all endpoints within this controller.

@CrossOrigin // Enables Cross-Origin Resource Sharing (CORS)
// allowing frontend applications hosted on different origins
// to communicate with this backend.

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerServiceImpl  customerServiceImpl) {
       this.customerService = customerServiceImpl;
    }



    @PostMapping
    public void saveCustomer(){

        System.out.println("save customer");

    }
}
