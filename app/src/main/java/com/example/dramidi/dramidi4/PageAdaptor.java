package com.example.dramidi.dramidi4;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by dramidi on 4/5/2017.
 */

public class PageAdaptor  extends FragmentPagerAdapter {
    private String fragments [] = { "Drawing", "Properties"};
    public PageAdaptor(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new DrawingShape();
            case 1:

             return  new Properties_rectangle();

            default:
                return new DrawingShape();
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }

}





