package com.codekrypt.greendao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.codekrypt.greendao.db.LOG;
import com.codekrypt.greendao.db.Meal;

import com.codekrypt.greendao.db.LOGDao;
import com.codekrypt.greendao.db.MealDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig lOGDaoConfig;
    private final DaoConfig mealDaoConfig;

    private final LOGDao lOGDao;
    private final MealDao mealDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        lOGDaoConfig = daoConfigMap.get(LOGDao.class).clone();
        lOGDaoConfig.initIdentityScope(type);

        mealDaoConfig = daoConfigMap.get(MealDao.class).clone();
        mealDaoConfig.initIdentityScope(type);

        lOGDao = new LOGDao(lOGDaoConfig, this);
        mealDao = new MealDao(mealDaoConfig, this);

        registerDao(LOG.class, lOGDao);
        registerDao(Meal.class, mealDao);
    }
    
    public void clear() {
        lOGDaoConfig.clearIdentityScope();
        mealDaoConfig.clearIdentityScope();
    }

    public LOGDao getLOGDao() {
        return lOGDao;
    }

    public MealDao getMealDao() {
        return mealDao;
    }

}
