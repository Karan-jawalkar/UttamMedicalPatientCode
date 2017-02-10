package com.UttamMedicals.uttammedicalsfinalpatient;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class SplashActivity extends Activity {
		
	DatabaseHandler dbh = new DatabaseHandler(this);

//	SharedPreferences prefs;
//	SharedPreferences settings;;
//	public String mobile_number;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		String email = dbh.getUsername();
	//	Log.e("email", ""+email);
		if(email != null)	{
			Intent i = new Intent(SplashActivity.this, Menu_class.class);
			startActivity(i);
			finish();
		}
		else
		{
			Intent i = new Intent(SplashActivity.this, Registration.class);
			startActivity(i);
			finish();
	
		}

		Thread logoTimer = new Thread() {
			public void run(){
				try{
					int logoTimer = 0;
					while(logoTimer < 5000){
						sleep(100);
						logoTimer = logoTimer +100;
					};
				
//						                    startActivity(new Intent("com.tutorial.CLEARSCREEN"));

//					prefs = getSharedPreferences("com.intuitionit.reminderapp",0);  
//					
////					String mobile_number = settings.getString("mobile_number", "");
//
//					mobile_number = prefs.getString("mobile_number", "");
//					
//					Log.e("splash mobile number", ""+mobile_number.length());

//					if (mobile_number.length()>9) {
//
//						Intent mainIntent = new Intent(SplashActivity.this,TabHostActivity.class);
//						startActivity(mainIntent);
//					}
//					else	
//					{
//						Intent mainIntent = new Intent(SplashActivity.this,RegistrationActivity.class);
//						startActivity(mainIntent);
//					}

				} 

				catch (InterruptedException e) {
					// TODO Auto-generated catch block	
					e.printStackTrace();
				}

				finally{
					finish();
				}
			}
		};

		logoTimer.start();
	}
}