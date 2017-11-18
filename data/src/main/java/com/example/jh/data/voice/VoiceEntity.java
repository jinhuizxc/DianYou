package com.example.jh.data.voice;


import com.example.jh.data.entity.BaseEntity;

/**
 * Created by fflamingogo on 2016/7/28.
 */
public class VoiceEntity extends BaseEntity {
    int id;
    int time;
    int duration;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
