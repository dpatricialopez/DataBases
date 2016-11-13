package jkvillavo12col.com.co.databases.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Juego;
import jkvillavo12col.com.co.databases.entities.Vehiculo;

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
   public List<Vehiculo> getVehiculo (ConexionSQLite conexionSQLite) {

      Cursor cursor = conexionSQLite.getDbSqlite().query(DBConstants.Vehiculo.TABLE_VEHICULOS, DBConstants.Vehiculo.obtenerCampos(),
              null, null, null, null, null);
      List<Vehiculo> lista=new ArrayList<>();
      Vehiculo vehiculo;
      if (cursor.moveToFirst()) {
         do {

            vehiculo = Vehiculo.obtenerVehiculoByCursor(cursor);
            lista.add(vehiculo);
         } while (cursor.moveToNext());

      }

      cursor.close();
      return lista;

   }

   public long deleteVehiculo (ConexionSQLite conexionSQLite, ContentValues contentValues, long idVehiculo) {
      String[] args = new String[]{String.valueOf(idVehiculo)};

      return conexionSQLite.getDbSqlite().delete(DBConstants.Vehiculo.TABLE_VEHICULOS, DBConstants.Juego.ID + "=?", args);

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
