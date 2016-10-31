package jkvillavo12col.com.co.databases.dao;

import android.content.ContentValues;
import android.database.Cursor;

import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Usuario;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class JuegoDaoSqlite {

   private static JuegoDaoSqlite instance;

   public static JuegoDaoSqlite getInstance () {

      if (instance == null) {
         instance = new JuegoDaoSqlite();
      }
      return instance;
   }

   public static void destroyInstance () {

      instance = null;
   }
   

   public long insertJuego (ConexionSQLite conexionSQLite, ContentValues contentValues) {

      return conexionSQLite.getDbSqlite().insertOrThrow(DBConstants.Juego.TABLE_JUEGOS, null, contentValues);

   }


   public void updateJuego (ConexionSQLite conexionSQLite, long idJuego, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idJuego)};
      conexionSQLite.getDbSqlite().update(DBConstants.Juego.TABLE_JUEGOS, contentValues, DBConstants.Usuario.ID + "=?", args);

   }

   public void deleteJuego (ConexionSQLite conexionSQLite, long idJuego, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idJuego)};
      conexionSQLite.getDbSqlite().delete(DBConstants.Juego.TABLE_JUEGOS,  DBConstants.Usuario.ID + "=?", args);

   }

}
