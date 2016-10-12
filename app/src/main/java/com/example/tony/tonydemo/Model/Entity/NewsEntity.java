package com.example.tony.tonydemo.Model.Entity;

/**
 * Created by lzy on 2016/8/6.
 */
public class NewsEntity {

    /**
     * ctime : 2016-10-09 10:17
     * title : 中国游客国庆在越南火烧越南盾 被驱逐出境
     * description : 网易国内
     * picUrl : http://cms-bucket.nosdn.127.net/catchpic/F/F8/F84A021F9BD90E6734A1A1C2CC042BC5.jpg?imageView&thumbnail=119y83
     * url : http://news.163.com/16/1009/10/C2U82U2Q00014JB6.html#f=dlist
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
