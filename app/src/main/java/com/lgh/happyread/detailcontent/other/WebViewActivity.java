package com.lgh.happyread.detailcontent.other;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lgh.happyread.R;
import com.lgh.happyread.base.ToolbarFragmentActivity;

public class WebViewActivity extends ToolbarFragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateToolTitle(getString(R.string.page));
    }


    @Override
    public Fragment newContentFragment() {
        return new WebViewFragment();
    }
}
