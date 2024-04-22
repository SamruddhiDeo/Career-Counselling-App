package com.example.careercounsellor;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.careercounsellor.ModelClasses.LoginModel;
import com.example.careercounsellor.ModelClasses.SignupModel;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CareerCounsellorDB";
    private static final int DATABASE_VERSION = 1;
    //sign up variables
    private static final String TABLE_SIGNUP = "signup";
    private static final String SIGNUP_ID = "id";
    private static final String SIGNUP_NAME = "name";
    private static final String SIGNUP_EMAIL = "email";
    private static final String SIGNUP_PASSWORD = "password";
    //login variables
    private static final String TABLE_LOGIN = "login";
    private static final String LOGIN_ID = "id";
    private static final String LOGIN_NAME = "name";
    private static final String LOGIN_PASSWORD = "password";
    private static final String TABLE_USERFEEDBACK = "userfeedback";
    private static final String USERFEEDBACK_ID = "id";
    private static final String USERFEEDBACK_FEEDBACK = "feedback";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_SIGNUP +"(" + SIGNUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SIGNUP_NAME + " TEXT,"  + SIGNUP_EMAIL + " TEXT," + SIGNUP_PASSWORD + " TEXT" + ")");

        db.execSQL("CREATE TABLE "+ TABLE_LOGIN +"(" + LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LOGIN_NAME + " TEXT," + LOGIN_PASSWORD + " TEXT" + ")");

        db.execSQL("CREATE TABLE userfeedback (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    feedback TEXT\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SIGNUP);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOGIN);
        onCreate(db);
    }

    public void signupAdd(SignupModel signupModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SIGNUP_NAME,signupModel.getName());
        values.put(SIGNUP_EMAIL,signupModel.getEmail());
        values.put(SIGNUP_PASSWORD,signupModel.getPassword());

        db.insert(TABLE_SIGNUP,null,values);
    }

    public void feedbackAdd(String takenFeedback){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERFEEDBACK_FEEDBACK, takenFeedback);

        db.insert(TABLE_USERFEEDBACK,null,values);
    }

//    public boolean authenticateUser(LoginModel loginModel){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] projection = {SIGNUP_ID,SIGNUP_NAME,SIGNUP_EMAIL,SIGNUP_PASSWORD};
//        String selection = SIGNUP_NAME + " = ?";
//        String[] selectionArgs = {loginModel.getName()};
//        Cursor cursor = db.query(
//                TABLE_SIGNUP,
//                projection,   // The columns to return
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                  // don't group the rows
//                null,                  // don't filter by row groups
//                null                    // The sort order
//        );
//
//        // Compare entered data with stored data
//        boolean isAuthenticated = false;
//        if (cursor != null && cursor.moveToFirst()) {
//            String storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(SIGNUP_PASSWORD));
//            if (storedPassword.equals(loginModel.getPassword())) {
//                // Authentication successful
//                isAuthenticated = true;
//            }
//            cursor.close();
//        }
////        db.close();
//        return isAuthenticated;
//    }

    public boolean checkUserExists(LoginModel loginModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM signup WHERE name = ?",new String[]{loginModel.getName()});
        boolean exists = cursor.getCount() > 0;
//        cursor.close();
//        db.close();
        return exists;
    }

    public boolean authenticateUser(LoginModel loginModel,Context context){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM signup WHERE name = ? AND password = ?", new String[]{loginModel.getName(), loginModel.getPassword()});
        boolean isAuthenticated = cursor.getCount() > 0;
        if (isAuthenticated) {
            // Authentication successful, retrieve username and email
            cursor.moveToFirst();
            String authId = cursor.getString(cursor.getColumnIndexOrThrow(SIGNUP_ID));
            String authUsername = cursor.getString(cursor.getColumnIndexOrThrow(SIGNUP_NAME));
            String authEmail = cursor.getString(cursor.getColumnIndexOrThrow(SIGNUP_EMAIL));
            // Store user info in SharedPreferences
            SharedPreferences.Editor editor = context.getSharedPreferences("user_session", Context.MODE_PRIVATE).edit();
            editor.putString("id", authId);
            editor.putString("name", authUsername);
            editor.putString("email", authEmail);
            editor.putBoolean("is_logged_in", true);
            editor.apply();
        }
//        cursor.close();
//        db.close();
        return isAuthenticated;
    }

//    public String getUserId(String name, String password){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String[] projection = {SIGNUP_ID};
//        String selection = SIGNUP_NAME + " = ? AND " + SIGNUP_PASSWORD + " = ?";
//        String[] selectionArgs = {name, password};
//
//        Cursor cursor = db.query(
//                TABLE_SIGNUP,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//
//        String userId = null;
//        if (cursor != null && cursor.moveToFirst()) {
//            userId = cursor.getString(cursor.getColumnIndexOrThrow(SIGNUP_ID));
//            cursor.close();
//        }
////        db.close();
//        return userId;
//    }


}
