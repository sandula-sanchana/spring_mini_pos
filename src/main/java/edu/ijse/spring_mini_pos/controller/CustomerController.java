package edu.ijse.spring_mini_pos.controller;

import edu.ijse.spring_mini_pos.dto.CustomerDTO;
import edu.ijse.spring_mini_pos.service.CustomerService;
import edu.ijse.spring_mini_pos.service.impl.CustomerServiceImpl;
import edu.ijse.spring_mini_pos.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/customer")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerService = customerServiceImpl;
    }


    @PostMapping
    public ResponseEntity<APIResponse<String>> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return new ResponseEntity(new APIResponse<>(
                201,"customer saved",null
        ),HttpStatus.CREATED);

    }


    @PutMapping
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
    }


    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }


    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
