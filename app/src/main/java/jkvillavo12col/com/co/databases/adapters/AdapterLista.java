package jkvillavo12col.com.co.databases.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jkvillavo12col.com.co.databases.R;
import jkvillavo12col.com.co.databases.entities.Juego;
import jkvillavo12col.com.co.databases.entities.Vehiculo;



public class AdapterLista extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private Context context;
   private List<Object> objects;
   private final int VEHICULO = 0, JUEGO = 1;

   public AdapterLista(Context context, List<Object> objects) {

      this.context = context;
      this.objects = objects;
   }

   @Override
   public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

      RecyclerView.ViewHolder viewHolder;
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());

      switch (viewType) {
         case VEHICULO:
            View v1 = inflater.inflate(R.layout.custom_item_vehicle, parent, false);
            viewHolder = new VehiculoViewHolder(v1);
            break;
         case JUEGO:
            View v2 = inflater.inflate(R.layout.custom_item_game, parent, false);
            viewHolder = new BuildingViewHolder(v2);
            break;
         default:
            View v2otro = inflater.inflate(R.layout.custom_item_game, parent, false);
            viewHolder = new BuildingViewHolder(v2otro);
            break;
      }
      return viewHolder;

   }

   @Override
   public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {

      switch (holder.getItemViewType()) {
         case VEHICULO:
            VehiculoViewHolder vehiculoViewHolder = (VehiculoViewHolder) holder;
            Vehiculo vehiculo = (Vehiculo) objects.get(position);
            vehiculoViewHolder.bindVehiculo(vehiculo);
            break;
         case JUEGO:
            BuildingViewHolder buildingViewHolder = (BuildingViewHolder) holder;
            Juego juego = (Juego) objects.get(position);
            buildingViewHolder.bindJuego(juego);
            break;
         default:
            break;
      }


   }

   @Override
   public int getItemViewType (int position) {

      if (objects.get(position) instanceof Vehiculo) {
         return VEHICULO;
      } else if (objects.get(position) instanceof Juego) {
         return JUEGO;
      }
      return -1;
   }

   @Override
   public int getItemCount () {

      return objects.size();
   }

   class VehiculoViewHolder extends RecyclerView.ViewHolder {

      private TextView textViewPlaca, textViewCiudad, textViewAnio, textViewMarca, textViewColor;

      public VehiculoViewHolder (View itemView) {

         super(itemView);

         textViewPlaca = (TextView) itemView.findViewById(R.id.customItemVehicle_textViewPlaca);
         textViewCiudad = (TextView) itemView.findViewById(R.id.customItemVehicle_textViewCiudad);
         textViewAnio = (TextView) itemView.findViewById(R.id.customItemVehicle_textViewAnio);
         textViewMarca = (TextView) itemView.findViewById(R.id.customItemVehicle_textViewMarca);
         textViewColor = (TextView) itemView.findViewById(R.id.customItemVehicle_textViewColor);

      }

      public void bindVehiculo (Vehiculo vehiculo) {

         textViewPlaca.setText(vehiculo.getPlacas());

         textViewAnio.setText(vehiculo.getAnio());
         textViewMarca.setText(vehiculo.getMarca());

         switch (vehiculo.getColor()) {

            case "AZUL":
               textViewColor.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));
               break;
            case "BLANCO":
               textViewColor.setBackgroundColor(context.getResources().getColor(android.R.color.white));
               break;
            case"NEGRO":
               textViewColor.setBackgroundColor(context.getResources().getColor(android.R.color.black));
               break;
            default:
               textViewColor.setBackgroundColor(context.getResources().getColor(android.R.color.black));
               break;

         }

      }

   }

   class BuildingViewHolder extends RecyclerView.ViewHolder {

      private TextView txtNombre, txtConsola, txtModo, txtCalificacion;

      public BuildingViewHolder (View itemView) {

         super(itemView);

         txtNombre= (TextView) itemView.findViewById(R.id.juego_nombre);
         txtConsola = (TextView) itemView.findViewById(R.id.juego_consola);
         txtModo = (TextView) itemView.findViewById(R.id.juego_modo);
         txtCalificacion = (TextView) itemView.findViewById(R.id.juego_calificacion);

      }

      public void bindJuego (Juego juego) {

         txtNombre.setText(juego.getNombres());
         txtConsola.setText(juego.getConsola());
         txtModo.setText(juego.getModo());
         txtCalificacion.setText(juego.getCalificacion());

      }

   }

}
