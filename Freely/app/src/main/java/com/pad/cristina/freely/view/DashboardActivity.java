package com.pad.cristina.freely.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.pad.cristina.freely.R;
import com.pad.cristina.freely.util.ZoomOutPageTransformer;
import com.pad.cristina.freely.view.fragments.EventFragment;

public class DashboardActivity extends FragmentActivity  implements EventFragment.OnFragmentInteractionListener{

    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mPager = findViewById(R.id.pager);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent intent1 = new Intent(DashboardActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_events:
                        Intent intent3 = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.action_favorites:
                        Intent intent2 = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new EventFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent intent1 = new Intent(DashboardActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_events:
                        Intent intent3 = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.action_favorites:
                        Intent intent2 = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}
