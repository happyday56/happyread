package com.lgh.happyread.splash;

import android.content.Context;

import com.lgh.happyread.base.BasePresenter;
import com.lgh.happyread.base.BaseView;
import com.lgh.happyread.model.PublicType;

public class SplashContract {
    public interface IView extends BaseView<IPresenter>{
        public void showForceUpdateDialog(Context context,  PublicType.UserLoginResult object);
        public void closeForceUpdateDialog();
    }

    public interface IPresenter extends BasePresenter<IView>{
        public void onUpdateSure();
        public void onUpdateCancel();
    }
}
