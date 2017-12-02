package com.example.ahmadmuammarfanani.teknofest2.tambahan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by John on 11/29/2017.
 */

public class HorizontalAdapter2 extends RecyclerView.Adapter<HorizontalAdapter2.HorizontalViewHolder> {
private ArrayList<DataSnapshot> produk;
private Context nowContext;

public HorizontalAdapter2(ArrayList<DataSnapshot> product , Context currentcotext) {
    this.produk = product;
    this.nowContext = currentcotext;

}

@Override
public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.costom_layout_recyclerview2 , parent , false);

    return new HorizontalViewHolder(view);
}

@Override
public void onBindViewHolder(final HorizontalViewHolder holder, final int position) {
    final DataSnapshot data = produk.get(position);
    holder.Namamkn.setText(produk.get(position).child("Nama").getValue(String.class));
    holder.Hargamkn.setText("Rp."+produk.get(position).child("Harga").getValue(String.class)+",-");

    String v = produk.get(position).getRef().getParent().getKey().toString();
    holder.img.setTag(v);
    //holder.namaToko.setText(produk.get(position).getKey().toString());
    /*holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//                Toast.makeText(this , "Wait For Open" , Toast.LENGTH_SHORT).show();
            listener.onClick(data);
        }
    });*/
    Picasso.with(nowContext)
            .load(produk.get(position).child("IMGUrl").child("APriority").getValue(String.class))
            .resize(144,128)
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

public void onClickItem(DataSnapshot data){
    Toast.makeText(nowContext, data.child("Nama").getValue(String.class), Toast.LENGTH_SHORT).show();

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
/*
public void setListener(MyItemClickListener listener){
    this.listener = listener;
}*/
}
