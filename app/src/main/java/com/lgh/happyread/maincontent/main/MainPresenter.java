package com.lgh.happyread.maincontent.main;

import android.app.Activity;
import android.content.Context;

import com.lgh.happyread.LAroundApplication;
import com.lgh.happyread.model.PublicType;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;

public class MainPresenter implements MainContract.IPresenter {

    private static final CommonLog log = LogFactory.createLog();
    private Context mContext;
    private MainContract.IView mView;

    private boolean loginStatus = false;

    ///////////////////////////////////////     presenter callback begin
    @Override
    public void bindView(MainContract.IView view) {
        mView = view;
        mView.bindPresenter(this);
    }

    @Override
    public void unBindView() {

    }
    ///////////////////////////////////////     presenter callback end


    ///////////////////////////////////////     lifecycle or ui operator begin
    public void onUiCreate(Context context){
        mContext = context;

        loginStatus = LAroundApplication.getInstance().getLoginStatus();
        if (!loginStatus) {
            log.e("loginStatus is false ,jump to welcome view!!!");
            LAroundApplication.getInstance().startToSplashActivity();
            if (mContext instanceof Activity){
                ((Activity)mContext).finish();
            }
            return;
        }

        initData();
    }
    ///////////////////////////////////////     lifecycle or ui operator end

    private void initData(){
        PublicType.UserLoginResult result = LAroundApplication.getInstance().getUserLoginResult();
        mView.updateNavView(result.mDataList);
    }



}
