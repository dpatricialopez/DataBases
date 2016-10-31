package jkvillavo12col.com.co.databases.dao;

import android.content.ContentValues;
import android.database.Cursor;

import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Usuario;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class VehiculoDaoSqlite {

   private static VehiculoDaoSqlite instance;

   public static VehiculoDaoSqlite getInstance () {

      if (instance == null) {
         instance = new VehiculoDaoSqlite();
      }
      return instance;
   }

   public static void destroyInstance () {

      instance = null;
   }



   public long insertVehiculo (ConexionSQLite conexionSQLite, ContentValues contentValues) {

      return conexionSQLite.getDbSqlite().insertOrThrow(DBConstants.Vehiculo.TABLE_VEHICULOS, null, contentValues);

   }


   public void updateVehiculo (ConexionSQLite conexionSQLite, long idVehiculo, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idVehiculo)};
      conexionSQLite.getDbSqlite().update(DBConstants.Vehiculo.TABLE_VEHICULOS, contentValues, DBConstants.Vehiculo.ID + "=?", args);

   }

   public void deleteVehiculo (ConexionSQLite conexionSQLite, long idVehiculo, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idVehiculo)};
      conexionSQLite.getDbSqlite().delete(DBConstants.Vehiculo.TABLE_VEHICULOS,  DBConstants.Vehiculo.ID + "=?", args);

   }


}
