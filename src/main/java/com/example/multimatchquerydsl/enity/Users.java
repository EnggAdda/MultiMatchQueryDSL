package com.example.multimatchquerydsl.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "users")
public class Users {

    private Integer userId;
    private String name ;
    private Integer age ;
    private String userDesc;
}
