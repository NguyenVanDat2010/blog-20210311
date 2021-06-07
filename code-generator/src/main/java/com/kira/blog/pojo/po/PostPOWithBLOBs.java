package com.kira.blog.pojo.po;

public class PostPOWithBLOBs extends PostPO {
    private String content;

    private String articleContent;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}