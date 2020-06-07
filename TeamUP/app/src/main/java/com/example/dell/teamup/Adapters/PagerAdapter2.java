package com.example.dell.teamup.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.teamup.CapSettings;
import com.example.dell.teamup.captain.CapChat;
import com.example.dell.teamup.captain.CapHome;
import com.example.dell.teamup.captain.CapRequest;
import com.example.dell.teamup.captain.CapSchedule;

/**
 * Created by Dell on 5/8/2018.
 */

public class PagerAdapter2 extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public PagerAdapter2(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs= NumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                CapHome tab1 = new CapHome();
                return tab1;
            case 1:
                CapRequest tab2 = new CapRequest();
                return tab2;
            case 2:
                CapSchedule tab3 = new CapSchedule();
                return tab3;
            case 3:
                CapChat tab4 = new CapChat();
                return tab4;
            case 4:
                CapSettings tab5 = new CapSettings();
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
