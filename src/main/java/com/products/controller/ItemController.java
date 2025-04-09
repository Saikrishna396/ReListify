package com.products.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.products.model.ItemProduct;
import com.products.model.ItemRepository;

@RestController
@RequestMapping("/api/items")
@CrossOrigin
public class ItemController {

    @Autowired private ItemRepository itemRepository;

   
    private final String uploadDir = "uploads/";

    @PostMapping
    public ResponseEntity<?> addItem(@RequestParam String title,
                                     @RequestParam String description,
                                     @RequestParam Double price,
                                     @RequestParam MultipartFile image) throws IOException {
        // Save image to disk
        String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, filename);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());

        // Save item
        ItemProduct item = new ItemProduct();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        item.setImageUrl("http://localhost:8080/" + uploadDir + filename);
        return ResponseEntity.ok(itemRepository.save(item));
    }

    @GetMapping
    public List<ItemProduct> getAllItems() {
        return itemRepository.findAll();
    }
}
