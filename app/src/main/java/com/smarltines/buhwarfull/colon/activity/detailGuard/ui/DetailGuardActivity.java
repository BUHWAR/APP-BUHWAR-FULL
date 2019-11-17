package com.smarltines.buhwarfull.colon.activity.detailGuard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.smarltines.buhwarfull.R;
import com.smarltines.buhwarfull.colon.activity.detailGuard.ui.main.SectionsPagerAdapter;

public class DetailGuardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guard);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(DetailGuardActivity.this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.info_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.infoTabs);
        tabs.setupWithViewPager(viewPager);
    }
}
