package edu.ijse.spring_mini_pos.respository;

import edu.ijse.spring_mini_pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
