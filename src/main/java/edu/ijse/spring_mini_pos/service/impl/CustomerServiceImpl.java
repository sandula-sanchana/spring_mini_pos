package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.CustomerDTO;
import edu.ijse.spring_mini_pos.entity.Customer;
import edu.ijse.spring_mini_pos.exception.BadRequestException;
import edu.ijse.spring_mini_pos.exception.ResourceNotFoundException;
import edu.ijse.spring_mini_pos.respository.CustomerRepository;
import edu.ijse.spring_mini_pos.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new BadRequestException("Request body is missing");
        }
        customerRepository.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new BadRequestException("Request body is missing"); // 400
        }
        if (customerDTO.getCId() == null) {
            throw new BadRequestException("Customer ID is required for update"); // 400
        }

        Customer existing = customerRepository.findById(customerDTO.getCId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found")); // 404

        existing.setCName(customerDTO.getCName());
        existing.setCAddress(customerDTO.getCAddress());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll(); // never null
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found")); // 404
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(int id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found"); // 404
        }
        customerRepository.deleteById(id);
    }
}
