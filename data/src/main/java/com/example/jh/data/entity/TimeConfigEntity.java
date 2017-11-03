package com.example.jh.data.entity;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

public class TimeConfigEntity {

    private int index;
    private String begin;
    private String end;
    private String repeat;

    public TimeConfigEntity(int index, String begin, String end, String repeat) {
        this.index = index;
        this.begin = begin;
        this.end = end;
        this.repeat = repeat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "TimeConfigEntity{" +
                "index=" + index +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", repeat='" + repeat + '\'' +
                '}';
    }
}
