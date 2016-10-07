package com.lgh.happyread.detailcontent;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lgh.happyread.base.ToolbarFragmentActivity;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;

public class DetailActivity extends ToolbarFragmentActivity {

    private static final CommonLog log = LogFactory.createLog();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public Fragment newContentFragment() {
        return new DetailFragment();
    }
}
