package com.example.jh.data.article;


import com.example.jh.data.entity.BaseEntity;

/**
 * Created by Administrator on 2017/5/16.
 */

public class ArticleEntity extends BaseEntity {
    private String article_id;
    private String article_content;
    private String article_title;
    private String article_img_src;
    private String article_critime;

    public ArticleEntity(){

    }

    public ArticleEntity(String article_id, String article_content, String article_title, String article_img_src, String article_critime) {
        this.article_id = article_id;
        this.article_content = article_content;
        this.article_title = article_title;
        this.article_img_src = article_img_src;
        this.article_critime = article_critime;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_img_src() {
        return article_img_src;
    }

    public void setArticle_img_src(String article_img_src) {
        this.article_img_src = article_img_src;
    }

    public String getArticle_critime() {
        return article_critime;
    }

    public void setArticle_critime(String article_critime) {
        this.article_critime = article_critime;
    }
}
