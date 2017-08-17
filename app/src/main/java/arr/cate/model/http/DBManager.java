package arr.cate.model.http;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import arr.cate.model.DaoMaster;
import arr.cate.model.DaoSession;
import arr.cate.model.ShopDao;
import arr.cate.model.ShopDaoDao;

/**
 * Created by tian on 2017/6/30.
 */

public class DBManager {

    private final static String dbName = "hahaha";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static synchronized DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    public SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    public SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 打开输出日志的操作,默认是关闭的
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }


    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(ShopDao user) {
        daoMaster = new DaoMaster(getWritableDatabase());
        daoSession = daoMaster.newSession();
        ShopDaoDao shopDaoDao = daoSession.getShopDaoDao();
        shopDaoDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<ShopDao> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShopDaoDao shopDaoDao = daoSession.getShopDaoDao();
        shopDaoDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(ShopDao user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShopDaoDao shopDaoDao = daoSession.getShopDaoDao();
        shopDaoDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(ShopDao user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShopDaoDao shopDaoDao = daoSession.getShopDaoDao();
        shopDaoDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public List<ShopDao> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShopDaoDao shopDaoDao = daoSession.getShopDaoDao();
        QueryBuilder<ShopDao> qb = shopDaoDao.queryBuilder();
        List<ShopDao> list = qb.list();
        return list;

    }

    /**
     * 查询用户列表
     */
    public List<ShopDao> queryUserList(Long id) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShopDaoDao shopDaoDao = daoSession.getShopDaoDao();
        QueryBuilder<ShopDao> qb = shopDaoDao.queryBuilder();
        qb.where(ShopDaoDao.Properties.Id.gt(id)).orderAsc(ShopDaoDao.Properties.Id);
        List<ShopDao> list = qb.list();
        return list;
    }


}

