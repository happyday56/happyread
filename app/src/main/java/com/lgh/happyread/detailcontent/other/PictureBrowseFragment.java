package com.lgh.happyread.detailcontent.other;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lgh.happyread.LAroundApplication;
import com.lgh.happyread.R;
import com.lgh.happyread.adapter.GalleryAdapterEx;
import com.lgh.happyread.base.BaseFragment;
import com.lgh.happyread.cache.FileCache;
import com.lgh.happyread.detailcontent.DetailCache;
import com.lgh.happyread.model.BaseType;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.CommonUtil;
import com.lgh.happyread.util.FileHelper;
import com.lgh.happyread.util.FileManager;
import com.lgh.happyread.util.LogFactory;
import com.lgh.happyread.widget.ImageViewEx;
import com.lgh.happyread.widget.PicGallery;

import java.util.List;

public class PictureBrowseFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    private static final CommonLog log = LogFactory.createLog();

    private Toolbar mToolbar;

    // 屏幕宽度
    public static int screenWidth;
    // 屏幕高度
    public static int screenHeight;
    private PicGallery gallery;
    private GalleryAdapterEx mAdapter;

    private BaseType.InfoItem mItem = new BaseType.InfoItem();
    private int mCurPos = 0;
    private int mTotalNum = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_view_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onUIReady(view);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.browse_options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_download:
                save();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void onUIReady(View view){
        initToolBar(view);

        screenWidth = getParentActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();
        screenHeight = getParentActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getHeight();


        gallery = (PicGallery)view.findViewById(R.id.pic_gallery);
        gallery.setVerticalFadingEdgeEnabled(false);// 取消竖直渐变边框
        gallery.setHorizontalFadingEdgeEnabled(false);// 取消水平渐变边框
        gallery.setDetector(new GestureDetector(getParentActivity(), new MySimpleGesture()));


        initData();

    }

    private void initToolBar(View view) {
        mToolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setBackgroundColor(Color.parseColor("#00ffffff"));
        if (getParentActivity() instanceof AppCompatActivity){
            AppCompatActivity appCompatActivity = (AppCompatActivity) getParentActivity();
            appCompatActivity.setSupportActionBar(mToolbar);
            final ActionBar ab = appCompatActivity.getSupportActionBar();
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void initData(){

        DetailCache mDetailCache = DetailCache.getInstance();
        mItem = mDetailCache.getInfoItem();
        List<String> list = mItem.mImageUrlList;
        log.i("mItem.mImageUrlList.size = " + mItem.mImageUrlList.size());
        mTotalNum = mItem.mImageUrlList.size();
        mAdapter = new GalleryAdapterEx(getParentActivity());
        gallery.setAdapter(mAdapter);
        gallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                return false;
            }
        });

        mAdapter.setData(list);

        gallery.setOnItemSelectedListener(this);

        updateTitle(0);

    }

    private void updateTitle(int pos){
        mToolbar.setTitle(String.valueOf(pos + 1) + "/" + String.valueOf(mTotalNum));
    }

    private void save(){
        LAroundApplication.getInstance().onEvent("SAVE01");
        boolean isSDCard = CommonUtil.hasSDCard();
        if (!isSDCard){
            CommonUtil.showToast(R.string.toast_save_fail, getParentActivity());
            return ;
        }

        if (mCurPos >= mItem.mImageUrlList.size()){

            return ;
        }

        if (!FileHelper.createDirectory(FileManager.getDownloadFileSavePath())){

            return ;
        }


        FileCache fileCache = new FileCache(getParentActivity());
        String url = mItem.mImageUrlList.get(mCurPos);
        String fromPath = fileCache.getSavePath(url);


        String toPath = FileManager.getDownloadFileSavePath() + String.valueOf(url.hashCode());
        boolean ret = FileHelper.saveBitmap(fromPath, toPath);
        if (ret){
            String text = getResources().getString(R.string.toast_save_success) + "," +
                    getResources().getString(R.string.toast_savefile_end) + toPath + ".jpg";

            CommonUtil.showToast(text, getParentActivity());
        }else{
            CommonUtil.showToast(R.string.toast_save_fail2, getParentActivity());
        }

    }

    private class MySimpleGesture extends GestureDetector.SimpleOnGestureListener {
        // 按两下的第二下Touch down时触发
        public boolean onDoubleTap(MotionEvent e) {

            View view = gallery.getSelectedView();
            if (view instanceof ImageViewEx) {
                ImageViewEx imageView = (ImageViewEx) view;
                if (imageView.getScale() > imageView.getMiniZoom()) {
                    imageView.zoomTo(imageView.getMiniZoom());
                } else {
                    imageView.zoomTo(imageView.getMaxZoom());
                }

            } else {

            }
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // Logger.LOG("onSingleTapConfirmed",
            // "onSingleTapConfirmed excute");
            // mTweetShow = !mTweetShow;
            // tweetLayout.setVisibility(mTweetShow ? View.VISIBLE
            // : View.INVISIBLE);
            return true;
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int pos,
                               long arg3) {

        log.e("onItemSelected pos = " + pos);

        mCurPos = pos;

        ImageViewEx imageViewEx = (ImageViewEx) view;
        boolean isDefaultBitmap = imageViewEx.getDefaultBitmapFlag();
        if (isDefaultBitmap){
            mAdapter.syncRefreshImageViewEx(imageViewEx);
        }

        updateTitle(mCurPos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
