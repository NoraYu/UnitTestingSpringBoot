package xiyu.unittestforspringboot.controller;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import xiyu.unittestforspringboot.spike.JsonAssertTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ItemControllerITTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void contextLoads() throws Exception{
        String response= this.restTemplate.getForObject("/",String.class);
        //if there's also a sql script exists in real code. both real and test data will be tested
        JSONAssert.assertEquals("[{id:100,name:macbook,price:3000,quantity:10},{\"id\":  101,name:soap,price:2.0,quantity:5},{\"id\":  102,name:blueberry,price:8.0,quantity:1}," +
                "{\"id\":1,\"name\":\"watch\",\"price\":337.0,\"quantity\":8,\"value\":2696.0},{\"id\":2,\"name\":\"vivo active 4\",\"price\":267.0,\"quantity\":10,\"value\":2670.0}]",response,false);
    }
}
