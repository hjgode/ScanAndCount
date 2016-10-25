package hsm.demo.scanandcount;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//TODO change date field from string to INT (LONG)
//DONE
/*
//Building the table includes:
StringBuilder query=new StringBuilder();
query.append("CREATE TABLE "+TABLE_NAME+ " (");
query.append(COLUMN_ID+"int primary key autoincrement,");
query.append(COLUMN_DATETIME+" int)");

//And inserting the data includes this:
values.put(COLUMN_DATETIME, System.currentTimeMillis());
 */
/**
 * Created by hgode on 10/23/16.
 */
public class DataReaderDBHelper extends SQLiteOpenHelper{
    private static final String TAG="DataReaderDBHelper: ";

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "DatenInfo.sql";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataReaderContract.DataEntry.TABLE_NAME + " (" +
                    DataReaderContract.DataEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    DataReaderContract.DataEntry.COLUMN_NAME_DATA + TEXT_TYPE + COMMA_SEP +
                    DataReaderContract.DataEntry.COLUMN_NAME_QUANTITY + INT_TYPE + COMMA_SEP +
                    DataReaderContract.DataEntry.COLUMN_NAME_DATE + INT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataReaderContract.DataEntry.TABLE_NAME;

    public DataReaderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        //db=SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME, null);
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
        // Drop older table if existed
        db.execSQL(SQL_DELETE_ENTRIES);
        // Creating tables again
        onCreate(db);
    }

    public void clearData(DataReaderDBHelper mDbHelper){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        // Creating tables again
        onCreate(db);
    }
    public static void saveData(DataReaderDBHelper mDbHelper, item myItem){
        Log.d(TAG, "saveData "+ myItem.toString());

        //save the data
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(DataReaderContract.DataEntry.COLUMN_NAME_DATA , myItem.getData());
        values.put(DataReaderContract.DataEntry.COLUMN_NAME_QUANTITY, myItem.getQuantity());
        values.put(DataReaderContract.DataEntry.COLUMN_NAME_DATE, myItem.getTimestamp());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DataReaderContract.DataEntry.TABLE_NAME, null, values);

    }

    public static List<item> readData(DataReaderDBHelper mDbHelper){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<item> myItems=new ArrayList<item>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
//        String[] projection = {
//                DataReaderContract.DataEntry.COLUMN_NAME_ID,
//                DataReaderContract.DataEntry.COLUMN_NAME_DATA,
//                DataReaderContract.DataEntry.COLUMN_NAME_QUANTITY,
//                DataReaderContract.DataEntry.COLUMN_NAME_DATE
//        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DataReaderContract.DataEntry.COLUMN_NAME_DATA + " = ?";
//        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DataReaderContract.DataEntry.COLUMN_NAME_QUANTITY + " DESC";

        Cursor c=db.rawQuery("SELECT "+
                DataReaderContract.DataEntry.COLUMN_NAME_DATA+", "+
                DataReaderContract.DataEntry.COLUMN_NAME_QUANTITY+", "+
                DataReaderContract.DataEntry.COLUMN_NAME_DATE +
                " FROM " + DataReaderContract.DataEntry.TABLE_NAME,
                null);
//        Cursor c = db.query(
//                DataReaderContract.DataEntry.TABLE_NAME,                     // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );

        if(c!=null && c.moveToFirst()){
            do{
//                java.util.Date d = getDate(c.getString(2));
                //add new item from sqlite data row
//                myItems.add(new item(c.getString(0), c.getInt(1), getDate(c.getString(2))));
                myItems.add(new item(c.getString(0), c.getInt(1), c.getLong(2)));
            }while (c.moveToNext());
        }
        c.close();
        return myItems;
    }

    public static String getDateString(long timestamp){
        SimpleDateFormat databaseDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDateAndTime = databaseDateTimeFormat.format(timestamp);     //2009-06-30 08:29:36
        return strDateAndTime;
    }

}
