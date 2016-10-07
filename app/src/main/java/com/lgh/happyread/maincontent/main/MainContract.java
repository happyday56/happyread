package com.lgh.happyread.maincontent.main;


import com.lgh.happyread.base.BasePresenter;
import com.lgh.happyread.base.BaseView;
import com.lgh.happyread.model.BaseType;

import java.util.List;

public class MainContract {
    public interface IView extends BaseView<IPresenter> {
        public void updateNavView(List<BaseType.ListItem> dataList);
    }

    public interface IPresenter extends BasePresenter<IView> {
    }
}
