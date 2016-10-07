package com.lgh.happyread.detailcontent.other;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.lgh.happyread.R;
import com.lgh.happyread.base.BaseActivity;

public class PictureBrowerActivity extends BaseActivity {

    private final String TAG_FRAGMENT = "Content_Fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_activity_layout);
        getFragmentManager().beginTransaction().add(R.id.content_container, newContentFragment(), TAG_FRAGMENT).commit();
    }


    public Fragment newContentFragment() {
        return new PictureBrowseFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
