package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@RequestParam("name") String name,
                                  @RequestParam("count") String count,
                                  @RequestParam("count") String stock) {

        Item item = new Item();
        item.setName(name);
        item.setCode(Integer.parseInt(count));
        item.setCount(Integer.parseInt(stock));
        Item result = itemRepository.save(item);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List> getItemByCode() {
        List<Item> itemList = itemRepository.findAll();
        if (itemList == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/getItem/{id}")
    public Item getRecipeById(@PathVariable (value = "id") String _id){
        Item result = itemRepository.findBy_id(_id);
        if (result == null){
            return null;
        }
        return result;
        /*if (result == null){
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("", HttpStatus.OK);*/
    }

    @PutMapping("/updateItem/{id}")
    public ResponseEntity<String> updateItem(@PathVariable (value = "id") String _id,
                                             @RequestParam("name") String name,
                                             @RequestParam("code") int code,
                                             @RequestParam("count") int count){
        if (!itemRepository.existsBy_id(_id)){
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        Item temp = itemRepository.findBy_id(_id);
        if (name.equals("")) {
            temp.setName(temp.getName());
        }else {
            temp.setName(name);
        }
        temp.setCode(code);
        temp.setCount(count);
        itemRepository.save(temp);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable String id){
        Item temp = itemRepository.findBy_id(id);
        if (temp == null){
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
