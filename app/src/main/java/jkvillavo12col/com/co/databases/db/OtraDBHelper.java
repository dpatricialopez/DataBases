package jkvillavo12col.com.co.databases.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JkVillavo12Col on 29/10/16.
 */

public class OtraDBHelper extends SQLiteOpenHelper {

   private Context context;


   public OtraDBHelper (Context context) {

      super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
      this.context = context;
   }

   @Override
   public void onCreate (SQLiteDatabase sqLiteDatabase) {

      sqLiteDatabase.execSQL(DBConstants.Usuario.getSQLCreate());

   }

   @Override
   public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

   }
}
