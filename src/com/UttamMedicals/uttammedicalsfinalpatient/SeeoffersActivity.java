package com.UttamMedicals.uttammedicalsfinalpatient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class SeeoffersActivity extends Activity {

	DatabaseHandler dbh = new DatabaseHandler(this);
	ListView lv;
	Context context;
	List<String> list;
	String[] arrayy;

	//	ArrayList prgmName;
	String prgmId[]; 
	String prgmNameList[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seeoffers);
		//		lv.setOnItemClickListener(new View.On)
		//		context=this;
		lv=(ListView) findViewById(R.id.listViewForCustom);



	lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id)
			{
		//	//	Intent in = new Intent(OrdersDetailsActivity.this,ConfirmOrderAdminActivity.class);
		////		startActivity(in);

			}
		});
//	Log.e("abovetakeoffername","above");
	new TakeOfferNoAndOfferName(Utils.URL_GETOFFERNOANDOFFERNAME).execute();
//	Log.e("abovetakeoffername","below");
//		String see_order_username = dbh.getorderandusername();
//		Log.e("usernames", see_order_username);
//
//		String see_order_id = dbh.getorderandid();
//		Log.e("ids", see_order_id);
//
//		//Log.e("qqqqqqqqqqqqqqqqqqqq", see_order);
//		String delims = "`";
//		
//		prgmId = see_order_id.split(delims);
//		prgmNameList = see_order_username.split(delims);
//

	//	lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmId));
	}

	private class TakeOfferNoAndOfferName  extends AsyncTask<Void, Void, String>{

		String URL;
		String response = null;


		public TakeOfferNoAndOfferName(String URL) {
			this.URL = URL;	
		}

		@Override
		protected void onPreExecute() {
			Log.e("aa","in preexecute");
		}
		
		@Override
		protected String doInBackground(Void... params) {

			try {
				Log.e("aa","in take offer no and name");
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				HttpPost httpPost = new HttpPost(URL);
//				List<NameValuePair> lst = new ArrayList<NameValuePair>();
//
//				httpPost.setEntity(new UrlEncodedFormEntity(lst));
				httpResponse = httpClient.execute(httpPost);

				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

				Log.e("rse:", ""+response);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}

		protected void onPostExecute(String result) {

			try {
				//String prgmNameList[] = null;
				JSONArray jar = new JSONArray(result);

				int len = jar.length();
				String prgmNameList[] = new String[len];
				String orderno[] = new String[len];
				for (int i = 0; i < jar.length(); i++)
				{
					JSONObject job =jar.getJSONObject(i);
					String ORDERNO =  job.getString("OFFER_NO");
					String EMAIL = job.getString("OFFER_NAME");
					prgmNameList[i] = ORDERNO;
					orderno[i] = EMAIL;
				//	Log.e("ressssssssssssssssss",ORDERNO+""+EMAIL);
					//	arrlis.add(map);
				}
				
			lv.setAdapter(new CustomAdapter(SeeoffersActivity.this, prgmNameList,orderno));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				super.onPostExecute(result);
			}
		}

	}

}
