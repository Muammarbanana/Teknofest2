package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Produk> produks;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Produk> produks) {
        this.c = c;
        this.produks = produks;
    }

    @Override
    public int getCount() {
        return produks.size();
    }

    @Override
    public Object getItem(int i) {
        return produks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        if(inflater==null){
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }if(convertview==null){
            convertview=inflater.inflate(R.layout.listview_layout,viewGroup,false);
        }
        MyHolder holder = new MyHolder(convertview);
        holder.nama.setText(produks.get(i).getNama());
        holder.harga.setText(produks.get(i).getHarga());
        holder.lokasi.setText(produks.get(i).getLokasi());
        holder.jam.setText(produks.get(i).getJam());
        PicassoClient.downloading(c,produks.get(i).getUrl(),holder.gambar);
        PicassoClient.downloading(c,produks.get(i).getUrl2(),holder.foto);

        return convertview;
    }
}
