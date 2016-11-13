package jkvillavo12col.com.co.databases.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Juego;
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

      public List<Juego> getJuegos (ConexionSQLite conexionSQLite) {

         Cursor cursor = conexionSQLite.getDbSqlite().query(DBConstants.Juego.TABLE_JUEGOS, DBConstants.Juego.obtenerCampos(),
                 null, null, null, null, null);
         List<Juego> lista=new ArrayList<>();
         Juego juego =new Juego();
         if (cursor.moveToFirst()) {
            do {

               juego = Juego.obtenerJuegoByCursor(cursor);
               lista.add(juego);
            } while (cursor.moveToNext());

         }

         cursor.close();
         return lista;

      }

   public long deleteJuego (ConexionSQLite conexionSQLite, ContentValues contentValues, long idJuego) {
      String[] args = new String[]{String.valueOf(idJuego)};

      return conexionSQLite.getDbSqlite().delete(DBConstants.Juego.TABLE_JUEGOS, DBConstants.Juego.ID + "=?", args);

   }
   public void updateJuego (ConexionSQLite conexionSQLite, long idJuego, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idJuego)};
      conexionSQLite.getDbSqlite().update(DBConstants.Juego.TABLE_JUEGOS, contentValues, DBConstants.Juego.ID + "=?", args);

   }

   public void deleteJuego (ConexionSQLite conexionSQLite, long idJuego, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idJuego)};
      conexionSQLite.getDbSqlite().delete(DBConstants.Juego.TABLE_JUEGOS,  DBConstants.Juego.ID + "=?", args);

   }

}
