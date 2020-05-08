package kz.one.ElasticDemo.repository;

import kz.one.ElasticDemo.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
}
