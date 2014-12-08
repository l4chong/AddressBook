package lc.addressbook.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import lc.addressbook.Models.Result;
import lc.addressbook.Models.Results;

/**
 * Created by QA on 14-12-08.
 */
public class UserDataSource {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    public UserDataSource(Context context){
        dbHelper = new UserDBOpenHelper(context);
    }


    public void populateDB(Results results) {
         List<Result> mResults = results.getResults();
        String sql = "INSERT INTO "+ UserDBOpenHelper.TABLE_USERS +" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        SQLiteStatement statement = database.compileStatement(sql);

        database.beginTransaction();
        try {
            for(Result result : mResults){
                statement.clearBindings();
                statement.bindString(2, bean.getName());
                statement.bindString(3, bean.getName());
                statement.bindString(4, bean.getName());
                statement.bindString(5, bean.getName());
                statement.bindString(6, bean.getName());
                statement.bindString(7, bean.getName());
                statement.bindString(8, bean.getName());
                statement.bindString(9, bean.getName());
                statement.bindString(10, bean.getName());
                statement.bindString(11, bean.getName());
                statement.bindString(12, bean.getName());
                statement.bindString(13, bean.getName());
                statement.bindString(14, bean.getName());
                statement.bindString(15, bean.getName());
                statement.bindString(16, bean.getName());
                // rest of bindings


                statement.execute(); //or executeInsert() if id is needed
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public boolean isUserTableEmpty() {
        String count = "SELECT count(*) FROM " + UserDBOpenHelper.TABLE_USERS;
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount == 0) {
            return true;
        }
        return false;
    }


    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

}
