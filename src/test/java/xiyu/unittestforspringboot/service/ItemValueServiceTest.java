package xiyu.unittestforspringboot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xiyu.unittestforspringboot.Entity.Item;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemValueServiceTest {
    @InjectMocks
    private ItemValueService itemValueService;
    @Mock
    private ItemService itemServicemock;
    @Test
    public void calculateSumShouldwork(){
        //ItemService is tested for set value when getallitems() is called. So i use an overloaded constructor to se the value to mock
        when(itemServicemock.getallitems()).thenReturn(Arrays
                .asList(new Item("item1",9,10,0),
                        new Item("item2",10,10,0),
                        new Item("item3",5,10,0)
                ));
        assertEquals((double)(90+100+50),itemValueService.calculateSum());
    }


}
