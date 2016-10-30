package jkvillavo12col.com.co.databases.asyncs;

import android.os.AsyncTask;

import jkvillavo12col.com.co.databases.SplashActivity;
import jkvillavo12col.com.co.databases.db.ConexionSQLite;
import jkvillavo12col.com.co.databases.utils.FileUtils;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class AsyncTaskSplash extends AsyncTask<Void, Integer, Boolean> {

   SplashActivity activity;

   public AsyncTaskSplash (SplashActivity activityLoggin) {

      this.activity = activityLoggin;
   }


   @Override
   protected void onPreExecute () {


   }

   @Override
   protected Boolean doInBackground (Void... params) {

      try {

         ConexionSQLite conexionSQLite = ConexionSQLite.getInstance(activity.getApplicationContext());
         conexionSQLite.openDatabase();
         conexionSQLite.closeDatabase();
         // TODO: comentar la copia de la datatabse
         FileUtils.backUpDataBase(activity.getApplicationContext());
         Thread.sleep(2000);

         return false;

      } catch (Exception e) {
         return false;
      }

   }

   @Override
   protected void onPostExecute (Boolean result) {

      activity.nextActivity();

   }


}