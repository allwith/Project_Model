package com.ruanmeng.project_model.shopping;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.project_model.R;
import com.ruanmeng.swipemenulistview.SwipeMenu;
import com.ruanmeng.swipemenulistview.SwipeMenuCreator;
import com.ruanmeng.swipemenulistview.SwipeMenuItem;
import com.ruanmeng.swipemenulistview.SwipeMenuListView;
import com.ruanmeng.utils.ParentListView;

import java.util.ArrayList;

public class RuZhuShoppingActivity extends BaseActivity {
	CheckBox cb_quanxuan;//ȫѡ��ť
	TextView tv_jiesuan;//��Ǯ��
	ParentListView listview;//�����listview
	private SwipeMenuCreator menuCreator;//�໬�Ĳ˵�
	ShoppingCarM carM;//ʵ����
	private SelfSwipeMenuListViewAdapter selfSwipeMenuListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cz_shopping);
		initview();
		init_title();
		changeTitle("入住购物车",null);
	}

	
	/**
	 * ��ʼ���ؼ�
	 */
	private void initview() {
		listview = (ParentListView) this.findViewById(R.id.lv_MyShoppingCar);
		cb_quanxuan = (CheckBox) this.findViewById(R.id.cb_qc);
		tv_jiesuan = (TextView) this.findViewById(R.id.tv_jiesuan);
		/**
		 * ��ɾ������
		 */
		menuCreator = new SwipeMenuCreator() {
			@Override
			public void create(SwipeMenu menu) {
				SwipeMenuItem delete = new SwipeMenuItem(
						getApplicationContext());
				delete.setBackground(new ColorDrawable(Color.rgb(0xFF, 0x33,
						0x00)));
				// set item width
				delete.setWidth(150);
				// set item title
				delete.setTitle("ɾ��");
				// set item title fontsize
				delete.setTitleSize(15);
				// set item title font color
				delete.setTitleColor(Color.WHITE);
				menu.addMenuItem(delete);
			}
		};

		/**
		 * ��ʼ������
		 */
		carM = new ShoppingCarM();
		ShoppingCarM.Data data = new ShoppingCarM.Data();
		ShoppingCarM.Data data1 = new ShoppingCarM.Data();
		ArrayList<ShoppingCarM.Data> d = new ArrayList<ShoppingCarM.Data>();
		d.add(data1);
		d.add(data);
		carM.setData(d);
		ShoppingCarM.Data.ProductInfo info = new ShoppingCarM.Data.ProductInfo();
		info.setNewprice(100);
		info.setNum(1);
		ShoppingCarM.Data.ProductInfo info2 = new ShoppingCarM.Data.ProductInfo();
		info2.setNewprice(400);
		info2.setNum(4);
		ShoppingCarM.Data.ProductInfo info3 = new ShoppingCarM.Data.ProductInfo();
		info3.setNewprice(300);
		info3.setNum(2);
		ArrayList<ShoppingCarM.Data.ProductInfo> f = new ArrayList<ShoppingCarM.Data.ProductInfo>();
		ArrayList<ShoppingCarM.Data.ProductInfo> f1 = new ArrayList<ShoppingCarM.Data.ProductInfo>();
		f.add(info2);
		f.add(info);
		f1.add(info3);
		carM.getData().get(0).setPlist(f);
		carM.getData().get(1).setPlist(f1);

		/**
		 * 添加数据到adapter
		 */
		selfSwipeMenuListViewAdapter = new SelfSwipeMenuListViewAdapter();
		listview.setAdapter(selfSwipeMenuListViewAdapter);

		
		/**
		 * 点击全选
		 */
		cb_quanxuan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cb_quanxuan.isChecked()) {
					for (int i = 0; i < carM.getData().size(); i++) {
						for (int j = 0; j < carM.getData().get(i).getPlist()
								.size(); j++) {
							carM.getData().get(i).getPlist().get(j)
									.setIschecked(1);
						}
					}
				} else {
					for (int i = 0; i < carM.getData().size(); i++) {
						for (int j = 0; j < carM.getData().get(i).getPlist()
								.size(); j++) {
							carM.getData().get(i).getPlist().get(j)
									.setIschecked(0);
						}
					}
				}
				selfSwipeMenuListViewAdapter.notifyDataSetChanged();
				swipeMenuListViewAdapter.notifyDataSetChanged();
				jisuan();
			}
		});
	}

	private SwipeMenuListViewAdapter swipeMenuListViewAdapter;

	public class SelfSwipeMenuListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return carM.getData().size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getItemViewType(int position) {
			return super.getItemViewType(position);
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			convertView = View.inflate(RuZhuShoppingActivity.this, R.layout.item1,
					null);
			SwipeMenuListView swipeMenuListView = (SwipeMenuListView) convertView
					.findViewById(R.id.lv_item_myShoppingCar_ptrlv);
			//
			swipeMenuListView.setMenuCreator(menuCreator);

			// TODO: 2016/11/16   删除的 操作 
			swipeMenuListView
					.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(int nposition,
								SwipeMenu menu, int index) {
							//
							carM.getData().get(position).getPlist()
									.remove(nposition);
							if(carM.getData().get(position).getPlist().size()==0)
							{
								carM.getData().remove(position);
							}
							notifyDataSetChanged();
							swipeMenuListViewAdapter.notifyDataSetChanged();
							jisuan();
							return false;
						}

					});
			swipeMenuListViewAdapter = new SwipeMenuListViewAdapter(carM
					.getData().get(position).getPlist());
			swipeMenuListView.setAdapter(swipeMenuListViewAdapter);

			return convertView;
		}
	}

	public class SwipeMenuListViewAdapter extends BaseAdapter {

		ArrayList<ShoppingCarM.Data.ProductInfo> data;

		public SwipeMenuListViewAdapter(ArrayList<ShoppingCarM.Data.ProductInfo> data) {
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			convertView = View.inflate(RuZhuShoppingActivity.this, R.layout.item2,
					null);
			final TextView nu = (TextView) convertView
					.findViewById(R.id.iv_item_myShoppingCar_smlv_number);
			TextView jia = (TextView) convertView
					.findViewById(R.id.btn_item_myShoppingCar_smlv_jia);
			TextView jian = (TextView) convertView
					.findViewById(R.id.btn_item_myShoppingCar_smlv_jian);
			TextView price = (TextView) convertView
					.findViewById(R.id.tv_item_myShoppingCar_smlv_price);
			CheckBox box = (CheckBox) convertView
					.findViewById(R.id.ck_item_myShoppingCar_smlv_check);
			nu.setText(data.get(position).getNum() + "");
			price.setText("￥" + data.get(position).getNewprice());
			if (data.get(position).getIschecked() == 1) {
				box.setChecked(true);
			} else {
				box.setChecked(false);
			}
			jia.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int num = data.get(position).getNum() + 1;
					data.get(position).setNum(num);
					nu.setText(num + "");
					notifyDataSetChanged();
					jisuan();
				}
			});
			jian.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (data.get(position).getNum() == 1) {
						Toast.makeText(RuZhuShoppingActivity.this, "不能再减了", Toast.LENGTH_SHORT)
								.show();
						return;
					}
					int num = data.get(position).getNum() - 1;
					data.get(position).setNum(num);
					nu.setText(num + "");
					notifyDataSetChanged();
					jisuan();
				}
			});

			box.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (data.get(position).getIschecked() == 1) {
						data.get(position).setIschecked(0);
					} else {
						data.get(position).setIschecked(1);
					}
					notifyDataSetChanged();
					jisuan();
				}
			});

			return convertView;
		}
	}

	
	
	/**
	 * 加  减   运算
	 */
	int all = 0;
	public void jisuan()
	{
		int num = 0;
		int all = 0;
		float money = 0;

		for (int i = 0; i < carM.getData().size(); i++)
		{
			for (int j = 0; j < carM.getData().get(i).getPlist().size(); j++) {
				all++;
				if (carM.getData().get(i).getPlist().get(j).getIschecked() == 1)
				{
					num++;
					money = (float) (money + carM.getData().get(i).getPlist().get(j).getNum()* carM.getData().get(i).getPlist().get(j).getNewprice());
				}
			}
		}
		tv_jiesuan.setText("结算：" + money);
		if (all == num) {
			if(all!=0)
			{
				cb_quanxuan.setChecked(true);
			}
		} else {
			cb_quanxuan.setChecked(false);
		}
		
	}
}
