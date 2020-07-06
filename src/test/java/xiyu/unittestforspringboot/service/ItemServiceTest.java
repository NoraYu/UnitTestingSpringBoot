package xiyu.unittestforspringboot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xiyu.unittestforspringboot.DAO.ItemRepository;
import xiyu.unittestforspringboot.Entity.Item;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;
    @Mock
    private ItemRepository itemRepoMock;

    @Test
    public void getallitemsShouldSetValue(){
        when(itemRepoMock.findAll()).thenReturn(Arrays
                .asList(new Item("item1",9,10),
                        new Item("item2",10,10),
                        new Item("item3",5,10)
                ));
        for(Item i :itemService.getallitems()){
            assertEquals(i.getPrice()*i.getQuantity(),i.getValue());
        }
    }



}
