package com.example.mohit.ticketbookingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.net.ContentHandlerFactory;

public class MyDBHandler extends SQLiteOpenHelper {
public static final int DB_Version=1;
    public static final String DB_Name="TicketBooking.db";
    public static final String User_Table="UserInfo";
    public static final String User_UserName = "name";
    public static final String User_Password = "password";
    public static final String BookingDetails_Table="BookingDetails";
    public static final String BookingDetails_UserName="UserName";
    public static final String BookingDetails_FromLocation="FromLocation";
    public static final String BookingDetails_ToLocation="ToLocation";
    public static final String BookingDetails_TravellingDate="Traveldate";
    private static final String TAG = "MyActivity----->";

    public  SQLiteDatabase db;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_Name, null, DB_Version);
        SQLiteDatabase db=this.getWritableDatabase();
    }

   /* public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
*/


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query1="Create Table "+User_Table+" ("+User_UserName+" Text,"+User_Password+" Text);";
        String Query2="Create Table "+BookingDetails_Table+" ("+BookingDetails_UserName+" Text,"+BookingDetails_FromLocation+" Text,"+BookingDetails_ToLocation+" Text,"+BookingDetails_TravellingDate+" Text);";
        db.execSQL(Query1);
        db.execSQL(Query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF EXISTS " + User_Table);
        db.execSQL("DROP IF EXISTS " + BookingDetails_Table);
    }

    public boolean Register(Context context, String username,String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(User_UserName, username);
        newValues.put(User_Password, password);
        long result=db.insert(User_Table, null, newValues);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean TicketBooking(String username,String fromLocation,String toLocation,String travellDate)
    {
        Log.d(TAG, "-------------------In Ticket Booking Area--------------");
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("UserName",username);
        values.put("FromLocation",fromLocation);
        values.put("ToLocation",toLocation);
        values.put("Traveldate",travellDate);
        long result=db.insert(BookingDetails_Table,null,values);
        db.close();
        if(result==-1)
        {
            return false;
        }else {
            return true;
        }
    }

    public String getFromDb(String userName){
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d(TAG, "-------------------In Password get Area--------------");
        Log.d(TAG, "-------------------In Password get Area UserName----" + userName);
        String password = null;
        try {
            Cursor cursor = db.query(User_Table, null, " name=?", new String[]{userName}, null, null, null);
            if (cursor.getCount() < 1)
            {
                cursor.close();
                return "NOT EXIST";
            }
            cursor.moveToFirst();
            password = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return password;
    }

    public Cursor getAllData()
    {

            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor  res = sqLiteDatabase.rawQuery("SELECT * FROM " + BookingDetails_Table, null);
            return res;


    }
}
