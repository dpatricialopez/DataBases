package jkvillavo12col.com.co.databases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jkvillavo12col.com.co.databases.asyncs.AsyncTaskSplash;

public class SplashActivity extends AppCompatActivity {


   @Override
   protected void onCreate (Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash);

      AsyncTaskSplash asyncTaskSplash = new AsyncTaskSplash(SplashActivity.this);
      asyncTaskSplash.execute();

   }

   public void nextActivity () {

      Intent intent = new Intent(SplashActivity.this, LogginActivity.class);
      startActivity(intent);

   }
}
