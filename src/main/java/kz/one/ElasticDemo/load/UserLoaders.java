package kz.one.ElasticDemo.load;

import kz.one.ElasticDemo.model.User;
import kz.one.ElasticDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLoaders {
    private final UserRepository userRepository;
    private final ElasticsearchOperations operations;

    @PostConstruct
    @Transactional
    public void loadAllUsers(){
        operations.putMapping(User.class);
        System.out.println("Loading data");
        userRepository.save(getUserData());
        System.out.println("Loading complete");
    }

    private List<User> getUserData(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"Daniyar","One",100000L));
        users.add(new User(2L,"Maksat","One",200000L));
        users.add(new User(3L,"Moldir","Halyk",18000L));
        return users;
    }
}
