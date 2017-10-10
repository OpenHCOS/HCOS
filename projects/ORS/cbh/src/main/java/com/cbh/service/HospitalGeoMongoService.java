package com.cbh.service;

import com.cbh.mongo.HospitalGeoResponse;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tommy on 2017/6/9.
 */
@Repository
public interface HospitalGeoMongoService  extends MongoRepository<HospitalGeoMongoService,String> {

   List<HospitalGeoResponse> findByLocationNear(Point p, Distance d);
}
