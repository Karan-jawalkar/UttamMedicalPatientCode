package com.UttamMedicals.uttammedicalsfinalpatient;




import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabHostActivity extends TabActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
 
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
 
        intent = new Intent().setClass(this, SummerActivity.class);
        spec = tabHost.newTabSpec("Summer Season").setIndicator("Summer Season")
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, RainActivity.class);
        spec = tabHost.newTabSpec("Rainy Season").setIndicator("Rainy Season")
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, WinterActivity.class);
        spec = tabHost.newTabSpec("Winter Season").setIndicator("Winter Season")
                      .setContent(intent);
        tabHost.addTab(spec);
 
    }
}