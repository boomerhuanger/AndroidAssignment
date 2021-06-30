package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumFragment extends Fragment
{

    private View view;
    private int albumId;
    private ArrayList<Album> albums = new ArrayList<Album>();
    private final String baseUrl = "https://jsonplaceholder.typicode.com/";

    public AlbumFragment(int id)  {
        this.albumId = id + 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //ListView mListView = (ListView) findViewById(R.id.listView1);

        view = inflater.inflate(R.layout.fragment_album, container, false);

        getPhotos();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Albums list", "The length of the list of Albums is" + albums.size());
        ListView albumView = view.findViewById(R.id.listView1);
        AlbumAdapter albumAdapter = new AlbumAdapter(getActivity(), R.layout.album_listview, albums);
        albumView.setAdapter(albumAdapter);
        Log.d("Album adapter set", "Album adapter set");


        return view;
    }



    private void getPhotos() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        UserService service = retrofit.create(UserService.class);

        // Calling '/api/users/2'
        Call<List<Album>> callAsync = service.getPhotos();

        callAsync.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Log.i("Response", call.request().toString());

                List<Album> apiResponse = response.body();


                //users = apiResponse;

                for (Album album : apiResponse) {
                    albums.add(album);
                }



                Log.i("Albums size", "List of albums is" + albums.size());


                //Log.i("All albums", albums.toString());

                //Log.i("response", apiResponse.toString());

                Log.i("my tag1", "testing message1");
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
            }


        });
    }
}