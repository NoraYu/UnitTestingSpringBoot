package xiyu.unittestforspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xiyu.unittestforspringboot.DAO.ItemRepository;
import xiyu.unittestforspringboot.Entity.Item;
import xiyu.unittestforspringboot.service.ItemService;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;
    @GetMapping("/item")
    public Item getItem(){
        Item i=new Item("Apple Watch",399,1);
        itemRepository.save(i);
        //return itemRepository.findAll();
        return i;
    }

    @GetMapping("/itemrepo")
    public Item getItemfromrepo(){
        Item i=new Item("Apple Watch",399,1);
        itemRepository.save(i);
        //return itemRepository.findAll();
        return itemRepository.findAll().get(0);
    }

    @GetMapping("/itemfromservice")
    public Item getItemfromService(){
        return itemService.fakegetItem();
    }

    @GetMapping("/")
    public List<Item> allitems(){
        return itemService.getallitems();
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getitem(@PathVariable("id") long id){
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item _item = itemRepository
                    .save(new Item(item.getName(), item.getPrice(), item.getQuantity()));
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<Item> updateItems(@PathVariable long id,@RequestBody Item item){
        try{Item itemData = itemRepository.findById(id).get();
            itemData.setName(item.getName());
            itemData.setPrice(item.getPrice());
            itemData.setQuantity(item.getQuantity());
            return new ResponseEntity<>(itemRepository.save(itemData), HttpStatus.OK);}
        catch (NullPointerException  e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
