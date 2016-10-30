package jkvillavo12col.com.co.databases.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jkvillavo12col.com.co.databases.db.DBConstants;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class FileUtils {

   public static void backUpDataBase (Context context) {

      File local = context.getDatabasePath(DBConstants.DB_NAME);
      File external = new File(Environment.getExternalStorageDirectory() + "/BackUpApp/" +
              DBConstants.DB_NAME);
      if (local.exists()) {
         copyFile(local, external);
      }
   }


   public static void copyFile (File rutaOrigen, File rutaDestino) {

      if (!rutaDestino.getParentFile().exists())
         rutaDestino.getParentFile().mkdirs();

      InputStream inStream = null;
      OutputStream outStream = null;
      try {

         inStream = new FileInputStream(rutaOrigen);
         outStream = new FileOutputStream(rutaDestino);

         byte[] buffer = new byte[1024];
         int length;

         while ((length = inStream.read(buffer)) > 0)
            outStream.write(buffer, 0, length);

         inStream.close();
         outStream.close();

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
