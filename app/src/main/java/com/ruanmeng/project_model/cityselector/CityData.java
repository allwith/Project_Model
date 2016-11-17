package com.ruanmeng.project_model.cityselector;
import java.util.List;
public class CityData  {
	private String msgcode  ;
	private String msg  ;
	private List<CityInfo> data;
	public class CityInfo  {
		private String provinceId  ;
		private String provinceCode  ;
		private String provinceName  ;
		private String cityName  ;
		private String cityCode  ;
		private String cityId  ;
		private String countyId  ;
		private String countyName  ;
		private String countyCode  ;
		public void  setProvinceId (String provinceId){this.provinceId=provinceId;}
		public String getProvinceId (){return this.provinceId;}
		public void  setProvinceCode (String provinceCode){this.provinceCode=provinceCode;}
		public String getProvinceCode (){return this.provinceCode;}
		public void  setProvinceName (String provinceName){this.provinceName=provinceName;}
		public String getProvinceName (){return this.provinceName;}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public String getCityId() {
			return cityId;
		}
		public void setCityId(String cityId) {
			this.cityId = cityId;
		}
		public String getCountyId() {
			return countyId;
		}
		public void setCountyId(String countyId) {
			this.countyId = countyId;
		}
		public String getCountyName() {
			return countyName;
		}
		public void setCountyName(String countyName) {
			this.countyName = countyName;
		}
		public String getCountyCode() {
			return countyCode;
		}
		public void setCountyCode(String countyCode) {
			this.countyCode = countyCode;
		}

	}
	public void  setMsgcode (String msgcode){this.msgcode=msgcode;}
	public String getMsgcode (){return this.msgcode;}
	public void  setMsg (String msg){this.msg=msg;}
	public String getMsg (){return this.msg;}
	public List<CityInfo> getData() { return data;}
	public void setData(List<CityInfo> data) {this.data = data;}
}
