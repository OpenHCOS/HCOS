package com.cbh.web;

import com.cbh.entity.HospitalInfo;
import com.cbh.mongo.HospitalGeoResponse;
import com.cbh.service.HospitalGeoMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Tommy on 2017/6/9.
 */
@RequestMapping("/mongo")
@Controller
public class TestController {

    @Autowired
    HospitalGeoMongoService hospitalGeoMongoService;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, produces = "text/html")
    public String create(Model uiModel, HttpServletRequest httpServletRequest) {
        Point DUS = new Point( 121.48, 24.99 );
        List<HospitalGeoResponse> locations = hospitalGeoMongoService.findByLocationNear(DUS, new Distance(0.5, Metrics.KILOMETERS) );
        Query query = new Query(Criteria.where("category").regex("內科"));
        NearQuery nearQuery = NearQuery.near(DUS).maxDistance(new Distance(10, Metrics.KILOMETERS));
        nearQuery.query(query);
        nearQuery.num(10);
        GeoResults<HospitalGeoResponse> data = mongoTemplate.geoNear(nearQuery, HospitalGeoResponse.class);
        for(GeoResult<HospitalGeoResponse> hospitalGeoResponse:data){
            System.out.println(hospitalGeoResponse.getContent().getAddress());
            System.out.println(hospitalGeoResponse.getContent().getName());
        }
        return "redirect:/hospitalinfoes/";
    }
}
