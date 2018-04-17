package cn.edu.gdmec.android.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.sqlite.SQLiteHelper;

/**
 * Created by apple on 18/4/10.
 */

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteOpenHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context){
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context){
        if (instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }

    public void saveUserInfo(UserBean bean){
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", bean.userName);
        contentValues.put("nickName", bean.nickName);
        contentValues.put("sex", bean.sex);
        contentValues.put("signature", bean.signature);
        contentValues.put("qq", bean.qq);
        db.insert(SQLiteHelper.U_USERINFO, null, contentValues);
    }

    public UserBean getUserInfo(String userName){
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean userBean = null;
        while(cursor.moveToNext()){
            userBean = new UserBean();
            userBean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            userBean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            userBean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            userBean.signature = cursor.getString(cursor.getColumnIndex("signature"));
            userBean.qq = cursor.getString(cursor.getColumnIndex("qq"));
        }
        cursor.close();
        return userBean;
    }

    public void updateUserInfo(String key, String value, String userName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, contentValues, "UserName=?", new String[]{userName});
    }
}
