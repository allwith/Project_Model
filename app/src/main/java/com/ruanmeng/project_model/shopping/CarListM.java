package com.ruanmeng.project_model.shopping;

import java.util.List;

public class CarListM {


    /**
     * msgcode : 0
     * msg : 成功
     * data : [{"id":"589","s_id":"15","title":"52 ° 十五年陈酿","price":"210.00","privilege_price":"210","guige":"500ml/6","img_path1":"/data/upload/20160429/5723139b6b7d3.png","puy_away":"1","num":"1","create_time":"2016-07-19 08:13:00"},{"id":"582","s_id":"4","title":"36° 百年牛栏山","price":"660.00","privilege_price":"660","guige":"400ml/6","img_path1":"/data/upload/20160616/5762093ddac00.png","puy_away":"1","num":"3","create_time":"2016-07-18 16:18:20"},{"id":"581","s_id":"5","title":"46° 中国牛","price":"228.00","privilege_price":"228","guige":"500ml/6","img_path1":"/data/upload/20160428/5721db26b2f19.png","puy_away":"1","num":"3","create_time":"2016-07-18 16:18:07"},{"id":"580","s_id":"11","title":" 52° 中国牛 ","price":"648.00","privilege_price":"648","guige":"500ml/6","img_path1":"/data/upload/20160429/5722c249135c2.png","puy_away":"1","num":"2","create_time":"2016-07-18 16:17:59"},{"id":"579","s_id":"47","title":"52° 经典 二锅头","price":"2600.00","privilege_price":"2600","guige":"500ml/6","img_path1":"/data/upload/20160613/575e12f5bfcf0.png","puy_away":"1","num":"1","create_time":"2016-07-18 16:17:54"}]
     */

    private int msgcode;
    private String msg;
    /**
     * id : 589
     * s_id : 15
     * title : 52 ° 十五年陈酿
     * price : 210.00
     * privilege_price : 210
     * guige : 500ml/6
     * img_path1 : /data/upload/20160429/5723139b6b7d3.png
     * puy_away : 1
     * num : 1
     * create_time : 2016-07-19 08:13:00
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
        private String id;
        private String s_id;
        private String title;
        private String price;
        private String privilege_price;
        private String guige;
        private String img_path1;
        private String puy_away;
        private String num;
        private String create_time;
        private int ischeck;

        public int getIscheck() {
            return ischeck;
        }

        public void setIscheck(int ischeck) {
            this.ischeck = ischeck;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getS_id() {
            return s_id;
        }

        public void setS_id(String s_id) {
            this.s_id = s_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrivilege_price() {
            return privilege_price;
        }

        public void setPrivilege_price(String privilege_price) {
            this.privilege_price = privilege_price;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getImg_path1() {
            return img_path1;
        }

        public void setImg_path1(String img_path1) {
            this.img_path1 = img_path1;
        }

        public String getPuy_away() {
            return puy_away;
        }

        public void setPuy_away(String puy_away) {
            this.puy_away = puy_away;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
