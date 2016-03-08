package com.yangyang.recycylerviewcardview;

/**
 * Created by asus on 2016/3/8.
 */
public class News {
    private String iconUrl;
    private String Title;
    private String Content;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getIconUrl() {

        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
