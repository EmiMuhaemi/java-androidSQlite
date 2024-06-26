package com.example.undipssql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UniversityDB extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "university.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "data";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "name";
    public static final String COLUMN_ALAMAT = "address";
    public UniversityDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAMA + " TEXT, " +
                        COLUMN_ALAMAT + " TEXT);" ;

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addData(int id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAMA, name);
        cv.put(COLUMN_ALAMAT, address);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success to add data", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }
    void updateData(String id, String name, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAMA, name);
        cv.put(COLUMN_ALAMAT, address);

        long result = db.update(TABLE_NAME, cv, "id=?" ,new String[]{id});
        if (result == -1 ){
            Toast.makeText(context, "Failed to update data",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success to add data",Toast.LENGTH_SHORT).show();
        }
    }
    void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if (result == -1 ){
            Toast.makeText(context, "Failed to delete data",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success to update data",Toast.LENGTH_SHORT).show();
        }
    }

}
