package com.products.controller;

import com.products.service.ItemService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {

	@Autowired
    private  ItemService itemService;

    @PostMapping("/approve/{id}")
    public String approveItem(@PathVariable Long id) {
        itemService.approveItem(id);
        return "Item approved.";
    }
}
