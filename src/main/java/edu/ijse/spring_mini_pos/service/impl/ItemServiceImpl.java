package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.ItemDTO;
import edu.ijse.spring_mini_pos.entity.Item;
import edu.ijse.spring_mini_pos.respository.ItemRepository;
import edu.ijse.spring_mini_pos.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public void saveItem(ItemDTO itemDTO) {

        if(itemRepository.existsById(itemDTO.getId())){
            throw new RuntimeException("Item already exists!");
        }

        Item item = new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getQty(),
                itemDTO.getPrice()
        );

        itemRepository.save(item);
    }


    @Override
    public void updateItem(ItemDTO itemDTO) {

        if(!itemRepository.existsById(itemDTO.getId())){
            throw new RuntimeException("Item not found!");
        }

        Item item = new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getQty(),
                itemDTO.getPrice()
        );

        itemRepository.save(item);
    }


    @Override
    public List<ItemDTO> getAllItems() {

        return itemRepository.findAll()
                .stream()
                .map(item -> new ItemDTO(
                        item.getId(),
                        item.getName(),
                        item.getQty(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public ItemDTO getItem(String id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getQty(),
                item.getPrice()
        );
    }


    @Override
    public void deleteItem(String id) {

        if(!itemRepository.existsById(id)){
            throw new RuntimeException("Item not found");
        }

        itemRepository.deleteById(id);
    }
}
