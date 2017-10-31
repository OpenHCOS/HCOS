package com.cbh.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tommy on 2017/6/10.
 */
@Entity
@Table
public class PersonRecord {


    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("orderDis");

    public static final EntityManager entityManager() {
        EntityManager em = new Disease().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;


    private String lineId;

    private String currentStatus;

    private Double longtitude;

    private Double latitude;

    private String userName;

    private String symptom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PersonRecord attached = PersonRecord.findPersonRecord(this.id);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

    @Transactional
    public PersonRecord merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PersonRecord merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public static PersonRecord findPersonRecord(Integer id) {
        if (id == null) return null;
        return entityManager().find(PersonRecord.class, id);
    }


    public static PersonRecord findPersonRecord(String lineId) {
        if (lineId== null) return null;
        String jpaQuery="SELECT o FROM PersonRecord o where o.lineId=:lineId";
        Query query=entityManager().createQuery(jpaQuery, PersonRecord.class);
        query.setParameter("lineId",lineId);

        return (PersonRecord) query.getSingleResult();
    }


}
