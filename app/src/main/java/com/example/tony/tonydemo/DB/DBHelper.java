package com.example.tony.tonydemo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lzy on 2016/10/8.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static String CREATE_NEWS =  "CREATE TABLE News ("+ "id INTEGER PRIMARY KEY autoincrement,"+ "title VARCHAR(100),"
            + "writer VARCHAR(45),"
            + "createtime INTEGER"
            + ")";
    private SQLiteDatabase db = this.getWritableDatabase();
    public DBHelper(Context context) {
        super(context, "tonyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // TODO Auto-generated method stub
       db.execSQL(CREATE_NEWS);
       Log.e("create","数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
