package kz.one.ElasticDemo.repository;

import kz.one.ElasticDemo.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, Long> {
   List<User> save(List<User> users);

   List<User> findByName(String text);

   List<User> findBySalary(Long salary);
}


