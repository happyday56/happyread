package com.lgh.happyread.datastore;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import com.lgh.happyread.model.BaseType;
import com.lgh.happyread.util.CommonLog;
import com.lgh.happyread.util.LogFactory;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;



// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

	private static final CommonLog log = LogFactory.createLog();
	
    private final DaoConfig mInfoItemDaoConfig;
    private final InfoItemDao mInfoItemDao;
    

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);


        mInfoItemDaoConfig = daoConfigMap.get(InfoItemDao.class).clone();
        mInfoItemDaoConfig.initIdentityScope(type);
        mInfoItemDao = new InfoItemDao(mInfoItemDaoConfig, this);
        registerDao(BaseType.InfoItemEx.class, mInfoItemDao);


    }
    
    public void clear() {
    	mInfoItemDaoConfig.getIdentityScope().clear();
    }

    public InfoItemDao getInfoItemDao() {
        return mInfoItemDao;
    }



}
