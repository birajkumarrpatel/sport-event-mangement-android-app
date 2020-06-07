package com.example.dell.teamup.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.teamup.PlayerFragment5;
import com.example.dell.teamup.player.PlayerFragment1;
import com.example.dell.teamup.player.PlayerFragment2;
import com.example.dell.teamup.player.PlayerFragment3;
import com.example.dell.teamup.player.PlayerFragment4;

/**
 * Created by Dell on 3/29/2018.
 */

public class PagerAdapter1 extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter1(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs= NumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                PlayerFragment1 tab1 = new PlayerFragment1();
                return tab1;
            case 1:
                PlayerFragment2 tab2 = new PlayerFragment2();
                return tab2;
            case 2:
                PlayerFragment3 tab3 = new PlayerFragment3();
                return tab3;
            case 3:
                PlayerFragment4 tab4 = new PlayerFragment4();
                return tab4;
            case 4:
                PlayerFragment5 tab5 = new PlayerFragment5();
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
