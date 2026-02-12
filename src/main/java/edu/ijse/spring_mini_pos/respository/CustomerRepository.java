package edu.ijse.spring_mini_pos.respository;

import edu.ijse.spring_mini_pos.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}
