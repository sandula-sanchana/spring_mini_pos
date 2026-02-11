package edu.ijse.spring_mini_pos.controller;

import edu.ijse.spring_mini_pos.dto.ItemDTO;
import edu.ijse.spring_mini_pos.service.ItemService;
import edu.ijse.spring_mini_pos.service.impl.ItemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/item")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemServiceImpl itemServiceImpl) {
        this.itemService = itemServiceImpl;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveItem(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
    }


    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }


    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable String id) {
        return itemService.getItem(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
    }
}
