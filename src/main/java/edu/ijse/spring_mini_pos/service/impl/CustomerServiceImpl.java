package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.CustomerDTO;
import edu.ijse.spring_mini_pos.entity.Customer;
import edu.ijse.spring_mini_pos.exception.CustomException;
import edu.ijse.spring_mini_pos.respository.CustomerRepository;
import edu.ijse.spring_mini_pos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

        if (customerDTO == null) {
            throw new CustomException("CUSTOMER DTO NOT FOUND");
        }

        customerRepository.save(modelMapper.map(customerDTO,Customer.class));

    }


    @Override
    public void updateCustomer(CustomerDTO customerDTO) {

        assert customerDTO.getCId() != null;
        if (!customerRepository.existsById(customerDTO.getCId())) {
            throw new RuntimeException("Customer not found");
        }

        customerRepository.save(modelMapper.map(customerDTO,Customer.class));
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return modelMapper.map(customers,new  TypeToken<List<CustomerDTO>>() {}.getType());
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
