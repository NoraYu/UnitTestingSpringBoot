package xiyu.unittestforspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiyu.unittestforspringboot.DAO.ItemRepository;
import xiyu.unittestforspringboot.Entity.Item;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    //generate fake data
    public Item fakegetItem(){
        return new Item("GARMIN",270,1);
    }

    //get real data from database
    public List<Item> getallitems(){
        List<Item> list=itemRepository.findAll();
        for(Item item:list){
            item.setValue(item.getPrice()*item.getQuantity());
        }
        return list;
    }


}
