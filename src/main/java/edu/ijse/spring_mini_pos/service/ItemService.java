package edu.ijse.spring_mini_pos.service;

import edu.ijse.spring_mini_pos.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDTO itemDTO);

    void updateItem(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();

    ItemDTO getItem(String id);

    void deleteItem(String id);
}
