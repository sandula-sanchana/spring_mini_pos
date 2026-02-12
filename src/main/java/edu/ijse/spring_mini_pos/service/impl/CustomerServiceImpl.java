package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.CustomerDTO;
import edu.ijse.spring_mini_pos.entity.Customer;
import edu.ijse.spring_mini_pos.respository.CustomerRepository;
import edu.ijse.spring_mini_pos.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void saveCustomer(CustomerDTO customerDTO) {


        Customer customer = new Customer(
                0, // ✅ let DB generate it
                customerDTO.getCName(),
                customerDTO.getCAddress()
        );

        customerRepository.save(customer);
    }


    @Override
    public void updateCustomer(CustomerDTO customerDTO) {

        if (!customerRepository.existsById(customerDTO.getCId())) {
            throw new RuntimeException("Customer not found");
        }

        Customer customer = new Customer(
                customerDTO.getCId(),
                customerDTO.getCName(),
                customerDTO.getCAddress()
        );

        customerRepository.save(customer);
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(
                        customer.getCId(),
                        customer.getCName(),
                        customer.getCAddress()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public CustomerDTO getCustomer(int id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerDTO(
                customer.getCId(),
                customer.getCName(),
                customer.getCAddress()
        );
    }


    @Override
    public void deleteCustomer(int id) {

        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }

        customerRepository.deleteById(id);
    }
}
