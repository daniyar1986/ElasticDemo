package kz.one.ElasticDemo.controller;

import kz.one.ElasticDemo.builder.SearchQueryBuilder;
import kz.one.ElasticDemo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/manual/search")
@RequiredArgsConstructor
public class ManualSearchController {

    private final SearchQueryBuilder searchQueryBuilder;

    @GetMapping(value = "/{text}")
    public List<User> getAllUsers(@PathVariable String text){
        return searchQueryBuilder.getAllUsers(text);
    }

}
