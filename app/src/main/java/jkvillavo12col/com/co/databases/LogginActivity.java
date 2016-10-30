package jkvillavo12col.com.co.databases;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import jkvillavo12col.com.co.databases.app.DataBasesApp;
import jkvillavo12col.com.co.databases.entities.Usuario;
import jkvillavo12col.com.co.databases.fragments.dialogs.DialogAddPersonaFragment;
import jkvillavo12col.com.co.databases.mannager.UsuarioMannager;
import jkvillavo12col.com.co.databases.utils.SharedPrefsKeys;

public class LogginActivity extends AppCompatActivity implements DialogAddPersonaFragment.OnAddPersonaListener {

   private TextInputLayout textInputLayoutCorreo, textInputLayoutPassword;
   private EditText editTextCorreo, editTextPassword;
   private FloatingActionButton fab;

   @Override
   protected void onCreate (Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_loggin);

      SharedPreferences sharedPref = LogginActivity.this.getSharedPreferences(
              SharedPrefsKeys.Loggin.GROUP_KEY, Context.MODE_PRIVATE);
      if (sharedPref.contains(SharedPrefsKeys.Loggin.IS_LOGUEO)) {
         boolean isLogueo = sharedPref.getBoolean(SharedPrefsKeys.Loggin.IS_LOGUEO, false);
         if (isLogueo) {
            Intent intent = new Intent(LogginActivity.this, MainActivity.class);
            startActivity(intent);
         }
      }

      if (sharedPref.contains(SharedPrefsKeys.Loggin.ADDPERSONA)) {
         boolean addPersona = sharedPref.getBoolean(SharedPrefsKeys.Loggin.ADDPERSONA, false);
         if (addPersona) {
            FragmentManager fm = getSupportFragmentManager();
            DialogFragment newFragment = new DialogAddPersonaFragment();
            newFragment.show(fm, "DialogAddPersonaFragment");
         }
      }


      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      textInputLayoutCorreo = (TextInputLayout) findViewById(R.id.loggin_textInputCorreo);
      textInputLayoutPassword = (TextInputLayout) findViewById(R.id.loggin_textInputPassword);
      editTextCorreo = (EditText) findViewById(R.id.loggin_editTextCorreo);
      editTextPassword = (EditText) findViewById(R.id.loggin_editTextPassword);

      fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick (View view) {

            agregarUsuario();
         }
      });
   }


   private void agregarUsuario () {

      SharedPreferences sharedPref = LogginActivity.this.getSharedPreferences(
              SharedPrefsKeys.Loggin.GROUP_KEY, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPref.edit();
      editor.putBoolean(SharedPrefsKeys.Loggin.ADDPERSONA, true);
      editor.commit();

      FragmentManager fm = getSupportFragmentManager();
      DialogFragment newFragment = new DialogAddPersonaFragment();
      newFragment.show(fm, "DialogAddPersonaFragment");

   }

   public void ingresar (View view) {

      if (validarInformacion()) {
         Usuario usuarioDb = UsuarioMannager.getInstance().findUsuarioByUserPass(LogginActivity.this,
                 editTextCorreo.getText().toString(),
                 editTextPassword.getText().toString());
         if (usuarioDb == null) {
            Toast.makeText(this, "Usuario o contraseña erroneos", Toast.LENGTH_SHORT).show();
         } else {

            SharedPreferences sharedPref = LogginActivity.this.getSharedPreferences(
                    SharedPrefsKeys.Loggin.GROUP_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(SharedPrefsKeys.Loggin.IS_LOGUEO, true);
            editor.commit();

            DataBasesApp.getInstance().setCurrentUser(usuarioDb);

            Intent intent = new Intent(LogginActivity.this, MainActivity.class);
            startActivity(intent);
         }
      } else {
         Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show();
      }


   }

   private boolean validarInformacion () {

      int errores = 0;

      if ("".equalsIgnoreCase(editTextCorreo.getText().toString())) {
         errores++;
         textInputLayoutCorreo.setError("Necesario");
      } else {
         textInputLayoutCorreo.setError(null);
      }

      if ("".equalsIgnoreCase(editTextPassword.getText().toString())) {
         errores++;
         textInputLayoutPassword.setError("Necesario");
      } else {
         textInputLayoutPassword.setError(null);
      }

      if (errores == 0) return true;

      return false;
   }

   @Override
   public void onAddPersonaAgregar (Usuario persona) {

      Usuario usuarioDb = UsuarioMannager.getInstance().findUsuarioByUserPass(LogginActivity.this, persona.getCorreo(), persona.getPassword());
      if (usuarioDb == null) {
         UsuarioMannager.getInstance().insertUsuario(LogginActivity.this, persona);
      } else {
         Toast.makeText(this, "Ya existe una persona con el correo ingresado", Toast.LENGTH_SHORT).show();
      }

   }

   @Override
   public void onAddPersonaCancelar () {


      SharedPreferences sharedPref = LogginActivity.this.getSharedPreferences(
              SharedPrefsKeys.Loggin.GROUP_KEY, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPref.edit();
      editor.putBoolean(SharedPrefsKeys.Loggin.ADDPERSONA, false);
      editor.commit();

   }
}
