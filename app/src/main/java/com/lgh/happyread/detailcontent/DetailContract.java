package com.lgh.happyread.detailcontent;


import com.lgh.happyread.base.BasePresenter;
import com.lgh.happyread.base.BaseView;
import com.lgh.happyread.model.BaseType;

class DetailContract {
    interface IView extends BaseView<IPresenter> {
        void updateToolTitle(String title);

        void updateInfoItemEx(BaseType.InfoItemEx object);

        void onDestroy();
    }

    interface IPresenter extends BasePresenter<IView> {
        void shareToSina();

        void shareToTencent();

        void shareToWChat();

        void shareToWFriend();

        void shareToQZone();

        void enterWebView();

        void enterPhoneView();
    }

}
