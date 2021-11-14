package com.miguel.protectorasmadrid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miguel.protectorasmadrid.Clases.PortadaAnimal;
import com.miguel.protectorasmadrid.R;
import com.miguel.protectorasmadrid.UsuarioActivities.AnimalActivity;
import com.miguel.protectorasmadrid.Utils.Utiles;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<PortadaAnimal> listDatos;
    ArrayList<PortadaAnimal> originalDatos;
    Context context;
    private static View.OnClickListener listener;

    public RecycleAdapter(ArrayList<PortadaAnimal> listDatos, Context context) {
        this.listDatos = listDatos;
        this.context = context;
        this.originalDatos = new ArrayList<>();
        originalDatos.addAll(listDatos);

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, null, false);
        view.setOnClickListener(listener);

        return new ViewHolderDatos(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.tvFoto.setText(listDatos.get(position).getNOMBRE());
        holder.tvDesc.setText(listDatos.get(position).getDESCRIPCIONANIMAL());
        holder.imagen.setImageBitmap(Utiles.base64ToBitmap(listDatos.get(position).getFOTO()));

    }

    @Override
    public int getItemCount() {

        return listDatos.size();
    }

    // Filtro de busqueda
    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            listDatos.clear();
            listDatos.addAll(originalDatos);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                listDatos.clear();
                ArrayList<PortadaAnimal> collect = (ArrayList<PortadaAnimal>) originalDatos.stream()
                        .filter(i -> i.getNOMBRE().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                listDatos.addAll(collect);
            } else {
                listDatos.clear();
                for (PortadaAnimal i : originalDatos) {
                    if (i.getNOMBRE().toLowerCase().contains(strSearch)) {
                        listDatos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView tvFoto, tvDesc, header;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgvFoto);
            tvFoto = itemView.findViewById(R.id.tvFoto);
            tvDesc = itemView.findViewById(R.id.tvDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {
                        //Animal posicion = listDatos.get(pos);

                        Intent intent = new Intent(context, AnimalActivity.class);
                        intent.putExtra("idAnimal", listDatos.get(pos).getIDANIMAL());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        // Animacion
//                        ActivityOptionsCompat options = ActivityOptionsCompat.
//                                makeSceneTransitionAnimation((Activity) context, v, "transicion");
//                        context.startActivity(intent, options.toBundle());
                    }
                }
            });

        }


    }
}
