package com.example.jh.data.user;


import com.example.jh.data.entity.BaseEntity;

/**
 * Created by fflamingogo on 2016/7/18.
 */
public class TokenEntity extends BaseEntity {

    private String token;
    private String alias;
    private String user_nick;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "token='" + token + '\'' +
                ", alias='" + alias + '\'' +
                ", user_nick='" + user_nick + '\'' +
                '}';
    }
}
