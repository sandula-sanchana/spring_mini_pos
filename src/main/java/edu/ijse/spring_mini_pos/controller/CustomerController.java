package edu.ijse.spring_mini_pos.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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
}
