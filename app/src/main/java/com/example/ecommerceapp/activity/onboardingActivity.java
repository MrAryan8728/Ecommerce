package com.example.ecommerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.slideAdapter;

public class onboardingActivity extends AppCompatActivity {

ViewPager viewPager;
LinearLayout dotslayout;
TextView[] dots;
Animation animation;
Button button;
    com.example.ecommerceapp.adapter.slideAdapter slideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);
        //hide toolbar
        getSupportActionBar().hide();

        viewPager = findViewById(R.id.slider);
        dotslayout = findViewById(R.id.dots);
        button = findViewById(R.id.get_started_btn);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
        //Call Adapter
        slideAdapter = new slideAdapter(this);
        viewPager.setAdapter(slideAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(onboardingActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }
    private void addDots(int position){
        dots = new TextView[3];
        dotslayout.removeAllViews();
        for(int i = 0;i < dots.length;i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotslayout.addView(dots[i]);
        }
        if(dots.length > 0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.pink));
        }
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
           addDots(position);
           if(position == 0)
           {
               button.setVisibility(View.INVISIBLE);
           }
           else if(position == 1)
           {
               button.setVisibility(View.INVISIBLE);
           }
           else
           {
               animation = AnimationUtils.loadAnimation(onboardingActivity.this,R.anim.slide_animation);
               button.setAnimation(animation);
               button.setVisibility(View.VISIBLE);
           }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}