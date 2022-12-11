package com.example.artexhibition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
    public class FavDB extends SQLiteOpenHelper {
        public static final String DBNAME = "Login";
        private static int DB_VERSION = 1;
        private static String DATABASE_NAME = "favouriteDB";
        private static String TABLE_NAME = "favoriteTable";
        public static String KEY_ID = "id";
        public static String ITEM_TITLE = "itemTitle";
        public static String ITEM_IMAGE = "itemImage";
        public static String FAVORITE_STATUS = "fStatus";
        public static String ITEM_DESC ="itemDesc";


        private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " TEXT," + ITEM_TITLE+ " TEXT,"
                + ITEM_IMAGE + " TEXT," + FAVORITE_STATUS+" TEXT,"
            + ITEM_DESC + " TEXT)";

        public FavDB(Context context) { super(context,"Login",null,DB_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create Table users(username TEXT primary key, password Text)");
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        public Boolean insertData(String username, String password){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("password", password);
            long result = sqLiteDatabase.insert("users", null, contentValues);
            if (result==1)return false;
            else
                return true;
        }
        public Boolean checkusername(String username){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ?", new String[]{username});
            if (cursor.getCount() > 0) return true;
            else
                return false;
        }
        public Boolean checkusernamepassword(String username, String password){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
            if (cursor.getCount() > 0) return true;
            else
                return false;
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop Table if exists users");
        }
        public void insertEmpty() {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            for (int x = 1; x < 13; x++) {
                cv.put(KEY_ID, x);
                cv.put(FAVORITE_STATUS, "0");
                db.insert(TABLE_NAME,null, cv);
            }
        }
        public void insertIntoTheDatabase(String item_title, int item_image, String id, String fav_status) {
            SQLiteDatabase db;
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(ITEM_TITLE, item_title);
            cv.put(ITEM_IMAGE, item_image);
            cv.put(KEY_ID, id);
            cv.put(FAVORITE_STATUS, fav_status);
          //  cv.put(ITEM_DESC,item_desc);

            db.insert(TABLE_NAME,null, cv);
            Log.d("FavDB Status", item_title + ", favstatus - "+fav_status+" - . " + cv);
        }

        public Cursor read_all_data(String id) {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from " + TABLE_NAME + " where " + KEY_ID+"="+id+"";
            return db.rawQuery(sql,null,null);
        }

        public void remove_fav(String id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVORITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
            db.execSQL(sql);
            Log.d("remove", id.toString());

        }
        public Cursor select_all_favorite_list() {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
            return db.rawQuery(sql,null,null);
        }}
