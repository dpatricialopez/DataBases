package jkvillavo12col.com.co.databases.entities;

import android.database.Cursor;

import jkvillavo12col.com.co.databases.db.DBConstants;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class Vehiculo {

   private int id;
   private int id_usuario;
   private String placas;
   private String marca;
   private String color;
   private int anio;

   public Vehiculo(int id, int id_usuario, String placas, String marca, String color, int anio) {
      this.id = id;
      this.id_usuario = id_usuario;
      this.placas = placas;
      this.marca = marca;
      this.color = color;
      this.anio = anio;
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

   public String getPlacas() {
      return placas;
   }

   public void setPlacas(String placas) {
      this.placas = placas;
   }

   public String getMarca() {
      return marca;
   }

   public void setMarca(String marca) {
      this.marca = marca;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public int getAnio() {
      return anio;
   }

   public void setAnio(int anio) {
      this.anio = anio;
   }

   public static Vehiculo obtenerVehiculoByCursor (Cursor cursor) {

      Vehiculo vehiculo = new Vehiculo();
      vehiculo.setId(cursor.getInt(cursor.getColumnIndex(DBConstants.Vehiculo.ID)));
      vehiculo.setId_usuario(cursor.getInt(cursor.getColumnIndex(DBConstants.Vehiculo.IDUSUARIO)));
      vehiculo.setPlacas(cursor.getString(cursor.getColumnIndex(DBConstants.Vehiculo.PLACA)));
      vehiculo.setMarca(cursor.getString(cursor.getColumnIndex(DBConstants.Vehiculo.MARCA)));
      vehiculo.setColor(cursor.getString(cursor.getColumnIndex(DBConstants.Vehiculo.COLOR)));
      vehiculo.setAnio(cursor.getInt(cursor.getColumnIndex(DBConstants.Vehiculo.ANIO)));

      return vehiculo;
   }

   public Vehiculo() {


   }


}
