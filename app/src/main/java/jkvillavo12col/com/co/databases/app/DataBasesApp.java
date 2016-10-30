package jkvillavo12col.com.co.databases.app;

import android.app.Application;

import jkvillavo12col.com.co.databases.entities.Usuario;

/**
 * Created by JkVillavo12Col on 29/10/16.
 */

public class DataBasesApp extends Application {

   private static DataBasesApp instance;
   private Usuario currentUser;

   /**
    * Obtiene la instancia unica de la clase
    * Creado el 29/10/16 a las 10:55 AM <br>
    *
    * @return instancia unica de la clase
    */
   public static synchronized DataBasesApp getInstance () {

      if (instance == null) {
         instance = new DataBasesApp();
      }
      return instance;
   }

   public Usuario getCurrentUser () {

      return currentUser;
   }

   public void setCurrentUser (Usuario currentUser) {

      this.currentUser = currentUser;
   }

   @Override
   public void onCreate () {

      super.onCreate();
      instance = this;
   }
}
