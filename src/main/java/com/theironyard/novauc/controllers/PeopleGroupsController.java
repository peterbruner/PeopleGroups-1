package com.theironyard.novauc.controllers;

import com.theironyard.novauc.entities.Person;
import com.theironyard.novauc.services.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Merlin on 3/17/17.
 */
@RestController
public class PeopleGroupsController {
    @Autowired
    PersonRepository people;

    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public List<Person> getUsers() {
        return (List<Person>) people.findAll();
    }

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    public void addUser(@RequestBody Person user) {
        people.save(user);
    }

    @RequestMapping(path = "/person", method = RequestMethod.PUT)
    public void updateUser(@RequestBody Person user) {
        people.save(user);
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        people.delete(id);
    }

    @RequestMapping(path = "/person/{id}", method = RequestMethod.GET)
    public Person getUser(@PathVariable("id") int id) {
        return people.findOne(id);
    }

    @PostConstruct
    void init() {
        if (people.count() == 0) {
            Person p = new Person("Mike","123 something", "some@something.com", "yes", "lots" );
            people.save(p);
        }
    }
}
