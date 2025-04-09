package com.products.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.model.ItemProduct;
import com.products.model.ItemRepository;
import com.products.model.User;
import com.products.model.UserRepository;

import java.util.List;

@Service

public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository,UserRepository userRepository) {
    	this.itemRepository=itemRepository;
    	this.userRepository=userRepository;
    }

    public ItemProduct addItem(String username, ItemProduct item) {
        User user = userRepository.findByUsername(username).orElseThrow();
        item.setSeller(user);
        return itemRepository.save(item);
    }

    public List<ItemProduct> getAllApprovedItems() {
        return itemRepository.findByApproved(true);
    }

    public List<ItemProduct> getPendingItems() {
        return itemRepository.findByApproved(false);
    }

    public void approveItem(Long id) {
        ItemProduct item = itemRepository.findById(id).orElseThrow();
        item.setApproved(true);
        itemRepository.save(item);
    }
}
