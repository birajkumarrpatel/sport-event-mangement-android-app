package com.example.dell.teamup.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toolbar;

import com.example.dell.teamup.Organizer_TabFragment5;
import com.example.dell.teamup.organizer.Organizer_TabFragment1;
import com.example.dell.teamup.organizer.Organizer_TabFragment2;
import com.example.dell.teamup.organizer.Organizer_TabFragment3;
import com.example.dell.teamup.organizer.Organizer_TabFragment4;

/**
 * Created by Dell on 3/29/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    android.support.v7.widget.Toolbar toolbar;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, android.support.v7.widget.Toolbar toolbar){
        super(fm);
        this.mNumOfTabs= NumOfTabs;
        this.toolbar = toolbar;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Organizer_TabFragment1 tab1 = new Organizer_TabFragment1();
                return tab1;
            case 1:
                Organizer_TabFragment2 tab2 = new Organizer_TabFragment2();
                return tab2;
            case 2:
                Organizer_TabFragment3 tab3 = new Organizer_TabFragment3();
                return tab3;
            case 3:
                Organizer_TabFragment4 tab4 = new Organizer_TabFragment4();
                return tab4;
            case 4:
                Organizer_TabFragment5 tab5 = new Organizer_TabFragment5();
                return tab5;
            default:
                return null;
        }
    }
    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}
