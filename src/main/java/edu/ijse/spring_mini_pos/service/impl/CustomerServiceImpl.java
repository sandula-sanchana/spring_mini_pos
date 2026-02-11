package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.CustomerDTO;
import edu.ijse.spring_mini_pos.entity.Customer;
import edu.ijse.spring_mini_pos.respository.CustomerRepository;
import edu.ijse.spring_mini_pos.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
       customerRepository.save(new Customer(customerDTO.getCId(),customerDTO.getCName(),customerDTO.getCAddress()));
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return List.of();
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        return null;
    }

    @Override
    public void deleteCustomer(String id) {

    }
}
