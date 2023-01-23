package com.example.qualifandro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.qualifandro.Models.Job;
import com.example.qualifandro.Models.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserData.db";

    public final String USER_TABLE = "users";
    public final String ID = "id";
    public final String USERNAME = "username";
    public final String EMAIL = "email";
    public final String PASSWORD = "password";
    public final String PHONENUMBER = "phone_number";

    public final static String JOBS_TABLE = "jobs";
    public final static String JOBID = "id";
    public final static String JOBNAME = "name";
    public final static String COMPANY = "company";
    public final static String ADDRESS = "address";
    public final static String DESCRIPTION = "description";

    private static DBHelper db = null;

    public static DBHelper getInstance(Context context){
        return db = (db == null) ? new DBHelper(context) : db;
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "create table " + USER_TABLE + " (" +
                ID + " INTEGER not null PRIMARY KEY AUTOINCREMENT, " +
                USERNAME + " text not null, " +
                EMAIL +" text not null, " +
                PASSWORD +" text not null, " +
                PHONENUMBER+ " text not null " +
                ")";

        String CREATE_JOBS_TABLE = "create table jobs (" +
                "id integer not null primary key autoincrement, " +
                JOBNAME + " text not null, " +
                COMPANY + " text not null, " +
                ADDRESS + " text not null, " +
                DESCRIPTION + " text not null " +
                ")";

        String INSERT_DUMMY_JOB = "insert into " + JOBS_TABLE + " (" +
                JOBNAME + ", " +
                COMPANY + ", " +
                ADDRESS + ", " +
                DESCRIPTION
                + ") " +
                "values ( 'Junior Software Engineer', 'PT NUsantara', 'Jakarta', " +
                "'Lorem Ipsum has been the industry standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type " +
                "specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged')," +
                "('Marketing', 'PT Makmur', 'Bandung', 'It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently " +
                "with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum'), " +
                "('IT Support', 'PT Klingkit', 'Bogor', 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.')";


        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_JOBS_TABLE);
        db.execSQL(INSERT_DUMMY_JOB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;
        String DROP_JOBS_TABLE = "DROP TABLE IF EXISTS " + JOBS_TABLE;

        db.execSQL(DROP_USERS_TABLE);
        db.execSQL(DROP_JOBS_TABLE);
    }

    public void insertUser(String username, String password, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "insert into " + USER_TABLE + "(" + USERNAME + "," + EMAIL + "," + PASSWORD + "," + PHONENUMBER + ")"
                 + " values ( '" + username + "' , '" + email +"' , '" + password + "' , '" + phone +"' )" ;
        db.execSQL(query);
        db.close();
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + USER_TABLE + " where " + EMAIL + " = ?", new String[]{email});
        if(cursor.getCount()>0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public boolean checkPhone(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + USER_TABLE + " where " + PHONENUMBER + " = ?", new String[]{phone});
        if(cursor.getCount()>0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public boolean checkAccount(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + USER_TABLE + " where " + EMAIL + " = ? AND " + PASSWORD + " = ?", new String[]{email, password});
        if(cursor.getCount()>0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public User getUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        User user = null;

        Cursor cursor = db.rawQuery("select * from " + USER_TABLE + " where " + EMAIL + " = ? ", new String[]{email});
        if(cursor.moveToFirst()){
            user = new User(cursor.getInt(cursor.getColumnIndex(ID)), cursor.getString(cursor.getColumnIndex(USERNAME)), cursor.getString(cursor.getColumnIndex(EMAIL)),
                    cursor.getString(cursor.getColumnIndex(PASSWORD)), cursor.getString(cursor.getColumnIndex(PHONENUMBER)));
        }
        cursor.close();
        return user;
    }

    public ArrayList<Job> getJobs(){
        ArrayList<Job> jobs = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + JOBS_TABLE, new String[]{});
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(JOBID));
                String jobName = cursor.getString(cursor.getColumnIndex(JOBNAME));
                String desc = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
                String company = cursor.getString(cursor.getColumnIndex(COMPANY));
                String address = cursor.getString(cursor.getColumnIndex(ADDRESS));

                jobs.add(new Job(id, jobName,company,address,desc));
                cursor.moveToNext();
            }
        }
        return jobs;
    }

}
