package com.miguel.protectorasmadrid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miguel.protectorasmadrid.Clases.Noticia;
import com.miguel.protectorasmadrid.R;

import java.util.ConcurrentModificationException;
import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    Context context;
    List<Noticia> listaNoticias;

    public NoticiasAdapter(Context context, List<Noticia> listaNoticias) {
        this.context = context;
        this.listaNoticias = listaNoticias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticias, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitulo.setText(listaNoticias.get(position).getTitulo());
        holder.tvDesc.setText(listaNoticias.get(position).getDescripcion());
        holder.tvFecha.setText(listaNoticias.get(position).getFecha());
        holder.tvProtectora.setText(listaNoticias.get(position).getNomProtectora());
        Glide.with(holder.fotoNoticia).load(listaNoticias.get(position).getFoto()).into(holder.fotoNoticia);

    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitulo, tvDesc, tvFecha, tvProtectora;
        ImageView fotoNoticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloNoticia);
            tvDesc = itemView.findViewById(R.id.tvDescNoticia);
            tvFecha = itemView.findViewById(R.id.tvFechaNoticia);
            tvProtectora = itemView.findViewById(R.id.tvNomProtectora);
            fotoNoticia = itemView.findViewById(R.id.imgvFotoNoticia);

        }
    }
}
