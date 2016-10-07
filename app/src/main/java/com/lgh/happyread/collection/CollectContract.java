package com.lgh.happyread.collection;


import com.lgh.happyread.base.BasePresenter;
import com.lgh.happyread.base.BaseView;
import com.lgh.happyread.model.BaseType;

import java.util.List;

public class CollectContract {
    public interface IView extends BaseView<IPresenter> {
        public void updateInfomationView(List<BaseType.InfoItemEx> dataList);
        public void showDeleteDialog();
    }

    public interface IPresenter extends BasePresenter<IView> {
        public void onEnterDetail(BaseType.InfoItemEx item);
        public void clearCollcet();
        public int getCollectCount();
    }
}
