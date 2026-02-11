package edu.ijse.spring_mini_pos.service;

import edu.ijse.spring_mini_pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO dto);
    void updateCustomer(CustomerDTO dto);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomer(String id);
    void deleteCustomer(String id);

}
