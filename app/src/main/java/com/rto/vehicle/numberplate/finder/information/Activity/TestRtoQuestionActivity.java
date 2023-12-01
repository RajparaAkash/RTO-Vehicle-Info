package com.rto.vehicle.numberplate.finder.information.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rto.vehicle.numberplate.finder.information.Fragment.QuestionsFragment;
import com.rto.vehicle.numberplate.finder.information.Fragment.TrafficSignalsFragment;
import com.rto.vehicle.numberplate.finder.information.LocaleHelper;
import com.rto.vehicle.numberplate.finder.information.MyApplication;
import com.rto.vehicle.numberplate.finder.information.R;

import com.sdk.dierct.link.open.on.fail.sdk.Native_AdPreload1;

import java.util.ArrayList;
import java.util.List;

public class TestRtoQuestionActivity extends AppCompatActivity {

    Context context;
    Resources mResources;
    TextView set11;
    TextView set22;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_rto_question);
        Native_AdPreload1.getInstance(this).Native_Banner_Ads(this, findViewById(R.id.native_banner));

        (findViewById(R.id.back_img)).setOnClickListener(view -> onBackPressed());
        Context locale = LocaleHelper.setLocale(this, MyApplication.EXAM_RESOURCE);
        this.context = locale;
        this.mResources = locale.getResources();

        this.set11 = findViewById(R.id.set11);
        this.set22 = findViewById(R.id.set22);
        this.set11.setTextColor(getResources().getColor(R.color.white));
        this.set22.setTextColor(getResources().getColor(R.color.text_color));
        this.set11.setBackground(getResources().getDrawable(R.drawable.layout_selected));
        this.set22.setBackground(getResources().getDrawable(R.drawable.layout_unselected));

        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    set11.setTextColor(getResources().getColor(R.color.white));
                    set22.setTextColor(getResources().getColor(R.color.text_color));
                    set11.setBackground(getResources().getDrawable(R.drawable.layout_selected));
                    set22.setBackground(getResources().getDrawable(R.drawable.layout_unselected));
                } else if (i == 1) {
                    set11.setTextColor(getResources().getColor(R.color.text_color));
                    set22.setTextColor(getResources().getColor(R.color.white));
                    set11.setBackground(getResources().getDrawable(R.drawable.layout_unselected));
                    set22.setBackground(getResources().getDrawable(R.drawable.layout_selected));
                }
            }
        });
        this.set11.setOnClickListener(view -> {
            viewPager.setCurrentItem(0);
            set11.setTextColor(getResources().getColor(R.color.white));
            set22.setTextColor(getResources().getColor(R.color.text_color));
            set11.setBackground(getResources().getDrawable(R.drawable.layout_selected));
            set22.setBackground(getResources().getDrawable(R.drawable.layout_unselected));
        });
        this.set22.setOnClickListener(view -> {
            viewPager.setCurrentItem(1);
            set11.setTextColor(getResources().getColor(R.color.text_color));
            set22.setTextColor(getResources().getColor(R.color.white));
            set11.setBackground(getResources().getDrawable(R.drawable.layout_unselected));
            set22.setBackground(getResources().getDrawable(R.drawable.layout_selected));
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new QuestionsFragment(), "que");
        viewPagerAdapter.addFragment(new TrafficSignalsFragment(), "traffic");
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList;
        private final List<String> mFragmentTitleList;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.mFragmentList = new ArrayList();
            this.mFragmentTitleList = new ArrayList();
        }

        @Override
        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }
}
