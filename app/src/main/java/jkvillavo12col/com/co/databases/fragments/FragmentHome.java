package jkvillavo12col.com.co.databases.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import jkvillavo12col.com.co.databases.R;
import jkvillavo12col.com.co.databases.adapters.TabsAdapter;


public class FragmentHome extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabsAdapter tabsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);

        setHasOptionsMenu(true);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            tabsAdapter = new TabsAdapter(getChildFragmentManager());

            tabsAdapter.addFragment(new FragmentJuegos(0), "Juegos");
            tabsAdapter.addFragment(new FragmentJuegos(0), "Vehiculos");

            viewPager.setAdapter(tabsAdapter);
            tabLayout.setupWithViewPager(viewPager);



    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuItem item2 = menu.add("Carrito");
        item2.setIcon(R.drawable.ic_cloud_upload_white_24dp); // sets icon
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Sincronizar...
                if (connectionDetector.isConnected()) {
                    List<RequestGuardarEditarPunto> puntoList = mydb.getPuntosSincronizar("Sincronizar");
                    if (puntoList.size() > 0) {
                        setPuntoSincronizar(puntoList);
                    } else {
                        Toast.makeText(getActivity(), "No tiene Puntos para sincronizar!", Toast.LENGTH_LONG).show();
                        List<SincronizarPedidos> sincronizarPedidosList = mydb.sincronizarPedido();
                        if (sincronizarPedidosList.size() > 0) {
                            setPedidosSincroinzar();
                        } else {
                            Toast.makeText(getActivity(), "No tengo pedidos para sincronizar!", Toast.LENGTH_LONG).show();
                            List<NoVisita> noVisitaList = mydb.sincronizarNoVisita();
                            if (noVisitaList.size() > 0) {
                                setSincroinizarNoVisita();
                            } else {
                                Toast.makeText(getActivity(), "No tiene datos para sincronizar", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "No tiene acceso a internet para sincronizar", Toast.LENGTH_LONG).show();
                }

                return true;
            }

        });
    }



}
