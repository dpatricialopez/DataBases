package jkvillavo12col.com.co.databases.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * clase encargada de interactuar con la base de datos , maneja las consultas actualizaciones y eliminaciones de datos <br>
 * Creado el 15/10/2014 a las 14:16:23 <br>
 *
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
public class ConexionSQLite {

   protected Context context;
   protected SQLiteDatabase dbSqlite;
   private final DBHelper dbOpenHelper;

   private AtomicInteger mOpenCounter = new AtomicInteger();
   private static ConexionSQLite instance;

   private ConexionSQLite (Context context) {

      this.dbOpenHelper = new DBHelper(context);
      this.context = context;
      establishDb();
   }

   /**
    * Establece la base de datos
    */
   private void establishDb () {

      if (this.dbSqlite == null) {
         this.dbSqlite = this.dbOpenHelper.getWritableDatabase();
      }
   }

   /**
    * limpia la referencia a la base de datos
    */
   public SQLiteDatabase getDbSqlite () {

      if (dbSqlite == null) {
         establishDb();
      } else if (!dbSqlite.isOpen()) {
         dbSqlite = this.dbOpenHelper.getWritableDatabase();
      }
      return dbSqlite;
   }

   public DBHelper getDBHelper () {

      return dbOpenHelper;
   }

   public static synchronized ConexionSQLite getInstance (Context context) {

      if (instance == null) {
         instance = new ConexionSQLite(context);
      }
      return instance;
   }

   /**
    * Se valida si existe alguna conexion activa, si lo hay esta es retornada y sumamos el contador de conectados, si no lo hay, la creamos y la retornamos
    *
    * @return
    */
   public synchronized SQLiteDatabase openDatabase () {

      if (mOpenCounter.incrementAndGet() < 1) {
         // Opening new database
         dbSqlite = dbOpenHelper.getWritableDatabase();
      }

      return dbSqlite;
   }

   /**
    * Se valida en este metodo es la unica conexión abierta y si lo es, se cierra la base de daos
    */
   public synchronized void closeDatabase () {

      if (mOpenCounter.decrementAndGet() < 1) {
         // Closing database
         dbSqlite.close();
      }
        /*if(mOpenCounter.get()<0)
            throw new IllegalStateException();*/
   }

}