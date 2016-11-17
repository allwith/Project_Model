package com.ruanmeng.project_model.shopping;

import java.util.ArrayList;

/**
 * �ҵĹ��ﳵ
 * @author Administrator
 *
 */
public class ShoppingCarM {
	private String msgcode;
	private String msg;
	private ArrayList<Data> data;
	
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

	public ArrayList<Data> getData() {
		return data;
	}

	public void setData(ArrayList<Data> data) {
		this.data = data;
	}

	public static class Data{
		private int shopid;
		private String logo;
		private int isself;
		private String shopname;
		private int star;
		private ArrayList<ProductInfo> plist;
		
		public int getShopid() {
			return shopid;
		}

		public void setShopid(int shopid) {
			this.shopid = shopid;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public int getIsself() {
			return isself;
		}

		public void setIsself(int isself) {
			this.isself = isself;
		}

		public String getShopname() {
			return shopname;
		}

		public void setShopname(String shopname) {
			this.shopname = shopname;
		}

		public int getStar() {
			return star;
		}

		public void setStar(int star) {
			this.star = star;
		}

		public ArrayList<ProductInfo> getPlist() {
			return plist;
		}

		public void setPlist(ArrayList<ProductInfo> plist) {
			this.plist = plist;
		}

		public static class ProductInfo{
			private int pid;
			private String logo1;
			private String pname;
			private double newprice;
			private double oldprice;
			private double mail;
			private int num;
			private int ischecked;
			
			
			public int getIschecked() {
				return ischecked;
			}
			public void setIschecked(int ischecked) {
				this.ischecked = ischecked;
			}
			public int getPid() {
				return pid;
			}
			public void setPid(int pid) {
				this.pid = pid;
			}
			public String getLogo1() {
				return logo1;
			}
			public void setLogo1(String logo1) {
				this.logo1 = logo1;
			}
			public String getPname() {
				return pname;
			}
			public void setPname(String pname) {
				this.pname = pname;
			}
			public double getNewprice() {
				return newprice;
			}
			public void setNewprice(double newprice) {
				this.newprice = newprice;
			}
			public double getOldprice() {
				return oldprice;
			}
			public void setOldprice(double oldprice) {
				this.oldprice = oldprice;
			}
			public double getMail() {
				return mail;
			}
			public void setMail(double mail) {
				this.mail = mail;
			}
			public int getNum() {
				return num;
			}
			public void setNum(int num) {
				this.num = num;
			}
			
		}
	}
}
