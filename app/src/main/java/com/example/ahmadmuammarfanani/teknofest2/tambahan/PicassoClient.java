package com.example.ahmadmuammarfanani.teknofest2.tambahan;

import android.content.Context;
import android.widget.ImageView;

import com.example.ahmadmuammarfanani.teknofest2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class PicassoClient {
    public static void downloading(Context c, String url, ImageView gambar){
        if(url!=null && url.length()>0){
            Picasso.with(c).load(url).resize(150,150).placeholder(R.drawable.ic_launcher_background).into(gambar);
        }else{
            Picasso.with(c).load(R.drawable.ic_launcher_background).into(gambar);
        }
    }
}
