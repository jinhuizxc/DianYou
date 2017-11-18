package com.example.jh.data.entity;

/**
 * Created by Administrator on 2016/12/7.
 */

public class UsersEntity extends BaseEntity {
    private String id;
    private String name;
    private String nick;


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
