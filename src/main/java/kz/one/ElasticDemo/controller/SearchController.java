package kz.one.ElasticDemo.controller;

import kz.one.ElasticDemo.model.User;
import kz.one.ElasticDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
@RequiredArgsConstructor
public class SearchController {

    private final UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> searchAll() {
        List<User> usersList = new ArrayList<>();
        Iterable<User> users = userRepository.findAll();
        users.forEach(usersList::add);
        return usersList;
    }

    @GetMapping(value = "/name/{text}")
    public List<User> searchName(@PathVariable final String text) {
        return userRepository.findByName(text);
    }


    @GetMapping(value = "/salary/{salary}")
    public List<User> searchSalary(@PathVariable final Long salary) {
        return userRepository.findBySalary(salary);
    }
}
