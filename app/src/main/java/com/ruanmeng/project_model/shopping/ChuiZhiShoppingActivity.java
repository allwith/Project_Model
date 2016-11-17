package com.ruanmeng.project_model.shopping;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanmeng.BaseActivity;
import com.ruanmeng.nohttp.CallServer;
import com.ruanmeng.project_model.R;
import com.ruanmeng.share.Const;
import com.ruanmeng.share.HttpIP;
import com.ruanmeng.swipemenulistview.SwipeMenu;
import com.ruanmeng.swipemenulistview.SwipeMenuCreator;
import com.ruanmeng.swipemenulistview.SwipeMenuItem;
import com.ruanmeng.swipemenulistview.SwipeMenuListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.Request;

import java.util.List;

public class ChuiZhiShoppingActivity extends BaseActivity {
    CheckBox cb_quanxuan;
    TextView tv_jiesuan;
	SwipeMenuListView listview;
	String url="http://www.silutianyu.com/nlsApi/Public/?service=Product.ShoppingList&u_id=1";
	ProgressDialog pd_get;
	 private CarListM carData;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rz_shopping);
		initview();
		getData();
		init_title();
		changeTitle("垂直购物车",null);
	}

//
//

	List<CarListM.DataBean> list;
	private CarAdapter adapter;
	public void getData() {
		Request<String> mRequest = NoHttp.createStringRequest(HttpIP.URL, Const.POST);
		mRequest.add("service", "Product.ShoppingList");
		mRequest.add("u_id", "1");
		// 添加到请求队列
		CallServer.getRequestInstance().add(this, 0, mRequest,
				new CustomHttpListener3(this, true, CarListM.class) {
					@Override
					public void doWork(Object data, boolean isOne) {
						list = ((CarListM) data).getData();
//                        adapter = new ChargeAdapter(getApplicationContext(), R.layout.item_recharge_list, list);
//                        mRecyclerView.setAdapter(adapter);
//						for(int i=0;i<list.size();i++){
//							list.get(i).setIscheck(0);
//						}
						adapter=new CarAdapter();
						listview.setAdapter(adapter);

						Toast.makeText(getApplicationContext(), "请求成功" + data, Toast.LENGTH_SHORT).show();
					}
				}, true, true);

	}



		/**
		 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
		 */
		public static int dip2px(Context context, float dpValue) {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dpValue * scale + 0.5f);
		}

		public static int sp2px(Context context, float spValue) {
			final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
			return (int) (spValue * fontScale + 0.5f);
		}
	private void initview() {
		pd_get=new ProgressDialog(this);
		listview=(SwipeMenuListView) this.findViewById(R.id.listview);
		cb_quanxuan=(CheckBox) this.findViewById(R.id.cb_qc);
		tv_jiesuan=(TextView) this.findViewById(R.id.tv_jiesuan);
		cb_quanxuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cb_quanxuan.isChecked())
				{
					for(int i=0;i<list.size();i++)
					{
						list.get(i).setIscheck(1);
					}
				}else{
					for(int i=0;i<list.size();i++)
					{
						list.get(i).setIscheck(0);
					}
				}
				adapter.notifyDataSetChanged();
				jisuan();
			}
		});
		SwipeMenuCreator creator = new SwipeMenuCreator() {
			@Override
			public void create(SwipeMenu menu) {
				SwipeMenuItem deleteItem = new SwipeMenuItem(
						ChuiZhiShoppingActivity.this);
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dip2px(ChuiZhiShoppingActivity.this,90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		listview.setMenuCreator(creator);
		listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				carData.getData().remove(position);
				adapter.notifyDataSetChanged();
				jisuan();
				return false;
			}
		});
		
	}



	
	
	
	
	private class CarAdapter extends BaseAdapter{
//		private ImageLoader mImageLoader;
//		public CarAdapter(){
//			mImageLoader=new ImageLoader(MyShopCarActivity.this);
//		}
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(final int position, View view, ViewGroup arg2) {
			if(view==null){
				view=LayoutInflater.from(ChuiZhiShoppingActivity.this).inflate(R.layout.car_item, null);
			}
			ImageView img_head=(ImageView)view.findViewById(R.id.img_head);
			TextView tv_name=(TextView)view.findViewById(R.id.tv_name);
			TextView tv_canshu=(TextView)view.findViewById(R.id.tv_canshu);
			TextView tv_price=(TextView)view.findViewById(R.id.tv_price);
			TextView tv_price1=(TextView)view.findViewById(R.id.tv_price1);
			ImageView ck_is=(ImageView)view.findViewById(R.id.ck_is);
//			mImageLoader.DisplayImage(HttpIp.ImgPath+carData.getData().get(position).getImg_path1(), img_head, false);
			tv_name.setText(list.get(position).getTitle());
			tv_canshu.setText("参数："+list.get(position).getGuige());
			tv_price.setText("¥"+list.get(position).getPrivilege_price());
			tv_price1.setText("¥"+list.get(position).getPrice());
			tv_price1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			Button btn_jia=(Button)view.findViewById(R.id.btn_jia);
			Button btn_jian=(Button)view.findViewById(R.id.btn_jian);
			final EditText et_num=(EditText)view.findViewById(R.id.et_num);
			et_num.setText(list.get(position).getNum());
			
			if(list.get(position).getIscheck()==0){
				ck_is.setImageResource(R.drawable.gw_02);
				
			}else{
				ck_is.setImageResource(R.drawable.gw_01);
			}
			
			ck_is.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(list.get(position).getIscheck()==0){
						list.get(position).setIscheck(1);
					}else{
						list.get(position).setIscheck(0);
					}
					notifyDataSetChanged();
					jisuan();
				}
			});
			
			
			btn_jian.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					int num = Integer.valueOf(et_num.getText().toString());
					if (num == 1) {
						Toast.makeText(ChuiZhiShoppingActivity.this, "不能再减了", Toast.LENGTH_SHORT).show();
						return;
					}
					num--;
					if (num == 0) {
						num = 1;
					}
					list.get(position).setNum(String.valueOf(num));
					et_num.setText(String.valueOf(num));
					notifyDataSetChanged();
					jisuan();
					
				}
			});

			btn_jia.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					int num = Integer.valueOf(et_num.getText().toString());
					num++;
					list.get(position).setNum(String.valueOf(num));
					notifyDataSetChanged();
					jisuan();
					
				}
			});
			return view;
		}
	}
	
	public void jisuan(){
		float money = 0;
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIscheck() == 1) {
                num++;
                money = money + (Integer.parseInt(list.get(i).getNum()) * Float.parseFloat(list.get(i).getPrice()));
            }
        }
        tv_jiesuan.setText("合计："+money);
        if (num == list.size()) {
            cb_quanxuan.setChecked(true);
        }else{
            cb_quanxuan.setChecked(false);
        }
	}
}
