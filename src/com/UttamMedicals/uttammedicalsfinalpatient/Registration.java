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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity {
	EditText personname, drname, address, phone, email, password;
	Button register;

	DatabaseHandler dbh = new DatabaseHandler(this);
	ConnectionDetector cd = new ConnectionDetector(this);
	// RegistrationBaseAdapter registrationBaseAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationdetails);
		personname = (EditText)findViewById(R.id.personname);
		drname = (EditText)findViewById(R.id.drname);
		address = (EditText)findViewById(R.id.address);
		phone = (EditText)findViewById(R.id.phone);
		email = (EditText)findViewById(R.id.email);
		password = (EditText)findViewById(R.id.password);
		register = (Button)findViewById(R.id.Register);


		//registrationBaseAdapter = new RegistrationBaseAdapter(this);
		// registrationBaseAdapter= registrationBaseAdapter.open();

		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Name = personname.getText().toString();
				String Drname = drname.getText().toString();
				String Address = address.getText().toString();
				String Phone = phone.getText().toString();
				String Email_ID = email.getText().toString();
				String Password = password.getText().toString();

				String regex = "[0-9]+";
				//ifPhone.compareTo(regex);
				if(cd.isConnectingToInternet()){

					if(Name.equals("") || Drname.equals("") || Address.equals("")|| Phone.equals("") || Email_ID.equals("") || Password.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Invalid entries", Toast.LENGTH_LONG).show();
						return;
					}
					else{

						dbh.insertEntry(Name, Drname, Address, Phone, Email_ID, Password);
						String url = "http://uttammedicals.in/patient_side_php_files/registration.php";
						new GetDataForRegistration(url,Name, Drname,Address,Phone,Email_ID,Password).execute();
						//			registrationBaseAdapter.insertEntry(Name,Drname, Address, Phone, Email,Password);
						Intent i = new Intent(getApplicationContext(), Menu_class.class);
						startActivity(i);
						finish();
						Toast.makeText(getApplicationContext(), "Account Successfully created ", Toast.LENGTH_LONG).show();

					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_LONG).show();
				}
			}

			//}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}


	private class GetDataForRegistration extends AsyncTask<Void, Void, String>{

		String URL;
		String response = null;
		String pname,dname,address,phone,email,password;

		public GetDataForRegistration(String URL,String p_name, String d_name, String addresss,String phonee,String emaill, String passwordd ) {
			this.URL = URL;
			this.pname = p_name;
			this.dname = d_name;
			this.address = addresss;
			this.phone = phonee;
			this.email = emaill;
			this.password = passwordd;

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
				lst.add(new BasicNameValuePair("PATIENT_NAME",pname));
				lst.add(new BasicNameValuePair("DOCTOR_NAME",dname));
				lst.add(new BasicNameValuePair("ADDRESS",address));
				lst.add(new BasicNameValuePair("PHONE",phone));
				lst.add(new BasicNameValuePair("EMAIL",email));
				lst.add(new BasicNameValuePair("PASSWORD",password));

				httpPost.setEntity(new UrlEncodedFormEntity(lst));
				httpResponse = httpClient.execute(httpPost);

				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity);

				Log.e("rse:", ""+response);
				
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
//	@Override
//		protected void onPostExecute(String response) {
//			//	checkresponse(response);
//			JSONObject job = null;
//			try {
//				job = new JSONObject(result);
//				String res = job.getString("result");
//				if (res.equalsIgnoreCase("Success")) {
//					Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT).show();
//					Intent i = new Intent(getApplicationContext(), Menu_class.class);
//					startActivity(i);
//					finish();
//				}
//				else {
//					Toast.makeText(getApplicationContext(), "Account not Created ", Toast.LENGTH_SHORT).show();
//				}
//
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				
	//	}


	}

	public void checkresponse(String response){
		if (response.equalsIgnoreCase("Success")) {
			Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getApplicationContext(), Menu_class.class);
			startActivity(i);
			finish();
		}
		else {
			Toast.makeText(getApplicationContext(), "Account not Created ", Toast.LENGTH_SHORT).show();
		}

	}
}


