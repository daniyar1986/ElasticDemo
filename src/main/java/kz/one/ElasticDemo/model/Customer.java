package kz.one.ElasticDemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "javatechie", type = "customer", shards = 2)
@Data
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
}
