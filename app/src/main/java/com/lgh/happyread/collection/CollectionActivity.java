package com.lgh.happyread.collection;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lgh.happyread.R;
import com.lgh.happyread.base.ToolbarFragmentActivity;

public class CollectionActivity extends ToolbarFragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateToolTitle(getString(R.string.collect));
    }


    @Override
    public Fragment newContentFragment() {
        return new CollectFragment();
    }
}
