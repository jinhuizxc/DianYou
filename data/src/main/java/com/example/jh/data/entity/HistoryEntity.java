package com.example.jh.data.entity;

/**
 * Created by jinhui on 2017/11/17.
 */

public class HistoryEntity extends BaseEntity {

    private String id;
    private String address;
    private int time;
    private String lat;
    private String lng;
    private String speed;
    private String bearing;
    private String cellid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
        return "HistoryEntity{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", time=" + time +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", speed='" + speed + '\'' +
                ", bearing='" + bearing + '\'' +
                ", cellid='" + cellid + '\'' +
                '}';
    }
}
