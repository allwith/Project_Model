package com.ruanmeng.project_model.cityselector;

import java.util.List;

public class CityModel {

    private String code;
    private String msg;
    private CityData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CityData getData() {
        return data;
    }

    public void setData(CityData data) {
        this.data = data;
    }

    public class CityData {
        /**
         * id : 2
         * nid : 1
         * title : 北京市
         */

        private List<CityInfo> province;

        public List<CityInfo> getCity() {
            return city;
        }

        public void setCity(List<CityInfo> city) {
            this.city = city;
        }

        private List<CityInfo> city;

        public List<CityInfo> getProvince() {
            return province;
        }

        public void setProvince(List<CityInfo> province) {
            this.province = province;
        }

    }

    public static class CityInfo {

        public CityInfo() {
        }

        public CityInfo(String id, String title) {
            this.id = id;
            this.title = title;
        }

        private String id;

        private String nid;
        private String title;

        private String sortLetters;

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getSortLetters() {
            return sortLetters;
        }

        public void setSortLetters(String sortLetters) {
            this.sortLetters = sortLetters;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
