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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OfferDetails extends Activity {
	
	DatabaseHandler dbh = new DatabaseHandler(this);;
	public String img_response="";
	String data;
	TextView tv_offerno,tv_offername,tv_offerdatefrom,tv_offerdateto,tv_offer_del_opt;
	ImageView img_offer;
	Button btnpurchase_offer;
	String OFFER_NO = null,OFFER_NAME = null,DATE_FROM = null,DATE_TO = null,OFFER_STATUS=null;
	ConnectionDetector cd = new ConnectionDetector(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patientseeoffers);
		initialize();
		Intent i = getIntent();
		data = i.getExtras().getString("key");
		//Log.e("offer id from ", data);

		new Offerdetails(Utils.URL_GETDETAILSFOROFFER,data).execute();
		see_image_admin(Utils.URL_GETIMAGE_ONLY, data);
		img_offer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(), Patient_see_offersimage.class);
				in.putExtra("image_path", img_response);
				startActivity(in);
	
			}
		});
		
		btnpurchase_offer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Log.e("offers", OFFER_NO+OFFER_NAME+img_response);
				if(cd.isConnectingToInternet()){
				String name = dbh.getname();
				//Log.e("name", ""+name);
			new insertoffer(Utils.URL_INSERTPURCHASEOFFER,data,OFFER_NAME,name,img_response).execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void initialize() {
		// TODO Auto-generated method stub
		tv_offerno = (TextView) findViewById(R.id.TV_offer_no_see);
		tv_offername = (TextView) findViewById(R.id.TV_offer_Name);
		tv_offerdatefrom = (TextView) findViewById(R.id.TV_offer_date_from);
		tv_offerdateto = (TextView) findViewById(R.id.TV_offer_date_to);
		tv_offer_del_opt = (TextView) findViewById(R.id.TV_TILL_STOCK_LASTSS);
		img_offer =  (ImageView) findViewById(R.id.IV_offer_photo);
		btnpurchase_offer = (Button) findViewById(R.id.btnoffer_purchase);
	}

	private class Offerdetails extends AsyncTask<Void, Void, String>{

		String URL;
		String response = null;
		String order_id;


		public Offerdetails(String URL,String data) {
			this.URL = URL;	
			this.order_id = data;
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(Void... params) {

			try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				HttpPost httpPost = new HttpPost(URL);
				List<NameValuePair> lst = new ArrayList<NameValuePair>();
				lst.add(new BasicNameValuePair("OFFER_NO",order_id));
				httpPost.setEntity(new UrlEncodedFormEntity(lst));
				httpResponse = httpClient.execute(httpPost);

				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

				Log.e("rseasfjlaskfnlkasflkfas:", ""+response);
				
				//	parsemethodaa(response);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}

		//
		protected void onPostExecute(String result) {

			JSONObject job = null;
			//String ORDER_NO = null,DATE = null,TIME = null,EMAIL = null;

			try {
				JSONArray jarry = new JSONArray(result);
				job = jarry.getJSONObject(0);
				OFFER_NO =  job.getString("OFFER_NO");
				OFFER_NAME=  job.getString("OFFER_NAME");
				DATE_FROM =  job.getString("DATE_FROM");
				DATE_TO =  job.getString("DATE_TO");
				OFFER_STATUS = job.getString("TILL_STOCK_LAST");

				//	order_no_for_that_time = job.getString("order_no");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("all ",OFFER_NO+OFFER_NAME );
			tv_offerno.setText(OFFER_NO);
			tv_offername.setText(OFFER_NAME);
			tv_offerdatefrom.setText(DATE_FROM);
			tv_offerdateto.setText(DATE_TO);
			tv_offer_del_opt.setText(OFFER_STATUS);
//			fororderno.setText(ORDER_NO);
//			forpatientname.setText(EMAIL);
//			fordeliverydate.setText(DATE);
//			fordeliverytime.setText(TIME);
//			deliveryoptions.setText(DELIVERY_OPT);
//			itemdetails(data);
//		
		}
	}
	@SuppressLint("NewApi") private void see_image_admin(String URL, String order_id1){

		String response;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			HttpPost httpPost = new HttpPost(URL);
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("OFFER_NO",""+order_id1));
			lst.add(new BasicNameValuePair("ITEM_TYPE","I"));
			httpPost.setEntity(new UrlEncodedFormEntity(lst));
			httpResponse = httpClient.execute(httpPost);

			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);


			Log.e("image:", ""+response);
		img_response = response;
			//			byte[] decodedString = Base64.decode(response, Base64.NO_WRAP);
			//			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
			//			img_presc.setImageBitmap(decodedByte);
			//			
			//sendingimage(response);
			send_image(response);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void send_image(String response){


		Log.e("image in send_image", response);
		byte[] decodedString = Base64.decode(response, Base64.NO_WRAP);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		img_offer.setImageBitmap(decodedByte);
//		Intent in = new Intent(context, Admin_Img_Presc_see.class);
//		in.putExtra("image_path", response);
//		startActivity(in);
//		sendingimage(response);
	}
	
	private class insertoffer extends AsyncTask<Void, Void, String>{

		String URL;
		String response = null;
		String offerno,offername,pname,imgress;
	//	new insertoffer(Utils.URL_INSERTPURCHASEOFFER,data,OFFER_NAME,name,img_response).execute();
		public insertoffer(String URL,String data, String offer_name, String name,String img_res) {
			this.URL = URL;
			this.offerno = data;
			this.offername = offer_name;
			this.pname = name;
			this.imgress = img_res;
			
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(Void... params) {

			try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				HttpPost httpPost = new HttpPost(URL);

				List<NameValuePair> lst = new ArrayList<NameValuePair>();
				lst.add(new BasicNameValuePair("OFFER_NO",offerno));
				lst.add(new BasicNameValuePair("OFFER_NAME",offername));
				lst.add(new BasicNameValuePair("OFFER_DESCRIPTION",imgress));
				lst.add(new BasicNameValuePair("PATIENT_NAME",pname));
				lst.add(new BasicNameValuePair("OFFER_STATUS","O"));
				httpPost.setEntity(new UrlEncodedFormEntity(lst));
				httpResponse = httpClient.execute(httpPost);

				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

			//	Log.e("rse FROM PURCHASES PHP:", ""+response);
			//	checkresponse(response);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
		//	checkresponse(response);
			JSONObject job = null;
			try {
				job = new JSONObject(response);
				String res = job.getString("result");
				if (res.equalsIgnoreCase("Success")) {
					Toast.makeText(getApplicationContext(), "Your offer placed ", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getApplicationContext(), Menu_class.class);
					startActivity(i);
					finish();
				}
				else {
					Toast.makeText(getApplicationContext(), "offer not placed ", Toast.LENGTH_SHORT).show();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}


}
	

