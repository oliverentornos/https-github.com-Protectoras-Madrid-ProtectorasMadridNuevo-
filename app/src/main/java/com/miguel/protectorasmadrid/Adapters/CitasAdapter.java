package com.miguel.protectorasmadrid.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.protectorasmadrid.Clases.Cita;
import com.miguel.protectorasmadrid.R;

import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder> {
    Context context;
    List<Cita> listCitas;

    public CitasAdapter(Context context, List<Cita> listCitas) {
        this.context = context;
        this.listCitas = listCitas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citas, null, false);
        return new CitasAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (listCitas.get(position).getEstado()) {
            case 2:
                holder.lyEstado.setBackgroundColor(Color.parseColor("#2F86A6"));
                holder.tvEstado.setTextColor(Color.parseColor("#2F86A6"));
                holder.tvEstado.setText("En espera");
                break;
            case 0:
                holder.lyEstado.setBackgroundColor(Color.parseColor("#3E7C17"));
                holder.tvEstado.setTextColor(Color.parseColor("#3E7C17"));
                holder.tvEstado.setText("Realizada");

                break;
            case 1:
                holder.lyEstado.setBackgroundColor(Color.parseColor("#a51b30"));
                holder.tvEstado.setTextColor(Color.parseColor("#a51b30"));
                holder.tvEstado.setText("No realizada");

                break;
        }
        holder.tvFecha.setText(listCitas.get(position).getFecha());
        holder.tvHora.setText(listCitas.get(position).getHora());
        holder.tvCliente.setText(listCitas.get(position).getNombre());
        holder.tvAnimal.setText(listCitas.get(position).getAnimal());
        holder.tvEspecie.setText(listCitas.get(position).getEspecie());
    }

    @Override
    public int getItemCount() {
        return listCitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFecha, tvHora, tvCliente, tvAnimal, tvEspecie, tvEstado;
        LinearLayout lyEstado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFecha = itemView.findViewById(R.id.tvFechaCita);
            tvHora = itemView.findViewById(R.id.tvHoraCita);
            tvCliente = itemView.findViewById(R.id.tvClienteText);
            tvAnimal = itemView.findViewById(R.id.tvAnimalText);
            tvEspecie = itemView.findViewById(R.id.tvEspecieText);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            lyEstado = itemView.findViewById(R.id.lyEstado);
        }
    }
}
