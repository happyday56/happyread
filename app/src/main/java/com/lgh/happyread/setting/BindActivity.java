package com.lgh.happyread.setting;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lgh.happyread.R;
import com.lgh.happyread.base.ToolbarFragmentActivity;

public class BindActivity extends ToolbarFragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateToolTitle(getString(R.string.bind));
    }


    @Override
    public Fragment newContentFragment() {
        return new BindFragment();
    }
}
