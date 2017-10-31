package com.cbh.entity;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "hospital_info")
@RooDbManaged(automaticallyDelete = true)
public class HospitalInfo {

    @Column(name="longtitude")
    private String longtitude;

    @Column(name="latitude")
    private String latitude;

    public static List<HospitalInfo> findHospitalInfoNullGeoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM HospitalInfo o where o.longtitude is null ", HospitalInfo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
