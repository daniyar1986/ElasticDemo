package kz.one.ElasticDemo.controller;

import kz.one.ElasticDemo.model.Customer;
import kz.one.ElasticDemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;

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
}
