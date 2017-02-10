package com.UttamMedicals.uttammedicalsfinalpatient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;


public class Patient_see_offersimage extends Activity {
	ImageView iv;
	String get_image_path;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.seeofferimage);
	initialize();

	Intent i = getIntent();
	 get_image_path = i.getExtras().getString("image_path");
	 Log.e("image_path ", ""+get_image_path);
	 clickGetImage(get_image_path);
	//String data = i.getExtras().getInt("image_path");
//	Log.e("image_path ", ""+data);
//
//	see_item_name_image = dbh.getitemnameimage(data);
	
}

private void initialize() {
	// TODO Auto-generated method stub
	iv = (ImageView) findViewById(R.id.IV_offerimage);
}


public void clickGetImage(String get_image_path)
{
	
	byte[] decodedString = Base64.decode(get_image_path, Base64.NO_WRAP);
	Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
	iv.setImageBitmap(decodedByte);

	}

}
