package kz.one.ElasticDemo.service;

import kz.one.ElasticDemo.model.Customer;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryDSLService {
    private final ElasticsearchOperations operations;

    public List<Customer> searchMultiField(String firstName, int age){
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("firstName",firstName))
                .must(QueryBuilders.matchQuery("age",age));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return operations.queryForList(nativeSearchQuery, Customer.class);
    }

    public List<Customer> getCustomerSearchData(String firstName){
        String search = ".*"+firstName+".*";
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.regexpQuery("firstName", search))
                .build();
        return operations.queryForList(searchQuery, Customer.class);
    }

    public List<Customer> multiMatchQuery(String text){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(text)
                        .field("firstName")
                .field("lastName").type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .build();
        return operations.queryForList(searchQuery, Customer.class);
    }
}
