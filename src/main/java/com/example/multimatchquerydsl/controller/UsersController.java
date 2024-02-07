package com.example.multimatchquerydsl.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.multimatchquerydsl.enity.Users;

import com.example.multimatchquerydsl.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController
{
     @Autowired
     private ESService esService;

     @GetMapping("/multiMatch/{key}")
    public List<Users> multiMatch(@PathVariable String key , @RequestParam(value = "field1",  defaultValue = "name") String field1,
                                  @RequestParam(value = "field2", defaultValue = "userDesc") String field2) throws IOException {
        List<String>  fields = new ArrayList<String>();
        fields.add(field1);
        fields.add(field2);
         SearchResponse<Users>  searchResponse =
                 esService.multiMatch(key,fields);
        List<Hit<Users>> listOfHits = searchResponse.hits().hits();
        List<Users>  listOfUsers = new ArrayList <>();

          for(Hit<Users>  hit : listOfHits){
              listOfUsers.add(hit.source());
          }
   return listOfUsers;
     }

}
