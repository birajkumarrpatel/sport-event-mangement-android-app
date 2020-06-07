package com.example.dell.teamup;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dell.teamup.Adapters.PagerAdapter;
import com.example.dell.teamup.organizer.Organizer_TabFragment1;
import com.example.dell.teamup.organizer.Organizer_TabFragment2;
import com.example.dell.teamup.organizer.Organizer_TabFragment3;
import com.example.dell.teamup.organizer.Organizer_TabFragment4;

public class Home extends AppCompatActivity {
    TabLayout tabLayout;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Organizer");

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_playlist_play_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_schedule_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_settings_black_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),toolbar);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0)
                {
                    toolbar.setTitle("Home");
                }
                else if(tab.getPosition() == 1)
                {
                    toolbar.setTitle("Team Request");
                }
                else if(tab.getPosition() == 2)
                {
                    toolbar.setTitle("Team");
                }
                else if(tab.getPosition() == 3)
                {
                    toolbar.setTitle("Match Schedule");
                }
                else if(tab.getPosition() == 4)
                {
                    toolbar.setTitle("Setting");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





    }

}
