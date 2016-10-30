package jkvillavo12col.com.co.databases.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import jkvillavo12col.com.co.databases.utils.AppConstants;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class DBHelper extends SQLiteOpenHelper {

   private Context context;


   public DBHelper (Context context) {

      super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
      this.context = context;
   }

   @Override
   public void onOpen (SQLiteDatabase db) {

      super.onOpen(db);
      if (!db.isReadOnly()) {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
         } else {
            db.execSQL("PRAGMA foreign_keys=ON");
         }
      }
   }

   @Override
   public void onCreate (SQLiteDatabase sqLiteDatabase) {

      try {

         sqLiteDatabase.execSQL(DBConstants.Usuario.getSQLCreate());

      } catch (Exception e) {
         Log.e(AppConstants.TAG, e.getMessage());
      }

   }

   @Override
   public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

   }
}
