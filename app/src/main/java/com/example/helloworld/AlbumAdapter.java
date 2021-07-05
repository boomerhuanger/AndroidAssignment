package com.example.helloworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Album> {

    private Context mContext;
    private ImageView image;

    int mResource;

    public AlbumAdapter(Context context, int resource, ArrayList<Album> albums) {
        super(context, resource, albums);
        mContext = context;
        mResource = resource;
    }

    //getting the view and attach it to the ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int id = getItem(position).getId();
        int albumId = getItem(position).getAlbumId();
        String title = getItem(position).getTitle();
        String url = getItem(position).getUrl();
        String thumbnailUrl = getItem(position).getThumbnailUrl();
        Integer.toString(id);
        Integer.toString(albumId);

        Log.d("Image url", url);

        Album album = new Album(id, albumId, title, url, thumbnailUrl);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        image = convertView.findViewById(R.id.albumImage);

        Log.d("Image", image.toString());
        /*Bitmap bimage = null;

        Log.d("Got here", "Got here");

        try {
            InputStream in = new java.net.URL(url).openStream();
            bimage = BitmapFactory.decodeStream(in);
            if (bimage != null) {
                Log.d("Image found", "Image found");
            }
        } catch (IOException e) {
            Log.d("Image error", "Image error");
            e.printStackTrace();
        }


        image.setImageBitmap(bimage);
        //image.setVisibility(View.VISIBLE);*/

        Picasso.get().load(url).into(image);
        Picasso.get().setLoggingEnabled(true);

        /*GlideUrl url1 = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Glide.with(getContext()).load(url1).into(image);*/





        return convertView;

    }
}
