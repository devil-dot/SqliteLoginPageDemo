package com.kmtstudio.mysqlitedatabasedemo10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDetails";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USER_NAME = "Username";
    private static final String PASSWORD = "Password";
    private static final int VERSION_CODE = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) NOT NULL, " + EMAIL + " TEXT NOT NULL, " + USER_NAME + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_CODE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            Toast.makeText(context, "onCreate is called", Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {

            Toast.makeText(context,"Exception " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        } catch (Exception e) {

            Toast.makeText(context, "Exception " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(UserDetails userDetails) {

        SQLiteDatabase liteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, userDetails.getName());
        values.put(EMAIL, userDetails.getEmail());
        values.put(USER_NAME, userDetails.getUsername());
        values.put(PASSWORD, userDetails.getPassword());

        return liteDatabase.insert(TABLE_NAME, null, values);

    }

    public boolean getUserDetails(String uname, String pass) {

        SQLiteDatabase liteDatabase = this.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery(SELECT_ALL, null);

        boolean result = false;

        if (cursor.getCount() == 0) {

            Toast.makeText(context, "Sorry no data no found!!!", Toast.LENGTH_SHORT).show();

        } else {

            while (cursor.moveToNext()) {

                String username = cursor.getString(3);
                String password = cursor.getString(4);


                if (username.equals(uname) && password.equals(pass)) {

                    result = true;
                    break;

                }
            }
        }

        cursor.close();
        return result;
    }
}
