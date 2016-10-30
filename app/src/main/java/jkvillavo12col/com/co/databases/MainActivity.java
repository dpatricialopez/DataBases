package jkvillavo12col.com.co.databases;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import jkvillavo12col.com.co.databases.app.DataBasesApp;
import jkvillavo12col.com.co.databases.utils.SharedPrefsKeys;

public class MainActivity extends AppCompatActivity {

   private TextView textViewNombre;

   @Override
   protected void onCreate (Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick (View view) {

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
         }
      });

      textViewNombre = (TextView) findViewById(R.id.main_textViewUsuario);
      textViewNombre.setText(DataBasesApp.getInstance().getCurrentUser().getNombres() + " " +
              DataBasesApp.getInstance().getCurrentUser().getApellidos());
   }

   @Override
   public boolean onCreateOptionsMenu (Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected (MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_desloguear) {

         SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(
                 SharedPrefsKeys.Loggin.GROUP_KEY, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPref.edit();
         editor.remove(SharedPrefsKeys.Loggin.IS_LOGUEO);
         editor.commit();

         finish();

         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   @Override
   protected void onStop () {

      super.onStop();
   }

   @Override
   protected void onPause () {

      super.onPause();
   }

   @Override
   public void onSaveInstanceState (Bundle outState, PersistableBundle outPersistentState) {

      super.onSaveInstanceState(outState, outPersistentState);
   }
}
