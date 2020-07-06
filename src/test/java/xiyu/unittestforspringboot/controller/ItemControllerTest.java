package xiyu.unittestforspringboot.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import xiyu.unittestforspringboot.DAO.ItemRepository;
import xiyu.unittestforspringboot.Entity.Item;
import xiyu.unittestforspringboot.service.ItemService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @Autowired
    //@InjectMocks
    private MockMvc mockMvc;

    @MockBean
    private ItemService mock;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void getItemShouldWork() throws Exception{
        RequestBuilder request=MockMvcRequestBuilders.get("/item")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":  0,\"name\":\"Apple Watch\",\"price\":399.0,\"quantity\":1}"))
                .andReturn();
        //JSONAssert.assertEquals(expected,actual,false);
    }

    @Test
    public void getItemfromRepositoryShouldWork() throws Exception{
        Item i=new Item("Apple Watch",399,1);
        i.setId(1);
        List<Item> list=spy(List.class);
        when(itemRepository.findAll()).thenReturn(list);
        list.add(i);
        when(itemRepository.findAll().get(0)).thenReturn(i);
        RequestBuilder request=MockMvcRequestBuilders.get("/itemrepo")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":  1,\"name\":\"Apple Watch\",\"price\":399.0,\"quantity\":1}"))
                .andReturn();
        //JSONAssert.assertEquals(expected,actual,false);
    }

    @Test
    public void getItemFromServiceShouldWork()throws Exception{
        when(mock.fakegetItem()).thenReturn(new Item("GARMIN",270,1));
        RequestBuilder request= MockMvcRequestBuilders.get("/itemfromservice")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{\"id\":0,\"name\":\"GARMIN\",\"price\":270.0,\"quantity\":1}"))
                .andReturn();
    }

    @Test
    public void getallitemsShoulWork() throws Exception{

        when(mock.getallitems()).thenReturn(Arrays.asList(new Item("Apple Watch",399,1),new Item("GARMIN",270,1)));
        //when(mock.getallitems()).thenReturn(Arrays.asList(new Item("Apple Watch",399,1)));

        RequestBuilder request=MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":  0,name:\"Apple Watch\",price:399.0,quantity:1},{\"id\":  0,name:GARMIN,price:270.0,quantity:1}]"))
                //.andExpect(content().json("[{\"id\":  1,\"name\":\"Apple Watch\",\"price\":399.0,\"quantity\":1}]"))

                .andReturn();
        //assertEquals("",result.getResponse().getContentAsString());
    }

    @Test
    public void createNewItemShouldWork() throws Exception{
        //Item item=new Item("newItem",10,10);
        RequestBuilder request=MockMvcRequestBuilders.post("/item")
                .content("{\"name\":\"newitem\",\"price\":100.0,\"quantity\":10}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request).andExpect(status().isCreated())
                //.andExpect(header().string("location",containsString("/item/")))
                .andReturn();
    }


    @Test
    public void updateItemShouldWork() throws Exception{
        //Item item=new Item("newItem",10,10);
        when(itemRepository.findById(1l)).thenReturn(Optional.of(new Item()));

        RequestBuilder request=MockMvcRequestBuilders.put("/item/1")
                .content("{\"name\":\"updateditem\",\"price\":1.0,\"quantity\":10}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request).andExpect(status().isOk())
                //.andExpect(header().string("location",containsString("/item/")))
                .andReturn();
    }

    @Test
    public void deleteShouldWork() throws Exception{
        when(itemRepository.findById(1l)).thenReturn(Optional.of(new Item()));

        RequestBuilder request=MockMvcRequestBuilders.delete("/item/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request).andExpect(status().isNoContent())
                //.andExpect(header().string("location",containsString("/item/")))
                .andReturn();
    }


}
