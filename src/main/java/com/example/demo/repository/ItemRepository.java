package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findByCode(int code);
    Item findBy_id(String id);
    boolean existsBy_id(String id);
    long deleteBy_id(String id);
}
