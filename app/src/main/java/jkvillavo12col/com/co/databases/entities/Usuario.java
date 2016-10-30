package jkvillavo12col.com.co.databases.entities;

import android.database.Cursor;

import jkvillavo12col.com.co.databases.db.DBConstants;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class Usuario {

   private int id;
   private String nombres;
   private String apellidos;
   private String correo;
   private String password;
   private boolean machito;

   public Usuario (String nombres, String apellidos, String correo, String password, boolean machito) {

      this.nombres = nombres;
      this.apellidos = apellidos;
      this.correo = correo;
      this.password = password;
      this.machito = machito;
   }

   public static Usuario obtenerUsuarioByCursor (Cursor cursor) {

      Usuario usuario = new Usuario();
      usuario.setId(cursor.getInt(cursor.getColumnIndex(DBConstants.Usuario.ID)));
      usuario.setNombres(cursor.getString(cursor.getColumnIndex(DBConstants.Usuario.NOMBRES)));
      usuario.setApellidos(cursor.getString(cursor.getColumnIndex(DBConstants.Usuario.APELLIDOS)));
      usuario.setCorreo(cursor.getString(cursor.getColumnIndex(DBConstants.Usuario.CORREO)));
      usuario.setPassword(cursor.getString(cursor.getColumnIndex(DBConstants.Usuario.PASSWORD)));
      if (cursor.getInt(cursor.getColumnIndex(DBConstants.Usuario.SEXO)) == 1) {
         usuario.setMachito(true);
      } else {
         usuario.setMachito(false);
      }
      return usuario;
   }

   public Usuario () {


   }

   public int getId () {

      return id;
   }


   public void setId (int id) {

      this.id = id;
   }

   public String getNombres () {

      return nombres;
   }

   public void setNombres (String nombres) {

      this.nombres = nombres;
   }

   public String getApellidos () {

      return apellidos;
   }

   public void setApellidos (String apellidos) {

      this.apellidos = apellidos;
   }

   public String getCorreo () {

      return correo;
   }

   public void setCorreo (String correo) {

      this.correo = correo;
   }

   public String getPassword () {

      return password;
   }

   public void setPassword (String password) {

      this.password = password;
   }

   public boolean isMachito () {

      return machito;
   }

   public void setMachito (boolean machito) {

      this.machito = machito;
   }
}
