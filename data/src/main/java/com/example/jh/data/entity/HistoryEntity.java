package com.example.jh.data.entity;

/**
 * Created by jinhui on 2017/11/17.
 */

public class HistoryEntity extends BaseEntity {

    private String location_id;
    private String address;
    private String cellid;

    private String lat;
    private String lng;
    private String speed;
    private String bearing;
    private String location_time;

    public HistoryEntity(String lat, String lng, String speed, String bearing, String location_time) {
        this.lat = lat;
        this.lng = lng;
        this.speed = speed;
        this.bearing = bearing;
        this.location_time = location_time;
    }


    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation_time() {
        return location_time;
    }

    public void setLocation_time(String location_time) {
        this.location_time = location_time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getCellid() {
        return cellid;
    }

    public void setCellid(String cellid) {
        this.cellid = cellid;
    }

    @Override
    public String toString() {
        return "location_id='" + location_id + '\'' +
                ", address='" + address + '\'' +
                ", location_time='" + location_time + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", speed='" + speed + '\'' +
                ", bearing='" + bearing + '\'' +
                ", cellid='" + cellid + '\'';
    }
}
