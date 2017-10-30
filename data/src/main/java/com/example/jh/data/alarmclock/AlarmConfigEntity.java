package com.example.jh.data.alarmclock;


import com.example.jh.data.entity.BaseEntity;

/**
 * Created by Administrator on 2017/5/18.
 */

public class AlarmConfigEntity extends BaseEntity {
    private int index;
    private String begin;
    private String about;
    private String repeat;

    public AlarmConfigEntity(int index, String begin, String about, String repeat) {
        this.index = index;
        this.begin = begin;
        this.about = about;
        this.repeat = repeat;
    }

    public int getIndex() {
        return index;
    }

    public String getBegin() {
        return begin;
    }

    public String getAbout() {
        return about;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "AlarmClockEntity{" +
                "index=" + index +
                ", begin='" + begin + '\'' +
                ", about='" + about + '\'' +
                ", repeat='" + repeat + '\'' +
                '}';
    }
}
