package com.UttamMedicals.uttammedicalsfinalpatient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ListPrescMainActivity extends Activity {

	private static final int CAMERA_REQUEST = 1888;
	byte[] byteArray,byteArray1;
	String imgString ="",imgString1 ="";
	ProgressDialog mProgressDialog;
	public int flag;
	public String type;
	public String[] myArr = new String[8];
	public String[] myArrQTY = new String[8];
	String check;

	ConnectionDetector cd = new ConnectionDetector(this);

	public String order_no_for_that_time;

	public String  fromTime;
	public String  ToTime;
	public String  desiredDate;
	private Camera mCamera;
	public String Current_date, Current_time;
	Button btnPreview, take_picture,take_picture1, placeOrder, date, time1,time2, button_path;
	RadioButton rbhome, rbshop;

	TextView selected_date,selected_timeto,selected_timefrom;
	//RegistrationBaseAdapter registrationBaseAdapter;
	FrameLayout preview;
	//private CameraPreview mCameraPreview;
	public static String path = "";
	EditText et1,et2,et3,et4,et5,et6,et7,et8,qty1,qty2,qty3,qty4,qty5,qty6,qty7,qty8;
	ImageView imageview_forpresc, imageview_forpresc1;
	DatePicker dp;
	String take_array[];
	DatabaseHandler dbh = new DatabaseHandler(this);
	Date dt = new Date();
	String response_e = null;
	int hours = dt.getHours();
	int minutes = dt.getMinutes();
	int seconds = dt.getSeconds();

	int flaggy;
	//String current_Time = hours + ":" + minutes + ":" + seconds;
	Calendar c = Calendar.getInstance();
	String current_Date = c.get(Calendar.YEAR) + "-" 
			+ c.get(Calendar.MONTH)
			+ "-" + c.get(Calendar.DAY_OF_MONTH) 
			+ " at " + c.get(Calendar.HOUR_OF_DAY); 

	private SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"MMMM dd, yyyy");
	@SuppressLint("SimpleDateFormat") private SimpleDateFormat timeFormatter = new SimpleDateFormat(
			"hh:mm a");

	private Calendar dateTime = Calendar.getInstance();
	String current_date = dateFormatter.format(dateTime.getTime());
	String current_time = timeFormatter.format(dateTime.getTime());

	private static final int DIALOG_DATE = 1;
	private static final int DIALOG_TIME_FROM = 2;
	private static final int DIALOG_TIME_TO = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listprescdesign);
		initialize();
		//Context mycontext;

		take_picture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
				startActivityForResult(cameraIntent, CAMERA_REQUEST);
				flaggy=1;
			}
		});

		take_picture1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
				startActivityForResult(cameraIntent, CAMERA_REQUEST); 
				flaggy=2;
			}
		});

		placeOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(cd.isConnectingToInternet()){

					String list_item1 = et1.getText().toString()  + et2.getText().toString() + et3.getText().toString() + et4.getText().toString() + et5.getText().toString()  + et6.getText().toString() + et7.getText().toString() + et8.getText().toString();

					Log.e("array length", ""+myArr.length);
					myArr[0] = et1.getText().toString() ;
					myArr[1] = et2.getText().toString() ;
					myArr[2] = et3.getText().toString() ;
					myArr[3] = et4.getText().toString() ;
					myArr[4] = et5.getText().toString() ;
					myArr[5] = et6.getText().toString() ;
					myArr[6] = et7.getText().toString() ;
					myArr[7] = et8.getText().toString() ;

					myArrQTY[0] = qty1.getText().toString();
					myArrQTY[1] = qty2.getText().toString();
					myArrQTY[2] = qty3.getText().toString();
					myArrQTY[3] = qty4.getText().toString();
					myArrQTY[4] = qty5.getText().toString();
					myArrQTY[5] = qty6.getText().toString();
					myArrQTY[6] = qty7.getText().toString();
					myArrQTY[7] = qty8.getText().toString();
					Log.e("array qty length", ""+myArrQTY.length);
					
					
					String order_status = "O";
					String userDetails = dbh.getUsername();
					String rb_home = "Home delivery";
					String rb_shop = "Shop pickup";
					if(rbhome.isChecked()){
						sendparametertophp(Utils.URL_ListandPrescription_patient,current_date, current_time, userDetails, desiredDate, fromTime, ToTime,order_status,rb_home);

					}
					else{
						sendparametertophp(Utils.URL_ListandPrescription_patient,current_date, current_time, userDetails, desiredDate, fromTime, ToTime,order_status,rb_shop);
					}

					takeorderno(Utils.URL_Order_no_from_OrderHeader,current_time);


					//if no image
					Log.e("listitems", list_item1);

					if(imgString.length()==0 && list_item1.length()!=0 ){
						Log.e("TAG", "1ST CONDITION");
						for(int i = 0 ; i < myArr.length ; i++){

							new Sending_only_items(Utils.URL_Forsendingonlyitems_patient,"L", myArr[i], order_no_for_that_time, i+1,myArrQTY[i]).execute();

						}
						Toast.makeText(getApplicationContext(), "ORDER PLACED SUCCESSFULLY", Toast.LENGTH_LONG).show();
					}
					else if(imgString!=""&& list_item1.length()==0){
									Log.e("TAG", "2ST CONDITION");
						//			Toast.makeText(getApplicationContext(), "In 2nd ifelse", Toast.LENGTH_SHORT).show();

						sendingonlyimage(Utils.URL_Forsendingonlyimages_patient,"I",imgString,order_no_for_that_time,1,"C1");
						sendingonlyimage(Utils.URL_Forsendingonlyimages_patient,"I",imgString1,order_no_for_that_time,2,"C2");
						Toast.makeText(getApplicationContext(), "ORDER PLACED SUCCESSFULLY", Toast.LENGTH_LONG).show();

					}
					else{
							Log.e("TAG", "3ST CONDITION");
							Log.e("array qty length", ""+myArr.length);
						int ii =0;
						//int j=0;
						for( int j=0 ; j < myArr.length ; j++){
							new Sending_only_items(Utils.URL_Forsendingonlyitems_patient,"L", myArr[j], order_no_for_that_time, j+1,myArrQTY[j]).execute();

							ii++;
						}

						//			Log.e("after list images", "asdf");
						sendingonlyimage(Utils.URL_Forsendingonlyimages_patient,"I",imgString,order_no_for_that_time,ii+1,"C1");
						sendingonlyimage(Utils.URL_Forsendingonlyimages_patient,"I",imgString1,order_no_for_that_time,ii+2,"C2");
						Toast.makeText(getApplicationContext(), "ORDER PLACED SUCCESSFULLY", Toast.LENGTH_LONG).show();

					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Please Check internet connection", Toast.LENGTH_LONG).show();
				}

			}
		});

	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  

			if(flaggy==1){
				Log.e("flaggy is: ",""+flaggy);

								Bitmap photo = (Bitmap) data.getExtras().get("data"); 
								imageview_forpresc.setImageBitmap(photo);
								ByteArrayOutputStream stream = new ByteArrayOutputStream();
								photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
								byteArray = stream.toByteArray();
								imgString = Base64.encodeToString(byteArray, Base64.NO_WRAP);
								Log.e("sadsas", imgString);
			}
			if(flaggy==2){
				Log.e("flaggy is: ",""+flaggy);

				Bitmap photo = (Bitmap) data.getExtras().get("data"); 
				imageview_forpresc1.setImageBitmap(photo);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byteArray1 = stream.toByteArray();
				imgString1 = Base64.encodeToString(byteArray1, Base64.NO_WRAP);
				Log.e("sadsas", imgString1);
			}
		}  
	}




	private void initialize() {
		// TODO Auto-generated method stub

		//	button_path = (Button)findViewById(R.id.button1);
		take_picture = (Button)findViewById(R.id.btn_camera);
		take_picture1 = (Button)findViewById(R.id.btn_camera1);
		placeOrder = (Button)findViewById(R.id.btnplaceorder);

		et1 = (EditText)findViewById(R.id.item1);
		et2 = (EditText)findViewById(R.id.item2);
		et3 = (EditText)findViewById(R.id.item3);
		et4 = (EditText)findViewById(R.id.item4);
		et5 = (EditText)findViewById(R.id.item5);
		et6 = (EditText)findViewById(R.id.item6);
		et7 = (EditText)findViewById(R.id.item7);
		et8 = (EditText)findViewById(R.id.item8);

		qty1 = (EditText)findViewById(R.id.etqty1);
		qty2= (EditText)findViewById(R.id.etqty2);
		qty3 = (EditText)findViewById(R.id.etqty3);
		qty4 = (EditText)findViewById(R.id.etqty4);
		qty5 = (EditText)findViewById(R.id.etqty5);
		qty6= (EditText)findViewById(R.id.etqty6);
		qty7 = (EditText)findViewById(R.id.etqty7);
		qty8 = (EditText)findViewById(R.id.etqty8);

		
		date = (Button)findViewById(R.id.datebtnclick);
		time2 = (Button)findViewById(R.id.timetobtnclick);
		time1 = (Button)findViewById(R.id.timefrombtnclick);

		selected_date = (TextView)findViewById(R.id.selected_date_display);
		selected_timefrom = (TextView)findViewById(R.id.selected_time_from_display);
		selected_timeto = (TextView)findViewById(R.id.selected_time_to_display);

		//	dp = (DatePicker)findViewById(R.id.datePicker1);
		//preview = (FrameLayout) findViewById(R.id.camera_preview);
		rbhome = (RadioButton)findViewById(R.id.rdbtnhome);
		rbshop = (RadioButton)findViewById(R.id.rdbtnshop);
		//
		imageview_forpresc = (ImageView) findViewById(R.id.IG_FORCAPTUREPRESC);
		imageview_forpresc1 = (ImageView) findViewById(R.id.IG_FORCAPTUREPRESC1);


	}

	public void selectDate(View v)
	{
		showDialog(DIALOG_DATE);
	}
	public void  selectFromTime(View v)
	{

		showDialog(DIALOG_TIME_FROM);
	}
	public void  selectToTime(View v)
	{

		showDialog(DIALOG_TIME_TO);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DATE:
			return new DatePickerDialog(this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker veiw, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					dateTime.set(year, monthOfYear, dayOfMonth);
					desiredDate = dateFormatter
							.format(dateTime.getTime());
					//	Log.e("DIALOG_DATE", desiredDate);
					selected_date.setText(desiredDate);
				}
			},dateTime.get(Calendar.YEAR),
			dateTime.get(Calendar.MONTH),
			dateTime.get(Calendar.DAY_OF_MONTH));



		case DIALOG_TIME_FROM:

			return new TimePickerDialog(this, new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

					dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
					dateTime.set(Calendar.MINUTE, minute);
					fromTime = timeFormatter
							.format(dateTime.getTime());
					//	Log.e("DIALOG_TIME_FROM", fromTime);
					selected_timefrom.setText(fromTime);
				}

			}, dateTime.get(Calendar.HOUR_OF_DAY),
			dateTime.get(Calendar.MINUTE), false);

		case DIALOG_TIME_TO:

			return new TimePickerDialog(this, new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

					dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
					dateTime.set(Calendar.MINUTE, minute);
					ToTime = timeFormatter
							.format(dateTime.getTime());
					//	Log.e("DIALOG_TIME_TO", ToTime);
					selected_timeto.setText(ToTime);
				}

			}, dateTime.get(Calendar.HOUR_OF_DAY),
			dateTime.get(Calendar.MINUTE), false);
		}
		return null;

	}


	private class SendTime_toget_order_no extends AsyncTask<Void, Void, String>{


		String URL;
		String current_time;

		public SendTime_toget_order_no(String URL, String time) {
			this.URL = URL;
			this.current_time = time;

		}


		@Override
		protected String doInBackground(Void... params) {

			try {
				//	Log.e("called", "calledasdfasdf ");

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				HttpPost httpPost = new HttpPost(URL);

				List<NameValuePair> lst = new ArrayList<NameValuePair>();
				//		current_date, current_time, userDetails, desiredDate, fromTime, ToTime;
				lst.add(new BasicNameValuePair("TIME",current_time));

				httpPost.setEntity(new UrlEncodedFormEntity(lst));
				httpResponse = httpClient.execute(httpPost);

				httpEntity = httpResponse.getEntity();
				response_e = EntityUtils.toString(httpEntity);

				//				check = response_e;
				//				Log.e("rsqqqqe:", ""+response_e);
				//

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Log.e("called", "orderno= "+response_e.toString());
			return response_e;

		}
		@Override
		protected void onPostExecute(String result) {
			try {
				JSONObject job = new JSONObject(result);

				order_no_for_that_time = job.getString("order_no");
				//		Log.e("called", "orderno= "+order_no_for_that_time);
				Toast.makeText(getApplicationContext(), "order no is ;:"+order_no_for_that_time, Toast.LENGTH_LONG).show();

			} catch (Exception e) {
				// TODO: handle exception
			}

			super.onPostExecute(result);
		}
	}

	//Sending_only_items
	private class Sending_only_items extends AsyncTask<Void, Void, String>{

		String URL;
		String response = null;
		String itemtype,itemdesc,orderno,qty;
		int itemno;
		//	new Sending_only_items(url11,"L", myArr[i], order_no_for_that_time, i+1,myArrQTY[i]).execute();		

		public Sending_only_items(String url11, String item_type, String item_desc,
				String order_no_for_that_time, int i, String qty) {
			// TODO Auto-generated constructor stub
			this.URL = url11;
			this.itemtype = item_type;
			this.itemdesc = item_desc;
			this.orderno = order_no_for_that_time;
			this.itemno = i;
			this.qty = qty;

		}

		@Override
		protected String doInBackground(Void... params) {

			try {
				//	log.e("caca"+asd);
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpEntity httpEntity = null;
				HttpResponse httpResponse = null;
				HttpPost httpPost = new HttpPost(URL);

				List<NameValuePair> lst = new ArrayList<NameValuePair>();
				//		current_date, current_time, userDetails, desiredDate, fromTime, ToTime;
				lst.add(new BasicNameValuePair("ORDER_NO",orderno));
				lst.add(new BasicNameValuePair("ITEM_NO",""+itemno));
				lst.add(new BasicNameValuePair("ITEM_TYPE",itemtype));
				lst.add(new BasicNameValuePair("ITEM_DESCRIPTION",itemdesc));
				lst.add(new BasicNameValuePair("QTY",qty));
				httpPost.setEntity(new UrlEncodedFormEntity(lst));
				httpResponse = httpClient.execute(httpPost);

				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

				//		Log.e("rsegfjhfhjfhj:", ""+response);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
	}

	@SuppressLint("NewApi") public void sendparametertophp(String URL,String current_date, String current_time, String userDetails, String desiredDate, String fromTime, String ToTime, String Ostatus, String del_status){
		//		String URL;
		String response = null;
		//	String current_date, current_time, userDetails, desiredDate, fromTime, ToTime,Ostatus;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			HttpPost httpPost = new HttpPost(URL);

			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			//		current_date, current_time, userDetails, desiredDate, fromTime, ToTime;
			lst.add(new BasicNameValuePair("DATE",current_date));
			lst.add(new BasicNameValuePair("TIME",current_time));
			lst.add(new BasicNameValuePair("EMAIL",userDetails));
			lst.add(new BasicNameValuePair("REQ_DEL_DATE",desiredDate));
			lst.add(new BasicNameValuePair("REQ_DEL_TIME_FROM",fromTime));
			lst.add(new BasicNameValuePair("REQ_DEL_TIME_TO",ToTime));
			lst.add(new BasicNameValuePair("ORDER_STATUS",Ostatus));
			lst.add(new BasicNameValuePair("DELIVERY_OPTIONS",del_status));
			httpPost.setEntity(new UrlEncodedFormEntity(lst));
			httpResponse = httpClient.execute(httpPost);

			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);

			//	Log.e("rse:", ""+response);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void sendingonlyimage(String url_image,String type,String imgString1,String order_no_for_that_time,int i, String check) {
		// TODO Auto-generated method stub
		try {
			//	Log.e("called", "calledasdfasdf ");

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			HttpPost httpPost = new HttpPost(url_image);

			//	Log.e("ALL", order_no_for_that_time+i+type+imgString1);
			//Log.e("ALL", order_no_for_that_time+i+type+imgString1);
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			//		current_date, current_time, userDetails, desiredDate, fromTime, ToTime;
			lst.add(new BasicNameValuePair("ORDER_NO",order_no_for_that_time));
			lst.add(new BasicNameValuePair("ITEM_NO",""+i));
			lst.add(new BasicNameValuePair("ITEM_TYPE",type));
			lst.add(new BasicNameValuePair("ITEM_DESCRIPTION",imgString1));
			lst.add(new BasicNameValuePair("Checking",check));



			httpPost.setEntity(new UrlEncodedFormEntity(lst));
			httpResponse = httpClient.execute(httpPost);

			httpEntity = httpResponse.getEntity();
			//	Log.e("asdaffffffffffffffdddddddddddddd", ""+httpEntity);
			response_e = EntityUtils.toString(httpEntity);
			//parsemethod(response_e);

			//				check = response_e;
			//	Log.e("sssssssssssssssssssssssssssssssssssssssssssssssssssss:", ""+response_e);
			//

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void sendingimage(String url_image,String type,String imgString1,String order_no_for_that_time,int i) {
		// TODO Auto-generated method stub
		try {
			//	Log.e("called", "calledasdfasdf ");

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			HttpPost httpPost = new HttpPost(url_image);

			Log.e("ALL", order_no_for_that_time+i+type+imgString1);
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			//		current_date, current_time, userDetails, desiredDate, fromTime, ToTime;
			lst.add(new BasicNameValuePair("ORDER_NO",order_no_for_that_time));
			lst.add(new BasicNameValuePair("ITEM_NO",""+i));
			lst.add(new BasicNameValuePair("ITEM_TYPE",type));
			lst.add(new BasicNameValuePair("ITEM_DESCRIPTION",imgString1));



			httpPost.setEntity(new UrlEncodedFormEntity(lst));
			httpResponse = httpClient.execute(httpPost);

			httpEntity = httpResponse.getEntity();
			//Log.e("asdaffffffffffffffdddddddddddddd", ""+httpEntity);
			response_e = EntityUtils.toString(httpEntity);
			//parsemethod(response_e);

			//				check = response_e;
			//	Log.e("response from only image in both:", ""+response_e);
			//

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	private void takeorderno(String uRL_Order_no_from_OrderHeader,
			String current_time) {
		// TODO Auto-generated method stub
		try {
			//	Log.e("called", "calledasdfasdf ");

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;
			HttpPost httpPost = new HttpPost(uRL_Order_no_from_OrderHeader);

			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			//		current_date, current_time, userDetails, desiredDate, fromTime, ToTime;
			lst.add(new BasicNameValuePair("TIME",current_time));

			httpPost.setEntity(new UrlEncodedFormEntity(lst));
			httpResponse = httpClient.execute(httpPost);

			httpEntity = httpResponse.getEntity();
			response_e = EntityUtils.toString(httpEntity);
			parsemethod(response_e);

			//				check = response_e;
			//				Log.e("rsqqqqe:", ""+response_e);
			//

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void parsemethod(String result){
		JSONObject job = null;
		try {
			job = new JSONObject(result);
			order_no_for_that_time = job.getString("order_no");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		Log.e("called", "orderno= "+order_no_for_that_time);
		//Toast.makeText(getApplicationContext(), "order no is ;:"+order_no_for_that_time, Toast.LENGTH_LONG).show();

	}
}





