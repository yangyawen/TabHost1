package com.example.admin.tabhost1;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

import com.example.admin.tabhost1.activities.ContactActivity;
import com.example.admin.tabhost1.activities.DataActivity;
import com.example.admin.tabhost1.activities.PlansActivity;
import com.example.admin.tabhost1.activities.SettingActivity;
import com.example.admin.tabhost1.views.BaseHomeView;
import com.example.admin.tabhost1.views.MyView;
import com.example.admin.tabhost1.views.TabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity implements BaseHomeView, View.OnClickListener {

    private static final String LOG_TAG = "MainActivity";

    //@InjectView(R.id.tab_host) TabHost tabHost;
    @InjectView(R.id.container) ViewPager container;

    @InjectView(R.id.tab_plans) MyView plansTab;
    @InjectView(R.id.tab_data) MyView dataTab;
    @InjectView(R.id.tab_contacts) MyView contactTab;
    @InjectView(R.id.tab_setting) MyView settingTab;

    private List<TabFragment> fragmentList = new ArrayList<TabFragment>();
    private List<MyView> tabContainer = new ArrayList<MyView>();
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        // 主要做些初始化操作，然后监听就行了
        //tabHost = new TabHost(this);

        //tabHost.addTab(tabHost.newTabSpec("plans").setIndicator("plans").setContent(new Intent(this, PlansActivity.class)));
        //tabHost.addTab(tabHost.newTabSpec("data").setIndicator("data").setContent(new Intent(this, DataActivity.class)));
        //tabHost.addTab(tabHost.newTabSpec("contacts").setIndicator("contacts").setContent(new Intent(this, ContactActivity.class)));
        //tabHost.addTab(tabHost.newTabSpec("settings").setIndicator("settings").setContent(new Intent(this, SettingActivity.class)));

        //MyView
        tabContainer.add(plansTab);
        tabContainer.add(dataTab);
        tabContainer.add(contactTab);
        tabContainer.add(settingTab);

        plansTab.setOnClickListener(this);
        dataTab.setOnClickListener(this);
        contactTab.setOnClickListener(this);
        settingTab.setOnClickListener(this);

        plansTab.setIconAlpha(1.0f);

        String[] titles = new String[]{ "Page One", "Page Two", "Page Three",	"Page Four" };
        for (String title : titles) {
            TabFragment fragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TITLE, title);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()){

            @Override
            public int getCount(){
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position){
                return fragmentList.get(position);
            }
        };
        container.setAdapter(adapter);
    }

    @Override
    public void onClick(View v){

        refreshTabs();

        switch (v.getId()) {
            case R.id.tab_plans:
                tabContainer.get(0).setIconAlpha(1.0f);
                container.setCurrentItem(0, false);

                //tabHost.setCurrentTabByTag("plans");
                //plansTab.setSelected(true);
                break;

            case R.id.tab_data:
                tabContainer.get(1).setIconAlpha(1.0f);
                tabContainer.get(1).setSelected(true);
                container.setCurrentItem(1, false);

                //tabHost.setCurrentTabByTag("data");
                //dataTab.setSelected(true);
                break;

            case R.id.tab_contacts:
                tabContainer.get(2).setIconAlpha(1.0f);
                container.setCurrentItem(2, false);
                //tabHost.setCurrentTabByTag("contacts");
                //contactTab.setSelected(true);

                break;

            case R.id.tab_setting:
                tabContainer.get(3).setIconAlpha(1.0f);
                container.setCurrentItem(3, false);
                //tabHost.setCurrentTabByTag("settings");
                //settingTab.setSelected(true);
                break;

            default:
                break;

        }

    }

    private void refreshTabs() {
        for (int i = 0; i < tabContainer.size(); i++){
            tabContainer.get(i).setIconAlpha(0);
        }

    }



}
