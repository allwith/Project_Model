package com.ruanmeng.project_model.guide;

import java.util.List;

/**
 * Created by macbook on 16/5/30.
 */
public class CityM {
    private String msgcode;
    private String msg;

    public List<CityInfo> getData() {
        return data;
    }

    public void setData(List<CityInfo> data) {
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

    private List<CityInfo>data;


    public static class CityInfo{

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

        private String id;
        private String name;

    }
}
