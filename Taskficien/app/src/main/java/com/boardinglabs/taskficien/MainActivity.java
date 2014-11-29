package com.boardinglabs.taskficien;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.boardinglabs.taskficien.fragment.LoginFragment;


public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTabs() ;
    }
    private void setTabs()
    {
        addTab("Home", R.drawable.tab_home, HomeActivity.class);
        addTab("List", R.drawable.tab_list, ListActivity.class);
        addTab("Settings", R.drawable.tab_settings, SettingsActivity.class);
    }

    private void addTab(String labelId, int drawableId, Class<?> c)
    {
        TabHost tabHost = getTabHost();
        Intent intent = new Intent(this, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View tabIndicator = inflator.inflate(R.layout.tab_indicator, getTabWidget(), false);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);

        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }

}
