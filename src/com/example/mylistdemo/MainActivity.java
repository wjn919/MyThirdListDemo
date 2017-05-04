package com.example.mylistdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.mylistdemo.view.StickyGridHeadersGridView;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private ListView mLeftLstv;
	private StickyGridHeadersGridView mRightGdv;


	List<LeftData> mLeftDatas  = new ArrayList();
	List<SPCategory> mRightDatas = new ArrayList();
	List<SPCategory> subDatas1 = new ArrayList();
	List<SPCategory> subDatas2 = new ArrayList();
	private SPCategoryLeftAdapter mLeftAdapter;
	private SPCategoryRightAdapter mRightAdapter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftLstv = (ListView)findViewById(R.id.category_left_lstv);  
        mRightGdv = (StickyGridHeadersGridView)findViewById(R.id.category_right_gdvv);  
        mRightGdv.setAreHeadersSticky(false);   //设置标题栏不随着列表滑动而滑动  
        
        initLeftData();
        initRightData();
        mLeftAdapter = new SPCategoryLeftAdapter(this);  
        mRightAdapter = new SPCategoryRightAdapter(this);  
        mRightAdapter.setData(mRightDatas);    
        mLeftLstv.setAdapter(mLeftAdapter);  
       mRightGdv.setAdapter(mRightAdapter);
     
       
       mLeftLstv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			mLeftAdapter.setSelected(arg2);
			mLeftAdapter.notifyDataSetChanged();
			Toast.makeText(MainActivity.this, mLeftDatas.get(arg2).getFirstType(), Toast.LENGTH_SHORT).show();
			
		}
	});
      
       
    }


	private void initRightData() {
		SPCategory parentmData = new SPCategory();
		parentmData.setName("分类1");
		parentmData.setId(1);
		for(int i = 0 ;i<8;i++){
			SPCategory sub = new SPCategory();
			sub.setId(i);
			sub.setName("item"+i);
			sub.setImage("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
			sub.setParentId(1);
			subDatas1.add(sub);
			parentmData.setSubCategory(subDatas1);
			sub.setParentCategory(parentmData);
			sub.setParentId(1);
		}
		
		SPCategory parentmData2 = new SPCategory();
		parentmData2.setName("分类2");
		parentmData2.setId(2);
		for(int i = 0 ;i<25;i++){
			SPCategory sub = new SPCategory();
			sub.setId(i);
			sub.setName("item"+i);
			sub.setImage("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
			sub.setParentId(2);
			sub.setLevel(i);
			subDatas2.add(sub);
			parentmData2.setSubCategory(subDatas2);
			sub.setParentCategory(parentmData2);
			sub.setParentId(2);
			
		}
		
		
		
		
		
		mRightDatas.add(parentmData);
		mRightDatas.add(parentmData2);
		
	}


	private void initLeftData() {
		for(int i = 0;i<30;i++){
			LeftData datas = new LeftData();
			datas.setFirstType("分类"+i);
			mLeftDatas.add(datas);
		}
		
	}

	

	class SPCategoryLeftAdapter extends BaseAdapter{
	
		private int selected=0;
		private Context mContext;
		public SPCategoryLeftAdapter(Context context) {
			this.mContext = context;
		}

		public void setSelected(int position){
	        this.selected=position;
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mLeftDatas.size();
		}

		@Override
		public LeftData getItem(int arg0) {
			// TODO Auto-generated method stub
			return mLeftDatas.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View convertView, ViewGroup viewGroup) {
			ViewHolder holder = null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = View.inflate(mContext, R.layout.item_left, null);
				holder.text = (TextView) convertView.findViewById(R.id.tv_left);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.text.setText(getItem(arg0).getFirstType());
			
			if (arg0==selected) {
				holder.text.setBackgroundResource(R.drawable.pm_all_button_bg);

	        }else {
	        	holder.text.setBackgroundResource(R.color.white); 
	        	}
			
			return convertView;
		}
		
	}
	
	static class ViewHolder{
		private TextView text;
	}
   

   
}
