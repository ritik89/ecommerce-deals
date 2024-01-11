package com.ritik.ecommerce.controller;

import com.ritik.ecommerce.model.GenericItem;
import com.ritik.ecommerce.repository.DealRepository;
import com.ritik.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @PostMapping(value = "/item")
    public String save(@RequestBody GenericItem item) {
        itemRepository.save(item);
        return "successfully saved item";
    }

    @GetMapping(value = "/item")
    public GenericItem getItem(@RequestParam(name = "id") Long id) {
        return itemRepository.findById(id).get();
    }
}
