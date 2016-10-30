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

   /**
    * Obtiene la instancia unica de la clase
    * Creado el 28/10/16 a las 12:02 PM <br>
    *
    * @return instancia unica de la clase
    */
   public static JuegoDaoSqlite getInstance () {

      if (instance == null) {
         instance = new JuegoDaoSqlite();
      }
      return instance;
   }

   public static void destroyInstance () {

      instance = null;
   }
   

   /**
    * Consulta un usuario por medio de su id unico, si lo encuentra construye un objeto y devuelve toda la informacion
    *
    * @param conexionSQLite conexion a la database
    * @param idUsuario      identificador unico del usuario
    *
    * @return objeto con los datos del usuario
    */
   public Usuario findUsuarioById (ConexionSQLite conexionSQLite, long idUsuario) throws Exception {

      Usuario usuarioLogueadoDTO = null;
      String[] args = new String[]{String.valueOf(idUsuario)};

      Cursor cursor = conexionSQLite.getDbSqlite().query(DBConstants.Usuario.TABLE_USUARIO, DBConstants.Usuario.obtenerCampos(),
              DBConstants.Usuario.ID + "=?", args,
              null, null, null);

      if (cursor.moveToFirst()) {
         usuarioLogueadoDTO = Usuario.obtenerUsuarioByCursor(cursor);
      }
      return usuarioLogueadoDTO;
   }

   public Usuario findUsuarioByUserPass (ConexionSQLite conexionSQLite, String usuario, String contrasenia) {

      Usuario usuarioLogueadoDTO = null;
      String[] args = new String[]{usuario, contrasenia};

      Cursor cursor = conexionSQLite.getDbSqlite().query(
              DBConstants.Usuario.TABLE_USUARIO,
              DBConstants.Usuario.obtenerCampos(),
              DBConstants.Usuario.CORREO + "=? AND " + DBConstants.Usuario.PASSWORD + "=?",
              args,
              null, null, null);

      if (cursor.moveToFirst()) {
         usuarioLogueadoDTO = Usuario.obtenerUsuarioByCursor(cursor);
      }
      return usuarioLogueadoDTO;
   }

   /**
    * Inserta un usuario en la database
    * Creado el 21/04/2016 a las 5:46 PM <br>
    *
    * @param contentValues valores a insertar
    *
    * @return el identificador del usuario ingresado
    *
    * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>@param conexionSQLite
    */
   public long insertUsuario (ConexionSQLite conexionSQLite, ContentValues contentValues) {

      return conexionSQLite.getDbSqlite().insertOrThrow(DBConstants.Usuario.TABLE_USUARIO, null, contentValues);

   }

   /**
    * Actualiza en databas ela informacion de un usuario
    * Creado el 21/04/2016 a las 6:10 PM <br>
    *
    * @param conexionSQLite conexion a la database
    * @param idUsuario      identificador unico del usuario
    * @param contentValues  contentt values con la info de usuario
    *
    * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
    */
   public void updateUsuario (ConexionSQLite conexionSQLite, long idUsuario, ContentValues contentValues) {

      String[] args = new String[]{String.valueOf(idUsuario)};
      conexionSQLite.getDbSqlite().update(DBConstants.Usuario.TABLE_USUARIO, contentValues, DBConstants.Usuario.ID + "=?", args);

   }


}
