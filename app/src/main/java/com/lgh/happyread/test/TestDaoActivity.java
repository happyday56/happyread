package com.lgh.happyread.test;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.lgh.happyread.R;
import com.lgh.happyread.datastore.DaoMaster;
import com.lgh.happyread.datastore.DaoMaster.DevOpenHelper;
import com.lgh.happyread.datastore.DaoSession;
import com.lgh.happyread.datastore.InfoItemDao;
import com.lgh.happyread.model.BaseType;
import com.lgh.happyread.model.BaseType.InfoItem;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class TestDaoActivity extends Activity implements OnClickListener, OnItemClickListener{

private static final CommonLog log = LogFactory.createLog();
	
	private Button mBtnAdd;
	private Button mBtnReset;
	private ListView listView;
	
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private InfoItemDao infoItemDao;
    private SQLiteDatabase db;
    
    private TestContentExAdapter mAdapter;
	private List<BaseType.InfoItemEx> data = new ArrayList<BaseType.InfoItemEx>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
				
		setupViews();	
		initData();	
	}

	@Override
	protected void onDestroy() {

		db.close();
		
		super.onDestroy();
	}

	
	private void setupViews(){
		setContentView(R.layout.test_dao_layout);
		
		mBtnAdd = (Button) findViewById(R.id.btnAdd);
		mBtnReset = (Button) findViewById(R.id.btnReset);

		
		mBtnAdd.setOnClickListener(this);
		mBtnReset.setOnClickListener(this);

		listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);
	}
	
	private void initData(){

        DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "0happyread-db", null);
        db = helper.getWritableDatabase();

        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        infoItemDao = daoSession.getInfoItemDao();


        mAdapter = new TestContentExAdapter(this, data);
        listView.setAdapter(mAdapter);

    	data = infoItemDao.loadAll();
    	log.e("data = "  + data + ",size = " + data.size());
    	if (data.size() > 0){
        	BaseType.InfoItemEx item = data.get(data.size() - 1);
        	log.e("item = " + item);
        	if (item != null){
          		key = Integer.valueOf(item.mKeyID);
        		mAdapter.refreshData(data);		
        	}
  
    	}

    
	}
	

	
	private void refreshData(){

		data = infoItemDao.loadAll();
		log.e("load all size = " + data.size());
		mAdapter.refreshData(data);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btnAdd:
				add();
				break;
			case R.id.btnReset:
				reset();
				break;
		}
	}
	
	int key = 10;	
	private void add(){
		BaseType.InfoItemEx item = new BaseType.InfoItemEx(String.valueOf(key), 0, "title", "content", "time", "commentCount",
									"linkcount", "username", "sourceFrom",  "sourceUrl", 
									"headpath", "imageurl", "thuURL");
		
		long id = infoItemDao.insert(item);
		log.e("infoItemDao insert id = " + id);
		key += 5;
		
		refreshData();
	}
	
	private void reset(){
		infoItemDao.deleteAll();
		
		refreshData();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int pos, long arg3) {
		BaseType.InfoItem item = (InfoItem) adapterView.getItemAtPosition(pos);
		
		infoItemDao.deleteByKey(item.mKeyID);
	    Log.d("TestDaoActivity", "Deleted note, ID: " + item.mKeyID);

	    refreshData();
	}

}
