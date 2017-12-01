package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ahmadmuammarfanani.teknofest2.tambahan.Produk;
import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by John on 11/29/2017.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {
    private ArrayList<DataSnapshot> produk;
    private Context nowContext;


    public HorizontalAdapter(ArrayList<DataSnapshot> product , Context currentcotext) {
        this.produk = product;
        this.nowContext = currentcotext;
    }


    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.costom_layout_recyclerview, parent , false);

        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HorizontalViewHolder holder, int position) {
        holder.Namamkn.setText(produk.get(position).child("Nama").getValue(String.class));
        holder.Hargamkn.setText(produk.get(position).child("Harga").getValue(String.class));

        Picasso.with(nowContext)
                .load(produk.get(position).child("IMGUrl").child("APriority").getValue(String.class))
                .into((ImageView) holder.img);

    }

    @Override
    public int getItemCount() {
        if(produk.size() > 5){
            return 5;
        }else{
            return produk.size();
        }
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {
        TextView Namamkn;
        TextView Hargamkn;
        ImageView img;
        HorizontalViewHolder(View itemview){
            super(itemview);
            Namamkn = (TextView) itemview.findViewById(R.id.namamakanan);
            img = (ImageView) itemview.findViewById(R.id.gambarmakanan);
            Hargamkn = (TextView) itemview.findViewById(R.id.hargamakanan);
        }
    }
}