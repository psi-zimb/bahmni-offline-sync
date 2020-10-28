package org.bahmni.module.bahmniOfflineSync.eventLog;

import javax.persistence.Column;
import java.util.Date;

public class EventLog {

    private String category;

    private Integer id;

    private String uuid;

    private Date timestamp;

    private String object;

    private String parentUuid;

    private String province;

    private String district;

    private String facility;

    public EventLog(String uuid, String category, Date timestamp, String object,  String filter, String parentUuid) {
        this.uuid = uuid;
        this.category = category;
        this.timestamp = timestamp;
        this.object = object;
        this.parentUuid = parentUuid;
        this.filter = filter;
    }

    private String filter;

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getFilter() {
        return filter;
    }

    public String getObject() {
        return object;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }
}
