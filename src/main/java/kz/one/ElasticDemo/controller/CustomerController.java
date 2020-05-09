package kz.one.ElasticDemo.controller;

import kz.one.ElasticDemo.model.Customer;
import kz.one.ElasticDemo.repository.CustomerRepository;
import kz.one.ElasticDemo.service.QueryDSLService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final QueryDSLService queryDSLService;

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("/all")
    public Iterable<Customer> findAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/findByFName/{firstName}")
    public List<Customer> findByFirstName(@PathVariable final String firstName){
        return customerRepository.findByFirstName(firstName);
    }

    @GetMapping("/searchMultiField/{firstName}/{age}")
    public List<Customer> searchMultiField(@PathVariable String firstName, @PathVariable int age){
        return queryDSLService.searchMultiField(firstName, age);
    }
    @GetMapping("/customerSearch/{firstName}")
    public List<Customer> getCustomerByField(@PathVariable String firstName){
        return queryDSLService.getCustomerSearchData(firstName);
    }

    @GetMapping("/search/{text}")
    public List<Customer> doMultiMatchQuery(@PathVariable final String text){
        return queryDSLService.multiMatchQuery(text);
    }
}
