package com.example.hades.retrofit2;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public abstract class TestBase4GitHUb {
    private String BASE_URL = "https://api.github.com/";
    protected final static String USER_NAME = "YingVickyCao";

    protected void init() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        request(service);
    }

    protected abstract void request(GitHubService service) throws IOException;
}
