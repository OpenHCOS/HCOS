package com.cbh.service;

import com.cbh.mongo.HospitalLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Tommy on 2017/8/9.
 */
public interface HospitalLocationMongoService extends MongoRepository<HospitalLocation,String> {
}
