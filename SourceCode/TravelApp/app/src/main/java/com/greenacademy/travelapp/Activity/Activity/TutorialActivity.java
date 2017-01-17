package com.greenacademy.travelapp.Activity.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.greenacademy.travelapp.Activity.Adapter.ScreenSlideFragmentAdapter;
import com.greenacademy.travelapp.Activity.Animation.ZoomOutPageTranformer;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment1;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment2;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment3;
import com.greenacademy.travelapp.Activity.Fragment.TutorialFragment4;
import com.greenacademy.travelapp.Activity.LoginActivity.ActivityLogin;
import com.greenacademy.travelapp.Activity.MainActivity;
import com.greenacademy.travelapp.R;

import java.util.LinkedList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    ViewPager tutorialPager;
    ScreenSlideFragmentAdapter myPagerAdapter;
    RadioGroup rgpTutorial;
    Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        rgpTutorial = (RadioGroup) findViewById(R.id.rgpTutorial);
        rgpTutorial.check(R.id.rbtn1);


        innitButton();

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
                if(position == 3){
                    btnSkip.setText(R.string.button_start);
                }else {
                    btnSkip.setText(R.string.button_skip);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void innitButton() {
        btnSkip = (Button) findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, ActivityLogin.class);
                startActivity(intent);
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
