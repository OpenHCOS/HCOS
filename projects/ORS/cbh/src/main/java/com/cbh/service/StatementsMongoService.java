package com.cbh.service;


import com.cbh.mongo.Statements;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tommy on 2017/4/22.
 */
@Repository
public interface StatementsMongoService extends MongoRepository<Statements, String>  {


    public Statements findById(String id);
    public Statements findByText(String text);


    @Query("{ 'in_response_to.text' : { $regex: ?0 } }")
    public List<Statements> findStatementsByRegexpResponse(String regexp);

}
