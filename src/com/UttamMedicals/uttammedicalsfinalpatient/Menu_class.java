package com.UttamMedicals.uttammedicalsfinalpatient;



import android.app.Activity;
import android.content.Intent;



import android.nfc.cardemulation.OffHostApduService;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Menu_class extends Activity {

	Button about, capture, healthtips, offers, discalimer, newproducts;
	ConnectionDetector cd = new ConnectionDetector(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		initialize();
		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet()){
					Intent i = new Intent(Menu_class.this, About.class);
					startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_LONG).show();
				}
			}
		});

		healthtips.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet()){
					Intent ii = new Intent(Menu_class.this, TabHostActivity.class);
					startActivity(ii);
				}
				else{
					Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_LONG).show();
				}

			}
		});
		capture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet()){
					Intent c = new Intent(Menu_class.this, ListPrescMainActivity.class);
					startActivity(c);
				}
				else{
					Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_LONG).show();
				}
			}
		});

		offers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet()){
					Intent c = new Intent(Menu_class.this, SeeoffersActivity.class);
					startActivity(c);
				}
				else{
					Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_LONG).show();
				}
			}
		});

		discalimer.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent c = new Intent(Menu_class.this, Disclaimer.class);
				startActivity(c);
			}
		});

		newproducts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet()){
					Intent c = new Intent(Menu_class.this, SeenewProducts.class);
					startActivity(c);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please check your Internet connection", Toast.LENGTH_LONG).show();
				}
			}
		});

	}
	private void initialize() {
		// TODO Auto-generated method stub
		about = (Button)findViewById(R.id.about);
		capture = (Button)findViewById(R.id.btsnap);
		healthtips = (Button)findViewById(R.id.btnhealthtips);
		offers = (Button)findViewById(R.id.btnseeeoffers);
		discalimer = (Button)findViewById(R.id.btndiscalimer);
		newproducts = (Button)findViewById(R.id.btnseenewproducts);

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}