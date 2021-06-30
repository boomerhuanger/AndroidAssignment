package com.example.helloworld;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface UserService {

    @GET("/users/")
    public Call<List<User>> getUsers();

    @GET("/photos/")
    public Call<List<Album>> getPhotos();

}
