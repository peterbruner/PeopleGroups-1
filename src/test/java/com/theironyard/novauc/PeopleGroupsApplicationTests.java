package com.theironyard.novauc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.novauc.controllers.PeopleGroupsController;
import com.theironyard.novauc.entities.Person;
import com.theironyard.novauc.services.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
@SpringBootTest
public class PeopleGroupsApplicationTests {

    @Autowired
    WebApplicationContext wap;

    @Autowired
    PersonRepository persons;

    MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
    }

    //???
//    @Test
//	public void contextLoads() {
//	}

    //String name, String address, String email, String employment, String education

    @Test
    public void addUser() throws Exception {
        Person person = new Person();
        person.setName("prakash");
        person.setAddress("123 nowhere");
        person.setEmail("prakash@nowhere");
        person.setEmployment("theironyard");
        person.setEducation("masters");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/person")
                .content(json)
                .contentType("application/json")
        );
        Assert.assertFalse(persons.count() == 0);
        Assert.assertTrue(persons.count() == 2);
    }

    @Test
    public void updateUser() throws Exception {
        Assert.assertTrue(persons.findOne(1).getEmail().equals("some@something.com"));
        Person person = new Person();
        person.setId(1);
        person.setEmail("your@mom.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/person/1")
                .content(json)
                .contentType("application/json")
        );
        Assert.assertTrue(persons.findOne(1).getEmail().equals("your@mom.com"));
    }

    @Test
    public void deleteUser() throws Exception {
        long tableSize = persons.count();
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/1"));
        Assert.assertTrue(tableSize > persons.count());
    }

    @Test
    public void getUsers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(persons.findAll());
        mockMvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

}
