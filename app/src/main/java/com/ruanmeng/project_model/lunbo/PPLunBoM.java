package com.ruanmeng.project_model.lunbo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class PPLunBoM {

    /**
     * msgcode : 1
     * msg : 成功
     * data : [{"id":1,"image":"/Documents/AdsSite/1.png"},{"id":2,"image":"/Documents/AdsSite/2.png"},{"id":3,"image":"/Documents/AdsSite/3.png"},{"id":4,"image":"/Documents/AdsSite/4.png"},{"id":5,"image":"/Documents/AdsSite/5.png"}]
     */

    private String msgcode;
    private String msg;
    /**
     * id : 1
     * image : /Documents/AdsSite/1.png
     */

    private List<LunBoBean> data;

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

    public List<LunBoBean> getData() {
        return data;
    }

    public void setData(List<LunBoBean> data) {
        this.data = data;
    }

    public static class LunBoBean {
        private String id;
        private String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
