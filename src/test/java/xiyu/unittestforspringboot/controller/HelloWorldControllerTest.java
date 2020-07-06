package xiyu.unittestforspringboot.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest { 

@Autowired
private MockMvc mockMvc;
@Test
public void hello_basic() throws Exception {
    //call url "/hello-world"
    RequestBuilder request= MockMvcRequestBuilders.get("/helloworld")
            .accept(MediaType.APPLICATION_JSON);

    MvcResult result=mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("hello world"))
            .andReturn();
    //verify return string
    assertEquals("hello world",result.getResponse().getContentAsString());

} 


} 
