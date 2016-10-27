package com.example.tony.tonydemo.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lzy on 2016/10/8.
 */
public class NewsOpe {
    private DBHelper mDbHelper = null;
    private SQLiteDatabase mDb = null;
    private static String TABLE_NAME="news";
    private static String KEY_TITLE = "title";
    private static String KEY_WRITER = "writer";
    private static String KEY_TIME = "createtime";
    private Context mCtx;
    public NewsOpe(Context ctx) {
        mDbHelper = new DBHelper(ctx);
        mDb = mDbHelper.getWritableDatabase();
        this.mCtx= ctx;
    }

    public long insert(String title, String writer,long createtime)
    {
        long createResult = 0;
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_WRITER, writer);
        initialValues.put(KEY_TIME, createtime);
        try {
            createResult = mDb.insert(TABLE_NAME, null, initialValues);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return createResult;
    }

    @SuppressLint("Recycle")
    public ArrayList<News> getAllNews()
    {
        ArrayList<News> newlist = new ArrayList<>();
        Cursor mCursor = null;
        mCursor = mDb.query(TABLE_NAME, new String[] { KEY_TITLE, KEY_WRITER,
                KEY_TIME }, null, null, null, null, null);

        while ((mCursor.moveToNext()))
        {
            News tmp = new News();
            tmp.setTitle(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_TITLE)));
            tmp.setWriter(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_WRITER)));
            tmp.setCreatetime(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_TITLE)));
            newlist.add(tmp);
        }
        if (mCursor != null && !mCursor.isClosed()) {
            //mCursor.close();
        }
        return newlist;
    }

    public boolean deleteByTitle(String title)
    {
        int isDelete;
        String[] tName;
        tName = new String[] { title };
        isDelete = mDb.delete(TABLE_NAME, KEY_TITLE + "=?", tName);

        return isDelete > 0;
    }

    public News getNewsByTitle(String title)
    {
        ArrayList<News> newlist = new ArrayList<>();
        @SuppressLint("Recycle") Cursor mCursor = mDb.query(TABLE_NAME, new String[]{KEY_TITLE,KEY_WRITER,KEY_TIME},"title like ?",new String[]{"%aaa%"},null,null,null);
//        Cursor c = mDb.query(TABLE_NAME, new String[]{KEY_TITLE,KEY_WRITER,KEY_TIME},//select包含的字段   
//                   "title = ?",//where条件表达式  
//         new String[]{title},  //条件值  
//                null,  //group子句  
//                null,  //having子句  
//                null //排序字段  
//        );

        while ((mCursor.moveToNext()))
        {
            News tmp = new News();
            tmp.setTitle(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_TITLE)));
            tmp.setWriter(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_WRITER)));
            tmp.setCreatetime(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_TITLE)));
            newlist.add(tmp);
        }
        if (mCursor != null && !mCursor.isClosed()) {
            //mCursor.close();
        }
        return newlist.get(0);
    }
}
