package com.miguel.protectorasmadrid.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.miguel.protectorasmadrid.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapterAnimal extends SliderViewAdapter<SliderAdapterAnimal.SliderAdapterAnimalVH> {
    private Context context;
    private List<Bitmap> listfotos;

    public SliderAdapterAnimal(Context context, List<Bitmap> listfotos) {
        this.context = context;
        this.listfotos = listfotos;
    }

    @Override
    public SliderAdapterAnimalVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sliderview, null);
        return new SliderAdapterAnimalVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterAnimalVH viewHolder, int position) {
        Glide.with(context).load(listfotos.get(position)).into(viewHolder.imgvImagen);
    }

    @Override
    public int getCount() {
        return listfotos.size();
    }

    public class SliderAdapterAnimalVH extends SliderViewAdapter.ViewHolder {
        ImageView imgvImagen;

        public SliderAdapterAnimalVH(View itemView) {
            super(itemView);
            imgvImagen = itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }
}
