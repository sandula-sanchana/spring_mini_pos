package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.ItemDTO;
import edu.ijse.spring_mini_pos.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public void saveItem(ItemDTO itemDTO) {

    }

    @Override
    public void updateItem(ItemDTO itemDTO) {

    }

    @Override
    public List<ItemDTO> getAllItems() {
        return List.of();
    }

    @Override
    public ItemDTO getItem(String id) {
        return null;
    }

    @Override
    public void deleteItem(String id) {

    }
}
