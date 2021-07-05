package com.example.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserFragment extends Fragment {
    private Context mContext;
    private View view;
    private ArrayList<User> users = new ArrayList<User>();
    private final String baseUrl = "https://jsonplaceholder.typicode.com/";
    private Fragment fragment;

    public UserFragment(Context context) {
        this.mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.user_list, container, false);
        //TextView title = view.findViewById(R.id.title)
        getUsers();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Users list", "The length of the list of Users is" + users.size());
        ListView userInfo = view.findViewById(R.id.userList);
        UserAdapter userAdapter = new UserAdapter(getActivity(), R.layout.user_info, users);
        userInfo.setAdapter(userAdapter);
        userInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                view.setVisibility(View.INVISIBLE);
                Log.i("Touch", "Touched the screen");
                Log.i("Position in list", Integer.toString(position));
                FragmentManager fm = getFragmentManager();
                fragment = fm.findFragmentByTag("Album Fragment");
                if (fragment == null) {

                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new AlbumFragment(position);
                    //android.R.id.content gives you the root element of a view without using its name etc
                    ft.add(R.id.container, fragment, "Album Fragment");
                    //AlbumAdapter albumAdapter = new AlbumAdapter();
                    //ListView albumListView = findViewById(R.id.listView1);
                    //albumListView.setAdapter(albumAdapter);
                    ft.commit();
                    //mListView.setVisibility(View.INVISIBLE);
                    //TextView title = view.findViewById(R.id.title);
                    Log.i("Position is ", Integer.toString(position));
                    //String titleString = "Album ID: " + Integer.toString(position + 1);
                    //title.setText(titleString);

                }
            }
        });
        Log.d("User adapter set", "User adapter set");




        return view;
    }


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


