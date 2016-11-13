package jkvillavo12col.com.co.databases.mannager;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

import jkvillavo12col.com.co.databases.dao.JuegoDaoSqlite;
import jkvillavo12col.com.co.databases.dao.UsuarioDaoSqlite;
import jkvillavo12col.com.co.databases.dao.VehiculoDaoSqlite;
import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Juego;
import jkvillavo12col.com.co.databases.entities.Usuario;
import jkvillavo12col.com.co.databases.entities.Vehiculo;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class VehiculoMannager {

   private static VehiculoMannager instance;

   /**
    * Obtiene la instancia unica de la clase
    * Creado el 28/10/16 a las 12:00 PM <br>
    *
    * @return instancia unica de la clase
    */
   public static VehiculoMannager getInstance () {

      if (instance == null) {
         instance = new VehiculoMannager();
      }
      return instance;
   }

   public static void destroyInstance () {

      instance = null;
   }

   public List<Vehiculo> getVehiculo (Context applicationContext) throws Exception {

      Vehiculo vehiculo = new Vehiculo();
      ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(applicationContext);
      List<Vehiculo> lista;
      try {
         conexionSQLite.openDatabase();
         conexionSQLite.getDbSqlite().beginTransactionNonExclusive();
         lista = VehiculoDaoSqlite.getInstance().getVehiculo(conexionSQLite);
         conexionSQLite.getDbSqlite().setTransactionSuccessful();
      } finally {
         conexionSQLite.getDbSqlite().endTransaction();
         conexionSQLite.closeDatabase();
      }

      return lista;
   }
   public void insertVehiculo (Context applicationContext, Vehiculo vehiculo) {

      ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(applicationContext);

      try {
         conexionSQLite.openDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(DBConstants.Vehiculo.IDUSUARIO, vehiculo.getId_usuario());
         contentValues.put(DBConstants.Vehiculo.PLACA, vehiculo.getPlacas());
         contentValues.put(DBConstants.Vehiculo.COLOR, vehiculo.getColor());
         contentValues.put(DBConstants.Vehiculo.ANIO, vehiculo.getAnio());
         contentValues.put(DBConstants.Vehiculo.MARCA, vehiculo.getMarca());


         VehiculoDaoSqlite.getInstance().insertVehiculo(conexionSQLite, contentValues);
      } finally {
         conexionSQLite.closeDatabase();
      }

   }



   public void updateVehiculoById (Context applicationContext, Vehiculo vehiculo) {
      ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(applicationContext);

      try {
         conexionSQLite.openDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(DBConstants.Vehiculo.IDUSUARIO, vehiculo.getId_usuario());
         contentValues.put(DBConstants.Vehiculo.PLACA, vehiculo.getPlacas());
         contentValues.put(DBConstants.Vehiculo.COLOR, vehiculo.getColor());
         contentValues.put(DBConstants.Vehiculo.ANIO, vehiculo.getAnio());
         contentValues.put(DBConstants.Vehiculo.MARCA, vehiculo.getMarca());

         VehiculoDaoSqlite.getInstance().updateVehiculo(conexionSQLite, vehiculo.getId(), contentValues);
      } finally {
         conexionSQLite.closeDatabase();
      }

   }





}
