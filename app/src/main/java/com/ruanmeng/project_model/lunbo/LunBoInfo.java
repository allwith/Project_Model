package com.ruanmeng.project_model.lunbo;

import java.util.List;

public class LunBoInfo {


    /**
     * msgcode : 1
     * msg : 搜索成功!
     * data : [{"link":"","j_id":"31","name":"测试1","img_path":"/data/upload/20160530/574bab5bc6203.jpg","id":"19","shequ_id":"164"},{"link":"http://baidu.com","j_id":"","name":"测试2","img_path":"/data/upload/20160530/574bab7a01a72.jpg","id":"20","shequ_id":"0"},{"link":"http://baidu.com","j_id":"","name":"啊实打实大飒飒的","img_path":"/data/upload/20160530/574bab9d72314.jpg","id":"21","shequ_id":"0"}]
     */

    private int msgcode;
    private String msg;
    /**
     * link :
     * j_id : 31
     * name : 测试1
     * img_path : /data/upload/20160530/574bab5bc6203.jpg
     * id : 19
     * shequ_id : 164
     */

    private List<DataBean> data;

    public int getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(int msgcode) {
        this.msgcode = msgcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String link;
        private String j_id;
        private String name;
        private String img_path;
        private String id;
        private String shequ_id;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getJ_id() {
            return j_id;
        }

        public void setJ_id(String j_id) {
            this.j_id = j_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShequ_id() {
            return shequ_id;
        }

        public void setShequ_id(String shequ_id) {
            this.shequ_id = shequ_id;
        }
    }
}
