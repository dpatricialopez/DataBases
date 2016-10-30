package jkvillavo12col.com.co.databases.entities;

import android.database.Cursor;

import jkvillavo12col.com.co.databases.db.DBConstants;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class Juego {

   private int id;
   private int id_usuario;
   private String nombres;
   private String consola;
   private int modo;
   private int calificacion;


   public Juego(int id, int id_usuario, String nombres, String consola, int modo, int calificacion) {
      this.id = id;
      this.id_usuario = id_usuario;
      this.nombres = nombres;
      this.consola = consola;
      this.modo = modo;
      this.calificacion = calificacion;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId_usuario() {
      return id_usuario;
   }

   public void setId_usuario(int id_usuario) {
      this.id_usuario = id_usuario;
   }

   public String getNombres() {
      return nombres;
   }

   public void setNombres(String nombres) {
      this.nombres = nombres;
   }

   public String getConsola() {
      return consola;
   }

   public void setConsola(String consola) {
      this.consola = consola;
   }

   public int getModo() {
      return modo;
   }

   public void setModo(int modo) {
      this.modo = modo;
   }

   public int getCalificacion() {
      return calificacion;
   }

   public void setCalificacion(int calificacion) {
      this.calificacion = calificacion;
   }

   public Juego() {
   }

   public static Juego obtenerJuegoByCursor (Cursor cursor) {

      Juego juego = new Juego();
      juego.setId(cursor.getInt(cursor.getColumnIndex(DBConstants.Juego.ID)));
      juego.setId_usuario(cursor.getInt(cursor.getColumnIndex(DBConstants.Juego.IDUSUARIO)));
      juego.setNombres(cursor.getString(cursor.getColumnIndex(DBConstants.Juego.NOMBRES)));
      juego.setCalificacion(cursor.getInt(cursor.getColumnIndex(DBConstants.Juego.CONSOLA)));
      juego.setModo(cursor.getInt(cursor.getColumnIndex(DBConstants.Juego.MODO)));

      return juego;

   }




}
