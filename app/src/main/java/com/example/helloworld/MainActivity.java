package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private final String baseUrl = "https://jsonplaceholder.typicode.com/";
    private ArrayList<User> users = new ArrayList<User>();
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);
        TextView title = findViewById(R.id.title);
        title.setText("User Info");


        //creating a HTTP client to send and create a response
        getUsers();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Log.d("Users list", "The length of the list of Users is" + users.size());

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.adapter_list_view, users);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Touch", "Touched the screen");
                Log.i("Position in list", Integer.toString(position));
                FragmentManager fm = getSupportFragmentManager();
                fragment = fm.findFragmentByTag("Album Fragment");
                if (fragment == null) {
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new AlbumFragment(position);
                    //android.R.id.content gives you the root element of a view without using its name etc
                    ft.add(android.R.id.content,fragment,"Album Fragment");
                    //AlbumAdapter albumAdapter = new AlbumAdapter();
                    //ListView albumListView = findViewById(R.id.listView1);
                    //albumListView.setAdapter(albumAdapter);
                    ft.commit();
                    //mListView.setVisibility(View.INVISIBLE);
                    TextView title1 = findViewById(R.id.title1);
                    Log.i("Position is ", Integer.toString(position));
                    String titleString = "Album ID: " + Integer.toString(position + 1);
                    title.setText(titleString);

                }
            }
        });


        //if there is a touch event, print it to the console


    };

    private void getUsers() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        UserService service = retrofit.create(UserService.class);

        // Calling '/api/users/2'
        Call<List<User>> callAsync = service.getUsers();

        callAsync.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Log.i("Response", call.request().toString());

                List<User> apiResponse = response.body();


                //users = apiResponse;

                for (User user : apiResponse) {
                    users.add(user);
                }

                Log.i("Users size", "List of users is" + users.size());


                Log.i("All users", users.toString());

                //Log.i("response", apiResponse.toString());

                Log.i("my tag", "testing message");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
            }






        });
    }




}
