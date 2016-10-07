
package com.lgh.happyread;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;


import com.lgh.happyread.maincontent.main.MainActivity;
import com.lgh.happyread.model.PublicType;
import com.lgh.happyread.splash.SplashActivity;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import cn.sharesdk.framework.ShareSDK;
public class LAroundApplication extends Application  implements ItatisticsEvent{

	private static final CommonLog log = LogFactory.createLog();
	
	private static LAroundApplication mInstance;

	private PublicType.UserLoginResult mUserLoginResult = new PublicType.UserLoginResult();
	
	
	private boolean isLogin = false;
	
	private PublicType.CheckUpdateResult updateObject = null;
	
	
	public synchronized static LAroundApplication getInstance(){
		return mInstance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		log.e("LAroundApplication  onCreate!!!");
		mInstance = this;
		startBackgroundService();
		MobclickAgent.setDebugMode(true);
		
		TCAgent.init(this);
		TCAgent.setReportUncaughtExceptions(true);
		ShareSDK.initSDK(this);
	}
	
	public void setUserLoginResult(PublicType.UserLoginResult object){
		mUserLoginResult = object;
	}
	
	public PublicType.UserLoginResult getUserLoginResult(){
		return mUserLoginResult;
	}
	
	public void setLoginStatus(boolean flag){
		isLogin = flag;
	}
	
	public PublicType.CheckUpdateResult  getNewVersion(){
	
		return updateObject;
	}
	
	public void setNewVersionFlag(PublicType.CheckUpdateResult object){
		updateObject = object;
	}
	
	public boolean getLoginStatus(){
	
		return isLogin;
	}
	
	public void startToSplashActivity(){
		Intent intent = new Intent();
		intent.setClass(this, SplashActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	public void startToMainActivity(){
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	private void startBackgroundService(){
		Intent intent = new Intent();
		intent.setClass(this, BackgroundService.class);
		startService(intent);
	}

	@Override
	public void onEvent(String eventID) {
		log.e("eventID = " + eventID);
		
		MobclickAgent.onEvent(this, eventID);
		TCAgent.onEvent(this, eventID);
	}

	@Override
	public void onEvent(String eventID, HashMap<String, String> map) {
		log.e("eventID = " + eventID);
	
		MobclickAgent.onEvent(this, eventID, map);
		TCAgent.onEvent(this, eventID, "", map);
	}
	
	public static void onPause(Activity context){
		MobclickAgent.onPause(context);
		TCAgent.onPause(context);
	}
	
	public static void onResume(Activity context){
		MobclickAgent.onResume(context);
		TCAgent.onResume(context);
	}
	
	public static void onCatchError(Context context){

		TCAgent.setReportUncaughtExceptions(true);
	}
}
