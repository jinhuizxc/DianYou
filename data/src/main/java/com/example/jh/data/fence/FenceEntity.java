package com.example.jh.data.fence;

import com.example.jh.data.entity.BaseEntity;

import java.util.List;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 */

public class FenceEntity extends BaseEntity {

    private int id = -1;
    private String name;
    private String type;
    private double lng1;
    private double lat1;
    private double lng2;
    private double lat2;
    private String address;
    private List<String> devices;
    private String radius;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLng1() {
        return lng1;
    }

    public void setLng1(double lng1) {
        this.lng1 = lng1;
    }

    public double getLat1() {
        return lat1;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public double getLng2() {
        return lng2;
    }

    public void setLng2(double lng2) {
        this.lng2 = lng2;
    }

    public double getLat2() {
        return lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    @Override
    public String toString() {
        return "FenceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lng1=" + lng1 +
                ", lat1=" + lat1 +
                ", lng2=" + lng2 +
                ", lat2=" + lat2 +
                ", address='" + address + '\'' +
                ", devices=" + devices +
                ", radius='" + radius + '\'' +
                '}';
    }
}

