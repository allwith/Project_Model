package com.ruanmeng.project_model.Refresh;

import java.util.List;

public class NewsModel {
    /**
     * code : 1
     * msg : 数据读取成功
     * data : {"site_id":"1","status":"","total":5,"pagenum":1,"pagesize":10,"totalpage":1,"list":[{"id":"487","name":"存贷比考核\u201c悬剑\u201d 村镇银行\u201c优惠\u201d揽储 ","litpic":null,"status":"1","summary":"","hits":"1","addtime":"1363310930","content":""},{"id":"486","name":"理财315：130产品未达预期收益 中信邮储上黑榜","litpic":null,"status":"1","summary":"","hits":"2","addtime":"1363310420","content":""},{"id":"484","name":"国有大行限制超级网银功能 担忧存款外流","litpic":null,"status":"1","summary":"","hits":"4","addtime":"1363309885","content":""},{"id":"481","name":"广州银行网银客户瞬间成富翁 银行安全系统遭质疑","litpic":null,"status":"1","summary":"","hits":"2","addtime":"1363309714","content":""},{"id":"479","name":"末日博士：中国信贷泡沫将成投资者主要风险","litpic":null,"status":"1","summary":"","hits":"0","addtime":"1363309539","content":""}]}
     */

    private String code;
    private String msg;
    /**
     * site_id : 1
     * status :
     * total : 5
     * pagenum : 1
     * pagesize : 10
     * totalpage : 1
     * list : [{"id":"487","name":"存贷比考核\u201c悬剑\u201d 村镇银行\u201c优惠\u201d揽储 ","litpic":null,"status":"1","summary":"","hits":"1","addtime":"1363310930","content":""},{"id":"486","name":"理财315：130产品未达预期收益 中信邮储上黑榜","litpic":null,"status":"1","summary":"","hits":"2","addtime":"1363310420","content":""},{"id":"484","name":"国有大行限制超级网银功能 担忧存款外流","litpic":null,"status":"1","summary":"","hits":"4","addtime":"1363309885","content":""},{"id":"481","name":"广州银行网银客户瞬间成富翁 银行安全系统遭质疑","litpic":null,"status":"1","summary":"","hits":"2","addtime":"1363309714","content":""},{"id":"479","name":"末日博士：中国信贷泡沫将成投资者主要风险","litpic":null,"status":"1","summary":"","hits":"0","addtime":"1363309539","content":""}]
     */

    private NewsData data;

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

    public NewsData getData() {
        return data;
    }

    public void setData(NewsData data) {
        this.data = data;
    }

    public class NewsData {
        private String site_id;
        private String status;
        private String total;
        private String pagenum;
        private String pagesize;
        private String totalpage;
        /**
         * id : 487
         * name : 存贷比考核“悬剑” 村镇银行“优惠”揽储
         * litpic : null
         * status : 1
         * summary :
         * hits : 1
         * addtime : 1363310930
         * content :
         */

        private List<NewsInfo> list;

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getPagenum() {
            return pagenum;
        }

        public void setPagenum(String pagenum) {
            this.pagenum = pagenum;
        }

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public List<NewsInfo> getList() {
            return list;
        }

        public void setList(List<NewsInfo> list) {
            this.list = list;
        }

    }

    public class NewsInfo {
        private String id;
        private String name;
        private String litpic;
        private String status;
        private String summary;
        private String hits;
        private String addtime;
        private String content;

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

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
