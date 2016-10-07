package com.lgh.happyread.base;

import android.support.v7.app.AppCompatActivity;

import com.lgh.happyread.LAroundApplication;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();

        LAroundApplication.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LAroundApplication.onResume(this);
    }
}
