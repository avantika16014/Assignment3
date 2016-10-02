package com.example.admin.assignment3;

/**
 * Created by Admin on 9/29/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.provider.Settings;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="MyDatabase.db";
    public static final String TABLE_NAME="STUDENT_DETAILS";
    public static final String COLUMN_NAME="NAME";
    public static final String COLUMN_ROLL_NO="ROLL_NO";
    public static final String COLUMN_CUR_SEM="CUR_SEM";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME+" ("+COLUMN_ROLL_NO+" text primary key, "+COLUMN_NAME+" text, "+COLUMN_CUR_SEM+" text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertEntry(String roll_no, String name, String cur_sem)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ROLL_NO, roll_no);
                contentValues.put(COLUMN_NAME, name);
                contentValues.put(COLUMN_CUR_SEM, cur_sem);
                db.insert(TABLE_NAME, null, contentValues);
                return true;
        }
        catch (Exception e)
        {
        }
        return false;
    }

    public Cursor retrieveEntry(String roll_no)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TABLE_NAME+" where ROLL_NO= '"+roll_no+"'",null);
        return cursor;
    }

    public boolean updateEntry(String roll_no, String name, String cur_sem)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_ROLL_NO,roll_no);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_CUR_SEM,cur_sem);
        if(db.update(TABLE_NAME,contentValues,""+COLUMN_ROLL_NO+" = ? ",new String[]{roll_no})>0)
            return true;
        else
            return false;
    }

    public boolean deleteEntry(String roll_no)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if(db.delete(TABLE_NAME, "" + COLUMN_ROLL_NO + " = ? ", new String[]{roll_no})>0)
                return true;
            else
                return false;
        }
        catch(Exception e)
        {

        }
        return false;
    }
}
