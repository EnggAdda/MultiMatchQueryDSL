package com.example.multimatchquerydsl.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.List;
import java.util.function.Supplier;

public class ESUtil
{
     public static Supplier<Query>  supplierQueryForMultiMatchQuery(String key , List<String> fields){

         Supplier<Query> supplier = ()->Query.of(q->q.multiMatch(multiMatchQuery(key, fields)));
     return supplier;
     }
     public static MultiMatchQuery multiMatchQuery(String key , List<String> fields ){

         val multimatch = new MultiMatchQuery.Builder();
       return   multimatch.query(key).fields(fields).build();
     }

}
