package jkvillavo12col.com.co.databases.fragments.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import jkvillavo12col.com.co.databases.R;
import jkvillavo12col.com.co.databases.entities.Usuario;

/**
 * Created by JkVillavo12Col on 28/10/16.
 */

public class DialogAddPersonaFragment extends DialogFragment {

   private View rootView;
   private OnAddPersonaListener mListener;
   private EditText editTextCorreo, editTextNombres, editTextApellidos, editTextPassword;
   private TextInputLayout textInputLayoutCorreo, textInputLayoutNombres, textInputLayoutApellidos, textInputLayoutPassword;
   private Spinner spinnerSexo;
   private Button buttonAgregar, buttonCancelar;

   public interface OnAddPersonaListener {

      public void onAddPersonaAgregar (Usuario persona);

      public void onAddPersonaCancelar ();
   }


   @Override
   public void onAttach (Activity activity) {

      super.onAttach(activity);
      try {
         mListener = (OnAddPersonaListener) activity;
      } catch (ClassCastException e) {
         // The activity doesn't implement the interface, throw exception
         throw new ClassCastException(activity.toString()
                 + " must implement NoticeDialogActionsListener");
      }
   }

   public DialogAddPersonaFragment () {

   }

   @Override
   public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      View rootView = inflater.inflate(R.layout.fragment_dialog_addpersona, container);
      Bundle bundle = getArguments();

      this.rootView = rootView;
      obtenerComponentes(rootView);
      setListeners();

      return rootView;
   }

   @NonNull
   @Override
   public Dialog onCreateDialog (Bundle savedInstanceState) {

      Dialog dialog = super.onCreateDialog(savedInstanceState);
      // request a window without the title
      dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      return dialog;

   }

   private void obtenerComponentes (View view) {

      editTextCorreo = (EditText) rootView.findViewById(R.id.dialogAddPerdosa_editTextCorreo);
      editTextNombres = (EditText) rootView.findViewById(R.id.dialogAddPerdosa_editTextNombres);
      editTextApellidos = (EditText) rootView.findViewById(R.id.dialogAddPerdosa_editTextApellidos);
      editTextPassword = (EditText) rootView.findViewById(R.id.dialogAddPerdosa_editTextPassword);

      textInputLayoutCorreo = (TextInputLayout) rootView.findViewById(R.id.dialogAddPerdosa_textInputCorreo);
      textInputLayoutNombres = (TextInputLayout) rootView.findViewById(R.id.dialogAddPerdosa_textInputNombres);
      textInputLayoutApellidos = (TextInputLayout) rootView.findViewById(R.id.dialogAddPerdosa_textInputApellidos);
      textInputLayoutPassword = (TextInputLayout) rootView.findViewById(R.id.dialogAddPerdosa_textInputPassword);

      spinnerSexo = (Spinner) rootView.findViewById(R.id.dialogAddPerdosa_spinnerSexo);

      buttonCancelar = (Button) rootView.findViewById(R.id.dialogAddPerdosa_buttonCancelar);
      buttonAgregar = (Button) rootView.findViewById(R.id.dialogAddPerdosa_buttonAgregar);


   }

   private void setListeners () {

      buttonCancelar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick (View view) {

            mListener.onAddPersonaCancelar();
            dismiss();
         }
      });

      buttonAgregar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick (View view) {

            if (validarInformacion()) {
               mListener.onAddPersonaAgregar(obtenerPersonaFromForm());
            } else {
               Toast.makeText(getActivity(), "Complete form", Toast.LENGTH_SHORT).show();
            }
            dismiss();

         }
      });

   }

   private boolean validarInformacion () {

      int errores = 0;
      if ("".equalsIgnoreCase(editTextCorreo.getText().toString())) {
         textInputLayoutCorreo.setError("Necesario");
         errores++;
      } else {
         textInputLayoutCorreo.setError(null);
      }

      if ("".equalsIgnoreCase(editTextNombres.getText().toString())) {
         textInputLayoutNombres.setError("Necesario");
         errores++;
      } else {
         textInputLayoutNombres.setError(null);
      }

      if ("".equalsIgnoreCase(editTextApellidos.getText().toString())) {
         textInputLayoutApellidos.setError("Necesario");
         errores++;
      } else {
         textInputLayoutApellidos.setError(null);
      }

      if ("".equalsIgnoreCase(editTextPassword.getText().toString())) {
         textInputLayoutPassword.setError("Necesario");
         errores++;
      } else {
         textInputLayoutPassword.setError(null);
      }

      if (errores == 0) {
         return true;
      }
      return false;
   }

   private Usuario obtenerPersonaFromForm () {

      Usuario persona = new Usuario();
      persona.setCorreo(editTextCorreo.getText().toString());
      persona.setNombres(editTextNombres.getText().toString());
      persona.setApellidos(editTextApellidos.getText().toString());
      persona.setPassword(editTextPassword.getText().toString());
      if (spinnerSexo.getSelectedItemPosition() == 0) {
         persona.setMachito(true);
      } else {
         persona.setMachito(false);
      }
      return persona;
   }

   @Override
   public void onResume () {
      // Get existing layout params for the window
      ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
      // Assign window properties to fill the parent
      params.width = WindowManager.LayoutParams.MATCH_PARENT;
      params.height = WindowManager.LayoutParams.MATCH_PARENT;
      getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
      // Call super onResume after sizing
      super.onResume();
   }

   @Override
   public void onSaveInstanceState (Bundle outState) {

      super.onSaveInstanceState(outState);
   }

   @Override
   public void onPause () {

      super.onPause();
   }

   @Override
   public void onStop () {

      super.onStop();
   }
}
