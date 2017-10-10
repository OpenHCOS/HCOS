package com.cbh.service;


import com.cbh.mongo.LineMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tommy on 2017/4/23.
 */
@Repository
public interface LineMessageMongoService extends MongoRepository<LineMessage,String> {
}
