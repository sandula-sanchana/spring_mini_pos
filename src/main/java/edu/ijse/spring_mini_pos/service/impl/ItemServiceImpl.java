package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.ItemDTO;
import edu.ijse.spring_mini_pos.entity.Item;
import edu.ijse.spring_mini_pos.exception.BadRequestException;
import edu.ijse.spring_mini_pos.exception.ResourceNotFoundException;
import edu.ijse.spring_mini_pos.respository.ItemRepository;
import edu.ijse.spring_mini_pos.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        if (itemDTO == null) throw new BadRequestException("Request body is missing");

        if (itemDTO.getId() != null) {
            throw new BadRequestException("ID must be null when creating an item");
        }

        itemRepository.save(modelMapper.map(itemDTO, Item.class));
    }


    @Override
    public void updateItem(ItemDTO itemDTO) {
        if (itemDTO == null) {
            throw new BadRequestException("Request body is missing");
        }
        if (itemDTO.getId() == null) {
            throw new BadRequestException("Item ID is required for update");
        }

        Item existing = itemRepository.findById(itemDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        existing.setName(itemDTO.getName());
        existing.setQty(itemDTO.getQty());
        existing.setPrice(itemDTO.getPrice());
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>() {}.getType());
    }

    @Override
    public ItemDTO getItem(Integer id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        return modelMapper.map(item, ItemDTO.class);
    }

    @Override
    public void deleteItem(Integer id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found");
        }
        itemRepository.deleteById(id);
    }
}
