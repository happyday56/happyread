package com.lgh.happyread.collection;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.lgh.happyread.datastore.DaoMaster;
import com.lgh.happyread.datastore.DaoSession;
import com.lgh.happyread.datastore.InfoItemDao;
import com.lgh.happyread.detailcontent.DetailActivity;
import com.lgh.happyread.detailcontent.DetailCache;
import com.lgh.happyread.model.BaseType;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class CollectPresenter implements  CollectContract.IPresenter {

    private static final CommonLog log = LogFactory.createLog();

    private Context mContext;
    private CollectContract.IView mView;

    private List<BaseType.InfoItemEx> mContentData = new ArrayList<BaseType.InfoItemEx>();


    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private InfoItemDao infoItemDao;
    private SQLiteDatabase db;

    public CollectPresenter(){

    }


    ///////////////////////////////////////     presenter callback begin
    @Override
    public void bindView(CollectContract.IView view) {
        mView = view;
        mView.bindPresenter(this);
    }

    @Override
    public void unBindView() {

    }

    @Override
    public void onEnterDetail(BaseType.InfoItemEx item) {
        DetailCache.getInstance().setTypeItem(item.mType);
        DetailCache.getInstance().setInfoItem(item);
        goContentActivity();
    }

    @Override
    public void clearCollcet() {
        infoItemDao.deleteAll();
        mContentData = infoItemDao.loadAll();
        mView.updateInfomationView(mContentData);
    }

    @Override
    public int getCollectCount() {
        return (int)infoItemDao.count();
    }
    ///////////////////////////////////////     presenter callback end


    ///////////////////////////////////////     lifecycle or ui operator begin
    public void onUiCreate(Context context){
        mContext = context;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "happyread-db", null);
        db = helper.getWritableDatabase();

        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        infoItemDao = daoSession.getInfoItemDao();

        mContentData = infoItemDao.loadAll();
        log.i("load all size = " + mContentData.size());
        mView.updateInfomationView(mContentData);
    }




    public void onUiDestroy() {
        if (db != null){
            db.close();
        }
    }
    ///////////////////////////////////////     lifecycle or ui operator end

    private void goContentActivity(){
        Intent intent = new Intent();
        intent.setClass(mContext, DetailActivity.class);
        mContext.startActivity(intent);
    }




}
