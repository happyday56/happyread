package com.lgh.happyread.maincontent.infomation;


import com.lgh.happyread.base.BasePresenter;
import com.lgh.happyread.base.BaseView;
import com.lgh.happyread.model.BaseType;

import java.util.List;

public class InfomationContract {
    public interface IView extends BaseView<IPresenter> {
        public void showFailView(boolean bShow);
        public void showLoadView(boolean bShow);
        public void updateInfomationView(List<BaseType.InfoItem> dataList);
        public void updateLoadMoreViewState(int state);
    }

    public interface IPresenter extends BasePresenter<IView> {
        public void onPullRefresh();
        public void onLoadMore();
        public void onEnterDetail(BaseType.InfoItem item);
    }
}
