package com.UttamMedicals.uttammedicalsfinalpatient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapterproduct extends BaseAdapter{
	   
		String [] result;
		Context context;
		String [] userId;
		private static LayoutInflater inflater=null;
		public CustomAdapterproduct(SeenewProducts mainActivity, String[] prgmNameList, String[] prgmId) {
			// TODO Auto-generated constructor stub
			result=prgmNameList;
			context=mainActivity;
			userId=prgmId;
			inflater = ( LayoutInflater )context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return result.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public class Holder
		{
			TextView tv1;
			TextView tv2;
		}


		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			Holder holder=new Holder();
			View rowView;     

			rowView = inflater.inflate(R.layout.mylistpatientlist, null);
			holder.tv1=(TextView) rowView.findViewById(R.id.TV_OFFERNAME);
			holder.tv2=(TextView) rowView.findViewById(R.id.TV_OFFERNO);

			holder.tv1.setText(userId[position]);
			holder.tv2.setText(result[position]);      
			rowView.setOnClickListener(new OnClickListener() {            
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					String actual_id = result[position];
					Intent in = new Intent(context,newproductsdetail.class);
					in.putExtra("key", actual_id);
				
					context.startActivity(in);
				//	((Activity) context).finish();
				}
			});   
			return rowView;

			// TODO Auto-generated method stub
		}

	}

	//ART7_image1

