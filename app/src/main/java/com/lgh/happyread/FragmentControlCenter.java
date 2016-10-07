package com.lgh.happyread;

import android.content.Context;

import com.lgh.happyread.maincontent.infomation.InfomationFragment;
import com.lgh.happyread.model.BaseType;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class FragmentControlCenter {

	private static final CommonLog log = LogFactory.createLog();
	
	private static  FragmentControlCenter instance;
	private Context mContext;
	
	private Map<String,InfomationFragment> mFragmentModelMaps= new HashMap<String, InfomationFragment>();
	

	private FragmentControlCenter(Context context) {
		mContext = context;
	}
	
	public static synchronized FragmentControlCenter getInstance(Context context) {
		if (instance == null){
			instance  = new FragmentControlCenter(context);
		}
		return instance;
	}

	public InfomationFragment getCommonFragmentEx(BaseType.ListItem object){
		InfomationFragment fragment = mFragmentModelMaps.get(object.mTypeID);
		if (fragment == null){
			fragment = new InfomationFragment(object);
			mFragmentModelMaps.put(object.mTypeID, fragment);
		}
		
		return fragment;
	}

	

}
