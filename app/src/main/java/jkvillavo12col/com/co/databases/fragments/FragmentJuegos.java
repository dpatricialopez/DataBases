package jkvillavo12col.com.co.databases.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jkvillavo12col.com.co.databases.MainActivity;
import jkvillavo12col.com.co.databases.R;
import jkvillavo12col.com.co.databases.adapters.AdapterLista;
import jkvillavo12col.com.co.databases.dao.JuegoDaoSqlite;
import jkvillavo12col.com.co.databases.db.DBConstants;
import jkvillavo12col.com.co.databases.entities.Juego;
import jkvillavo12col.com.co.databases.mannager.JuegoMannager;

@SuppressLint("ValidFragment")
public class FragmentJuegos extends Fragment {

    List<Juego> listList;
    private RecyclerView mListView;
    private JuegoDaoSqlite juegoDaoSqlite;
    private int vendedor;




    public FragmentJuegos() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_lista, container, false);
        mListView = (RecyclerView) rootView.findViewById(R.id.lista);


        return rootView;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Implementing ActionBar Search inside a fragment
        MenuItem item = menu.add("Search");
        item.setIcon(R.drawable.ic_search_black_24dp); // sets icon
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) sv.findViewById(id);
        textView.setHint("Buscar...");
        textView.setHintTextColor(getResources().getColor(R.color.color_gris));

        // implementing the listener
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //doSearch(s);
                return s.length() < 3;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

        item.setActionView(sv);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cargaJuegos();

    }

    private void cargaJuegos() {

        try {
            listList = JuegoMannager.getInstance().getJuegos(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Object> lista = Arrays.asList(listList.toArray());
        AdapterLista appAdapterRutero = new AdapterLista(getActivity(), lista);

        mListView.setAdapter(appAdapterRutero);


    }



}