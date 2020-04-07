package co.za.gmapssolutions.ekse;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseAdapter {
    //********SONG
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_SONG = "song";
    static final String KEY_FORMAT = "format";

    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "SheraDB";
    static final int DATABASE_VERSION = 1;
    public static final String TABLE_SONGS = "songs";
    static final String DATABASE_CREATE ="create table "+TABLE_SONGS+" (title TEXT not null," +
            "artist TEXT not null,song blob not null,format TEXT not null)";
    private  final Context context;
    private DatabaseHelper DBHelper;
    private static SQLiteDatabase db;

    public DataBaseAdapter(Context context){
        this.context = context;
        DBHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        //Context context;
        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            //this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try {
                db.execSQL(DATABASE_CREATE);
               // Toast.makeText(context,"Database created ",Toast.LENGTH_LONG).show();
            }catch (Exception e){
                e.printStackTrace();
                //Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            Log.w(TAG,"Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS );
            onCreate(db);
        }
    }
    //---opens the database---
    public DataBaseAdapter open() throws SQLException {
        //Toast.makeText(context,"Database Opened ",Toast.LENGTH_LONG).show();
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        db.close();
    }

    public void insertSong(String artist,String title,byte[] song,String format){
        try {
            String sql = "INSERT INTO "+ TABLE_SONGS + "(title,artist,song,format) VALUES("+ title +","+ artist +","+ song +","+ format + ");";
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
