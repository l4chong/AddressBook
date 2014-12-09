package lc.addressbook.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import lc.addressbook.Models.Location;
import lc.addressbook.Models.Result;
import lc.addressbook.Models.Results;
import lc.addressbook.Models.User;
import lc.addressbook.Models.Picture;
import lc.addressbook.Models.Name;

/**
 * Created by QA on 14-12-08.
 */
public class UserDataSource {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;
    private static final String[] allColumns = {
            UserDBOpenHelper.COLUMN_USERNAME,
            UserDBOpenHelper.COLUMN_LOCATION_CITY,
            UserDBOpenHelper.COLUMN_LOCATION_STATE,
            UserDBOpenHelper.COLUMN_LOCATION_STREET,
            UserDBOpenHelper.COLUMN_LOCATION_ZIP,
            UserDBOpenHelper.COLUMN_NAME_TITLE,
            UserDBOpenHelper.COLUMN_NAME_FIRST,
            UserDBOpenHelper.COLUMN_NAME_LAST,
            UserDBOpenHelper.COLUMN_PICTURE_LARGE,
            UserDBOpenHelper.COLUMN_PICTURE_MEDIUM,
            UserDBOpenHelper.COLUMN_PICTURE_THUMBNAIL,
            UserDBOpenHelper.COLUMN_GENDER,
            UserDBOpenHelper.COLUMN_EMAIL,
            UserDBOpenHelper.COLUMN_CELL,
            UserDBOpenHelper.COLUMN_PHONE
            };

    public UserDataSource(Context context){
        dbHelper = new UserDBOpenHelper(context);
    }

    public UserDataSource(){

    }

    public void populateDB(Results results) {
         List<Result> mResults = results.getResults();
        String sql = "INSERT INTO "+ UserDBOpenHelper.TABLE_USERS +" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        SQLiteStatement statement = database.compileStatement(sql);

        database.beginTransaction();
        try {
            for(Result result : mResults){
                statement.clearBindings();

                statement.bindString(1, result.getUser().getUsername());
                statement.bindString(2, result.getUser().getLocation().getCity());
                statement.bindString(3, result.getUser().getLocation().getState());
                statement.bindString(4, result.getUser().getLocation().getStreet());
                statement.bindString(5, result.getUser().getLocation().getZip());
                statement.bindString(6, result.getUser().getName().getTitle());
                statement.bindString(7, result.getUser().getName().getFirst());
                statement.bindString(8, result.getUser().getName().getLast());
                statement.bindString(9, result.getUser().getPicture().getLarge());
                statement.bindString(10, result.getUser().getPicture().getMedium());
                statement.bindString(11, result.getUser().getPicture().getThumbnail());
                statement.bindString(12, result.getUser().getGender());
                statement.bindString(13, result.getUser().getEmail());
                statement.bindString(14, result.getUser().getCell());
                statement.bindString(15, result.getUser().getPhone());
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

    public List<Result> getSortedUsers() {

        Cursor cursor = database.query(UserDBOpenHelper.TABLE_USERS, allColumns,
                null, null, null, null, UserDBOpenHelper.COLUMN_NAME_FIRST);

        //Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        List<Result> results = cursorToList(cursor);
        return results;
    }

    private List<Result> cursorToList(Cursor cursor) {
        List<Result> results = new ArrayList<Result>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Result result = new Result();
                User user = new User();
                Location location = new Location();
                Picture picture = new Picture();
                Name name = new Name();

                result.setUser(user);
                user.setLocation(location);
                user.setName(name);
                user.setPicture(picture);

                user.setUsername(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_USERNAME)));
                user.setGender(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_GENDER)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_EMAIL)));
                user.setCell(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_CELL)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_PHONE)));

                location.setCity(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_LOCATION_CITY)));
                location.setState(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_LOCATION_STATE)));
                location.setStreet(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_LOCATION_STREET)));
                location.setZip(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_LOCATION_ZIP)));


                name.setTitle(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_NAME_TITLE)));
                name.setFirst(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_NAME_FIRST)));
                name.setLast(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_NAME_LAST)));


                picture.setLarge(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_PICTURE_LARGE)));
                picture.setMedium(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_PICTURE_MEDIUM)));
                picture.setThumbnail(cursor.getString(cursor.getColumnIndex(UserDBOpenHelper.COLUMN_PICTURE_THUMBNAIL)));


                results.add(result);
            }
        }
        return results;
    }


    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void read(){
        database = dbHelper.getReadableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

}
