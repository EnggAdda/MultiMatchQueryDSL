package com.example.multimatchquerydsl.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.multimatchquerydsl.enity.Users;
import com.example.multimatchquerydsl.util.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ESService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Users> multiMatch(String key , List<String> fields ) throws IOException {
        Supplier<Query> supplier  = ESUtil.supplierQueryForMultiMatchQuery(key,fields);
        SearchResponse<Users> searchResponse =
                elasticsearchClient.search(s->s.index("users").query(supplier.get()), Users.class);
        System.out.println("es query" +supplier.get().toString());
        return searchResponse;
    }
}
