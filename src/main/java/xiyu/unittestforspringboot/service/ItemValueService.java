package xiyu.unittestforspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiyu.unittestforspringboot.Entity.Item;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemValueService {
    @Autowired
    ItemService itemService;


    public double calculateSum(){
        double sum=0;
        for(Item item:itemService.getallitems()){
            sum+=item.getValue();
        }
        return sum;

    }
}
