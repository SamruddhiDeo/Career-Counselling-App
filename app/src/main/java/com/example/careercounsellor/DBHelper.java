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

import com.example.careercounsellor.ModelClasses.CounsellorModel;
import com.example.careercounsellor.ModelClasses.LoginModel;
import com.example.careercounsellor.ModelClasses.SignupModel;
import com.example.careercounsellor.ModelClasses.TestResultModel;

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
    //feedback variables
    private static final String TABLE_USERFEEDBACK = "userfeedback";
    private static final String USERFEEDBACK_ID = "id";
    private static final String USERFEEDBACK_FEEDBACK = "feedback";

    //aptitude test variables
    private static final String TABLE_APTITUDE_RESULTS = "aptitude";
    private static final String APTITUDE_COLUMN1 = "col1";
    private static final String APTITUDE_COLUMN2 = "col2";
    private static final String APTITUDE_COLUMN3 = "col3";
    private static final String APTITUDE_COLUMN4 = "col4";
    private static final String APTITUDE_PERCENT_COLUMN1 = "percentcol1";
    private static final String APTITUDE_PERCENT_COLUMN2 = "percentcol2";
    private static final String APTITUDE_PERCENT_COLUMN3 = "percentcol3";
    private static final String APTITUDE_PERCENT_COLUMN4 = "percentcol4";
    private static final String APTITUDE_SUGGESTION = "suggestion";

    //personality test variables
    private static final String TABLE_PERSONALITY_RESULTS = "personality";
    private static final String PERSONALITY_COLUMN1 = "col1";
    private static final String PERSONALITY_COLUMN2 = "col2";
    private static final String PERSONALITY_COLUMN3 = "col3";
    private static final String PERSONALITY_COLUMN4 = "col4";
    private static final String PERSONALITY_PERCENT_COLUMN1 = "percentcol1";
    private static final String PERSONALITY_PERCENT_COLUMN2 = "percentcol2";
    private static final String PERSONALITY_PERCENT_COLUMN3 = "percentcol3";
    private static final String PERSONALITY_PERCENT_COLUMN4 = "percentcol4";
    private static final String PERSONALITY_SUGGESTION = "suggestion";
    //counsellor variables
    private static final String TABLE_COUNSELLOR = "counsellor";
    private static final String COUNSELLOR_IMG = "img";
    private static final String COUNSELLOR_NAME = "name";
    private static final String COUNSELLOR_RATING = "rating";
    private static final String COUNSELLOR_PRICE = "price";
    private static final String COUNSELLOR_TIME = "time";
    private static final String COUNSELLOR_DATE = "date";
    private static final String COUNSELLOR_TYPE_OF_CALL = "typeofcall";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_SIGNUP +"(" + SIGNUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SIGNUP_NAME + " TEXT,"  + SIGNUP_EMAIL + " TEXT," + SIGNUP_PASSWORD + " TEXT" + ")");

        db.execSQL("CREATE TABLE "+ TABLE_LOGIN +"(" + LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LOGIN_NAME + " TEXT," + LOGIN_PASSWORD + " TEXT" + ")");

        db.execSQL("CREATE TABLE "+TABLE_APTITUDE_RESULTS+" ("+APTITUDE_COLUMN1+" TEXT, "+APTITUDE_COLUMN2+" TEXT, "+APTITUDE_COLUMN3+" TEXT, "+APTITUDE_COLUMN4+" TEXT, "+APTITUDE_PERCENT_COLUMN1+" REAL, "+APTITUDE_PERCENT_COLUMN2+" REAL, "+APTITUDE_PERCENT_COLUMN3+" REAL, "+APTITUDE_PERCENT_COLUMN4+" REAL, "+APTITUDE_SUGGESTION+" TEXT )");

        db.execSQL("CREATE TABLE "+TABLE_PERSONALITY_RESULTS+" ("+PERSONALITY_COLUMN1+" TEXT, "+PERSONALITY_COLUMN2+" TEXT, "+PERSONALITY_COLUMN3+" TEXT, "+PERSONALITY_COLUMN4+" TEXT, "+PERSONALITY_PERCENT_COLUMN1+" REAL, "+PERSONALITY_PERCENT_COLUMN2+" REAL, "+PERSONALITY_PERCENT_COLUMN3+" REAL, "+PERSONALITY_PERCENT_COLUMN4+" REAL, "+PERSONALITY_SUGGESTION+" TEXT )");


        db.execSQL("CREATE TABLE userfeedback (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    feedback TEXT\n" +
                ")");

        db.execSQL("CREATE TABLE "+TABLE_COUNSELLOR+" ("+COUNSELLOR_IMG+" INTEGER, "+COUNSELLOR_NAME+" TEXT, "+COUNSELLOR_RATING+" TEXT, "+COUNSELLOR_PRICE+" TEXT, "+COUNSELLOR_DATE+" TEXT, "+COUNSELLOR_TIME+" TEXT, "+COUNSELLOR_TYPE_OF_CALL+" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SIGNUP);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_APTITUDE_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PERSONALITY_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COUNSELLOR);
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

    public void counsellorAdd(CounsellorModel counsellorModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNSELLOR_IMG, counsellorModel.getImg());
        values.put(COUNSELLOR_NAME, counsellorModel.getName());
        values.put(COUNSELLOR_RATING, counsellorModel.getRating());
        values.put(COUNSELLOR_PRICE, counsellorModel.getPrice());
        values.put(COUNSELLOR_DATE, counsellorModel.getDate());
        values.put(COUNSELLOR_TIME, counsellorModel.getTime());
        values.put(COUNSELLOR_TYPE_OF_CALL, counsellorModel.getTypeOfCall());

        db.insert(TABLE_COUNSELLOR,null,values);
    }

    public CounsellorModel getLatestCounsellor() {
        SQLiteDatabase db = this.getReadableDatabase();
        CounsellorModel counsellorModel = new CounsellorModel();

        String query = "SELECT * FROM " + TABLE_COUNSELLOR + " ORDER BY ROWID DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        // Move cursor to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve column values and store in TestResultModel
            counsellorModel.setImg(cursor.getInt(cursor.getColumnIndexOrThrow(COUNSELLOR_IMG)));
            counsellorModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(SIGNUP_NAME)));
            counsellorModel.setRating(cursor.getString(cursor.getColumnIndexOrThrow(COUNSELLOR_RATING)));
            counsellorModel.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COUNSELLOR_PRICE)));
            counsellorModel.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COUNSELLOR_DATE)));
            counsellorModel.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COUNSELLOR_TIME)));
            counsellorModel.setTypeOfCall(cursor.getString(cursor.getColumnIndexOrThrow(COUNSELLOR_TYPE_OF_CALL)));
        } else {
            return null;
        }

        return counsellorModel;

        }

    public void aptitudeResultAdd (TestResultModel testResultModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(APTITUDE_COLUMN1,testResultModel.getCol1());
        values.put(APTITUDE_COLUMN2,testResultModel.getCol2());
        values.put(APTITUDE_COLUMN3,testResultModel.getCol3());
        values.put(APTITUDE_COLUMN4,testResultModel.getCol4());
        values.put(APTITUDE_PERCENT_COLUMN1,testResultModel.getPercentCol1());
        values.put(APTITUDE_PERCENT_COLUMN2,testResultModel.getPercentCol2());
        values.put(APTITUDE_PERCENT_COLUMN3,testResultModel.getPercentCol3());
        values.put(APTITUDE_PERCENT_COLUMN4,testResultModel.getPercentCol4());
        values.put(APTITUDE_SUGGESTION,testResultModel.getSuggestion());

        db.insert(TABLE_APTITUDE_RESULTS,null,values);
    }

    public void personalityResultAdd (TestResultModel testResultModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PERSONALITY_COLUMN1,testResultModel.getCol1());
        values.put(PERSONALITY_COLUMN2,testResultModel.getCol2());
        values.put(PERSONALITY_COLUMN3,testResultModel.getCol3());
        values.put(PERSONALITY_COLUMN4,testResultModel.getCol4());
        values.put(PERSONALITY_PERCENT_COLUMN1,testResultModel.getPercentCol1());
        values.put(PERSONALITY_PERCENT_COLUMN2,testResultModel.getPercentCol2());
        values.put(PERSONALITY_PERCENT_COLUMN3,testResultModel.getPercentCol3());
        values.put(PERSONALITY_PERCENT_COLUMN4,testResultModel.getPercentCol4());
        values.put(PERSONALITY_SUGGESTION,testResultModel.getSuggestion());

        db.insert(TABLE_PERSONALITY_RESULTS,null,values);
    }

    public TestResultModel getLatestAptitudeResult() {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModel testResult = new TestResultModel();

        // Select the latest row from the aptitude table
        String query = "SELECT * FROM " + TABLE_APTITUDE_RESULTS + " ORDER BY ROWID DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        // Move cursor to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve column values and store in TestResultModel
            testResult.setCol1(cursor.getString(cursor.getColumnIndexOrThrow(APTITUDE_COLUMN1)));
            testResult.setCol2(cursor.getString(cursor.getColumnIndexOrThrow(APTITUDE_COLUMN2)));
            testResult.setCol3(cursor.getString(cursor.getColumnIndexOrThrow(APTITUDE_COLUMN3)));
            testResult.setCol4(cursor.getString(cursor.getColumnIndexOrThrow(APTITUDE_COLUMN4)));
            testResult.setPercentCol1(cursor.getDouble(cursor.getColumnIndexOrThrow(APTITUDE_PERCENT_COLUMN1)));
            testResult.setPercentCol2(cursor.getDouble(cursor.getColumnIndexOrThrow(APTITUDE_PERCENT_COLUMN2)));
            testResult.setPercentCol3(cursor.getDouble(cursor.getColumnIndexOrThrow(APTITUDE_PERCENT_COLUMN3)));
            testResult.setPercentCol4(cursor.getDouble(cursor.getColumnIndexOrThrow(APTITUDE_PERCENT_COLUMN4)));
            testResult.setSuggestion(cursor.getString(cursor.getColumnIndexOrThrow(APTITUDE_SUGGESTION)));
        } else {
            return null;
        }

        return testResult;
    }

    public TestResultModel getLatestPersonalityResult() {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModel testResult = new TestResultModel();

        // Select the latest row from the aptitude table
        String query = "SELECT * FROM " + TABLE_PERSONALITY_RESULTS + " ORDER BY ROWID DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        // Move cursor to the first row
        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve column values and store in TestResultModel
            testResult.setCol1(cursor.getString(cursor.getColumnIndexOrThrow(PERSONALITY_COLUMN1)));
            testResult.setCol2(cursor.getString(cursor.getColumnIndexOrThrow(PERSONALITY_COLUMN2)));
            testResult.setCol3(cursor.getString(cursor.getColumnIndexOrThrow(PERSONALITY_COLUMN3)));
            testResult.setCol4(cursor.getString(cursor.getColumnIndexOrThrow(PERSONALITY_COLUMN4)));
            testResult.setPercentCol1(cursor.getDouble(cursor.getColumnIndexOrThrow(PERSONALITY_PERCENT_COLUMN1)));
            testResult.setPercentCol2(cursor.getDouble(cursor.getColumnIndexOrThrow(PERSONALITY_PERCENT_COLUMN2)));
            testResult.setPercentCol3(cursor.getDouble(cursor.getColumnIndexOrThrow(PERSONALITY_PERCENT_COLUMN3)));
            testResult.setPercentCol4(cursor.getDouble(cursor.getColumnIndexOrThrow(PERSONALITY_PERCENT_COLUMN4)));
            testResult.setSuggestion(cursor.getString(cursor.getColumnIndexOrThrow(PERSONALITY_SUGGESTION)));
        } else {
            return null;
        }

        return testResult;
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
