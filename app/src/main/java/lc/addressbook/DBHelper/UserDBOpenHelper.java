package lc.addressbook.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by QA on 14-12-08.
 */
public class UserDBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "userId";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_LOCATION_CITY = "city";
    public static final String COLUMN_LOCATION_STATE_ = "state";
    public static final String COLUMN_LOCATION_STREET = "street";
    public static final String COLUMN_LOCATION_ZIP = "zip";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_FIRST = "first";
    public static final String COLUMN_NAME_LAST = "last";
    public static final String COLUMN_PICTURE_LARGE = "pictureLarge";
    public static final String COLUMN_PICTURE_MEDIUM = "pictureMedium";
    public static final String COLUMN_PICTURE_THUMBNAIL = "thumbnail";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELL = "cell";
    public static final String COLUMN_PHONE = "phone";



    private static final String TABLE_CREATE =
                    "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_LOCATION_CITY + " TEXT, " +
                    COLUMN_LOCATION_STATE_ + " TEXT, " +
                    COLUMN_LOCATION_STREET + " TEXT, " +
                    COLUMN_LOCATION_ZIP + " TEXT, " +
                    COLUMN_NAME_TITLE + " TEXT, " +
                    COLUMN_NAME_FIRST + " TEXT, " +
                    COLUMN_NAME_LAST + " TEXT, " +
                    COLUMN_PICTURE_LARGE + " TEXT, " +
                    COLUMN_PICTURE_MEDIUM + " TEXT, " +
                    COLUMN_PICTURE_THUMBNAIL + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_CELL + " TEXT, " +
                    COLUMN_PHONE + " TEXT " +
                    " )";



    public UserDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
        onCreate(sqlDB);
    }

}