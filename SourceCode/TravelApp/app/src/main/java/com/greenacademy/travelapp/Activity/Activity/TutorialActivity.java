package com.greenacademy.travelapp.Activity.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.greenacademy.travelapp.Activity.Adapter.ScreenSlideFragmentAdapter;
import com.greenacademy.travelapp.Activity.Animation.ZoomOutPageTranformer;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment1;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment2;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment3;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment4;
import com.greenacademy.travelapp.R;

import java.util.LinkedList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    ViewPager tutorialPager;
    ScreenSlideFragmentAdapter myPagerAdapter;
    RadioGroup rgpTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        rgpTutorial = (RadioGroup) findViewById(R.id.rgpTutorial);
        rgpTutorial.check(R.id.rbtn1);

        initViewPager();
        tutorialPager.setPageTransformer(true, new ZoomOutPageTranformer());
        tutorialPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = tutorialPager.getCurrentItem();
                switch (position){
                    case 0:
                        rgpTutorial.check(R.id.rbtn1);
                        break;
                    case 1:
                        rgpTutorial.check(R.id.rbtn2);
                        break;
                    case 2:
                        rgpTutorial.check(R.id.rbtn3);
                        break;
                    case 3:
                        rgpTutorial.check(R.id.rbtn4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initViewPager() {
        tutorialPager = (ViewPager) findViewById(R.id.myViewPager);
        List<Fragment> myFragmentList = new LinkedList<>();
        myFragmentList.add(new TutorialFragment1());
        myFragmentList.add(new TutorialFragment2());
        myFragmentList.add(new TutorialFragment3());
        myFragmentList.add(new TutorialFragment4());
        myPagerAdapter = new ScreenSlideFragmentAdapter(getSupportFragmentManager(), myFragmentList);

        tutorialPager.setAdapter(myPagerAdapter);
    }


}
