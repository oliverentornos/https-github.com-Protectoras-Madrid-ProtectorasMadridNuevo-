package com.miguel.protectorasmadrid.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.miguel.protectorasmadrid.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
    private Context context;
    private List<Bitmap> listFotos = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void deleteItem(int position) {
        this.listFotos.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(Bitmap foto) {
        this.listFotos.add(foto);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sliderview, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        Bitmap animal = listFotos.get(position);

        Glide.with(viewHolder.itemView)
                .load(animal)
                .fitCenter()
                .into(viewHolder.imageView);


    }

    public List<Bitmap> getListlistFotos() {
        return listFotos;
    }

    @Override
    public int getCount() {
        return listFotos.size();
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView imageView;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_auto_image_slider);


        }
    }
}
