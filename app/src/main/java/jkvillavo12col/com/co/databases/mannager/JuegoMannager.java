package jkvillavo12col.com.co.databases.mannager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import jkvillavo12col.com.co.databases.dao.JuegoDaoSqlite;
import jkvillavo12col.com.co.databases.dao.UsuarioDaoSqlite;
import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Usuario;
import jkvillavo12col.com.co.databases.entities.Vehiculo;
import jkvillavo12col.com.co.databases.entities.Juego;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class JuegoMannager {

   private static JuegoMannager instance;

   /**
    * Obtiene la instancia unica de la clase
    * Creado el 28/10/16 a las 12:00 PM <br>
    *
    * @return instancia unica de la clase
    */
   public static JuegoMannager getInstance () {

      if (instance == null) {
         instance = new JuegoMannager();
      }
      return instance;
   }

   public static void destroyInstance () {

      instance = null;
   }

   public List<Juego> getJuegos (Context applicationContext) throws Exception {

      Juego juego = new Juego();
      ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(applicationContext);
      List<Juego> lista;
      try {
         conexionSQLite.openDatabase();
         conexionSQLite.getDbSqlite().beginTransactionNonExclusive();
         lista = JuegoDaoSqlite.getInstance().getJuegos(conexionSQLite);
         conexionSQLite.getDbSqlite().setTransactionSuccessful();
      } finally {
         conexionSQLite.getDbSqlite().endTransaction();
         conexionSQLite.closeDatabase();
      }

      return lista;
   }


   public void insertJuego (Context applicationContext, Juego juego) {

      ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(applicationContext);

      try {
         conexionSQLite.openDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(DBConstants.Juego.NOMBRES, juego.getNombres());
         contentValues.put(DBConstants.Juego.CONSOLA, juego.getConsola());
         contentValues.put(DBConstants.Juego.ID, juego.getId());
         contentValues.put(DBConstants.Juego.IDUSUARIO, juego.getId_usuario());
         contentValues.put(DBConstants.Juego.CALIFICACION, juego.getCalificacion());

         UsuarioDaoSqlite.getInstance().insertUsuario(conexionSQLite, contentValues);
      } finally {
         conexionSQLite.closeDatabase();
      }

   }



   private void updateJuego (Context applicationContext, Juego juego) {

      ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(applicationContext);

      try {
         conexionSQLite.openDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(DBConstants.Juego.NOMBRES, juego.getNombres());
         contentValues.put(DBConstants.Juego.CONSOLA, juego.getConsola());
         contentValues.put(DBConstants.Juego.ID, juego.getId());
         contentValues.put(DBConstants.Juego.IDUSUARIO, juego.getId_usuario());
         contentValues.put(DBConstants.Juego.CALIFICACION, juego.getCalificacion());

         JuegoDaoSqlite.getInstance().updateJuego(conexionSQLite, juego.getId(), contentValues);
      } finally {
         conexionSQLite.closeDatabase();
      }

   }







}
