package com.cbh.web;
import com.cbh.entity.HospitalInfo;
import com.cbh.geo.util.GeoUtil;
import com.cbh.mongo.HospitalLocation;
import com.cbh.mongo.location.GeoLocation;
import com.cbh.service.HospitalLocationMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@RequestMapping("/hospitalinfoes")
@Controller
@RooWebScaffold(path = "hospitalinfoes", formBackingObject = HospitalInfo.class)
public class HospitalInfoController {

    @Autowired
    HospitalLocationMongoService hospitalLocationMongoService;

    @RequestMapping(value = "geo",produces = "text/html")
    @ResponseBody
    public String listGeo(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        List<HospitalInfo> hospitalInfos=null;

        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            hospitalInfos=HospitalInfo.findHospitalInfoNullGeoEntries(firstResult, sizeNo);
            uiModel.addAttribute("hospitalinfoes", hospitalInfos);
            float nrOfPages = (float) HospitalInfo.countHospitalInfoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            hospitalInfos=HospitalInfo.findHospitalInfoNullGeoEntries(0, 10000);
            uiModel.addAttribute("hospitalinfoes", hospitalInfos);
        }

        for(HospitalInfo hospitalInfo:hospitalInfos){
            System.out.println(hospitalInfo.getHpName());
            GeoUtil geoUtil=new GeoUtil();
            try {
                GeoLocation geoLocation=geoUtil.googleLocation(hospitalInfo.getAddress());
                hospitalInfo.setLongtitude(String.valueOf(geoLocation.getResults().get(0).getGeometry().getLocation().getLng()));
                hospitalInfo.setLatitude(String.valueOf(geoLocation.getResults().get(0).getGeometry().getLocation().getLat()));
                hospitalInfo.merge();
                HospitalLocation hospitalLocation=new HospitalLocation();
                hospitalLocation.setGeoLocation(geoLocation);
                hospitalLocation.setHosNo(hospitalInfo.getHpCode());
                hospitalLocation.setHospitalName(hospitalInfo.getHpName());
                hospitalLocationMongoService.save(hospitalLocation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        return "hospitalinfoes/list";
    }
}
