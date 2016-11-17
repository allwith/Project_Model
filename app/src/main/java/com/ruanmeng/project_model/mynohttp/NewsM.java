package com.ruanmeng.project_model.mynohttp;

import java.util.List;

/**
 * Created by macbook on 16/6/3.
 */
public class NewsM {

    private String msgcode;
    private String msg;

    public List<NewsInfo> getData() {
        return data;
    }

    public void setData(List<NewsInfo> data) {
        this.data = data;
    }

    public String getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(String msgcode) {
        this.msgcode = msgcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<NewsInfo>data;

    public class NewsInfo{

        private String aid;
        private String date;
        private String nid;
        private String info;
        private String small_logo1;
        private String logo1;
        private String logo2;
        private String logo3;
        private String title;
        private String type;


        public String getSmall_logo1() {
            return small_logo1;
        }

        public void setSmall_logo1(String small_logo1) {
            this.small_logo1 = small_logo1;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getLogo1() {
            return logo1;
        }

        public void setLogo1(String logo1) {
            this.logo1 = logo1;
        }

        public String getLogo2() {
            return logo2;
        }

        public void setLogo2(String logo2) {
            this.logo2 = logo2;
        }

        public String getLogo3() {
            return logo3;
        }

        public void setLogo3(String logo3) {
            this.logo3 = logo3;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String typeid;
    }



}
