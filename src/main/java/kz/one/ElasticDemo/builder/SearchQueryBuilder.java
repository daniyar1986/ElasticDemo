package kz.one.ElasticDemo.builder;

import kz.one.ElasticDemo.model.User;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchQueryBuilder {
    private final ElasticsearchOperations template;

    public List<User> getAllUsers(String text) {
        QueryBuilder query = QueryBuilders
                .boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("name")
                        .field("teamName")
                ).should(
                        QueryBuilders.queryStringQuery("*"+text+"*")
                        .lenient(true)
                        .field("name")
                        .field("teamName")
                );

         NativeSearchQuery build =
                new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<User> users = template.queryForList(build, User.class);
        return users;

    }

    public List<User> getAll(){
        BoolQueryBuilder should = QueryBuilders.boolQuery().should(QueryBuilders.matchAllQuery());
        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(should).build();
        List<User> users = template.queryForList(build, User.class);
        return users;
    }

}
