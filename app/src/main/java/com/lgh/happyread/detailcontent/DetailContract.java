package com.lgh.happyread.detailcontent;


import com.lgh.happyread.base.BasePresenter;
import com.lgh.happyread.base.BaseView;
import com.lgh.happyread.model.BaseType;

public class DetailContract {
    public interface IView extends BaseView<IPresenter> {
        public  void updateToolTitle(String title);
        public void updateInfoItemEx(BaseType.InfoItemEx object);
        public void onDestroy();
    }

    public interface IPresenter extends BasePresenter<IView>{
        public void   shareToSina();
        public void   shareToTencent();
        public void   shareToWChat();
        public void   shareToWFriend();
        public void   shareToQZone();
        public void   enterWebView();
        public void   enterPhoneView();
    }

}
