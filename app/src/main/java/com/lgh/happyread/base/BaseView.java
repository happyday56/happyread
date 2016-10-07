package com.lgh.happyread.base;

import android.view.View;

public interface BaseView  <T extends BasePresenter>{
    public void bindPresenter(T  presenter);
    public void setupView(View rootView);
}
