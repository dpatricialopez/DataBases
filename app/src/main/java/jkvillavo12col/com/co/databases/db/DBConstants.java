package jkvillavo12col.com.co.databases.db;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class DBConstants {

   public final static String DB_NAME = "DataBaseTest";
   public final static int DB_VERSION = 2;

   public static class Usuario {

      public final static String TABLE_USUARIO = "Usuario";

      public final static String ID = "id";
      public final static String NOMBRES = "nombres";
      public final static String APELLIDOS = "apellidos";
      public final static String CORREO = "correo";
      public final static String PASSWORD = "password";
      public final static String SEXO = "sexo";

      private static StringBuilder builderCreate;

      /**
       * Obtiene un array de Strings con los campos de la tabla
       *
       * @return array de estring de los campos de la tabla
       */
      public static String[] obtenerCampos () {

         return new String[]{ID, NOMBRES, APELLIDOS, CORREO, PASSWORD, SEXO};
      }

      public static String getSQLCreate () {

         builderCreate = new StringBuilder();
         builderCreate.append("	CREATE TABLE Usuario (	");
         builderCreate.append("	   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,	");
         builderCreate.append("	   nombres    TEXT,	");
         builderCreate.append("	   apellidos  TEXT,	");
         builderCreate.append("	   correo TEXT,	");
         builderCreate.append("	   password   TEXT,	");
         builderCreate.append("	   sexo   NUMERIC	");
         builderCreate.append("	);	");

         return builderCreate.toString();
      }

   }

   public static class Juego {

      public final static String TABLE_JUEGOS = "Juego";

      public final static String ID = "id";
      public final static String IDUSUARIO = "id_usuario";
      public final static String NOMBRES = "nombres";
      public final static String CONSOLA = "consola";
      public final static String MODO = "modo";
      public final static String CALIFICACION = "calificacion";

      private static StringBuilder builderCreate;

      /**
       * Obtiene un array de Strings con los campos de la tabla
       *
       * @return array de estring de los campos de la tabla
       */
      public static String[] obtenerCampos () {

         return new String[]{ID, IDUSUARIO, NOMBRES, CONSOLA, MODO, CALIFICACION};
      }

      public static String getSQLCreate () {

         builderCreate = new StringBuilder();
         builderCreate.append("	CREATE TABLE Juegos (	");
         builderCreate.append("	   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,	");
         builderCreate.append("	   idUsuario    int,	");
         builderCreate.append("	   nombres    TEXT,	");
         builderCreate.append("	   consola  TEXT,	");
         builderCreate.append("	   modo ,	INTEGER");
         builderCreate.append("	   calificacion   INTEGER,	");
         builderCreate.append("FOREIGN KEY(`idUsuario`) REFERENCES Usuario(id)");
         builderCreate.append("	);	");

         return builderCreate.toString();
      }
   }

   public static class Vehiculo {

      public final static String TABLE_VEHICULOS = "VEHICULO";

      public final static String ID = "id";
      public final static String IDUSUARIO = "id_usuario";
      public final static String PLACA = "placa";
      public final static String COLOR = "color";
      public final static String ANIO = "anio";
      public final static String MARCA = "marca";

      private static StringBuilder builderCreate;

      /**
       * Obtiene un array de Strings con los campos de la tabla
       *
       * @return array de estring de los campos de la tabla
       */
      public static String[] obtenerCampos () {

         return new String[]{ID, IDUSUARIO, PLACA, COLOR, ANIO, MARCA};
      }

      public static String getSQLCreate () {

         builderCreate = new StringBuilder();
         builderCreate.append("	CREATE TABLE vehiculo (	");
         builderCreate.append("	   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,	");
         builderCreate.append("	   idUsuario    int,	");
         builderCreate.append("	   placa    TEXT,	");
         builderCreate.append("	   color  TEXT,	");
         builderCreate.append("	   anio ,	INTEGER");
         builderCreate.append("	   marca   TEXT,	");
         builderCreate.append("FOREIGN KEY(`idUsuario`) REFERENCES Usuario(id)");
         builderCreate.append("	);	");

         return builderCreate.toString();
      }
   }

}
