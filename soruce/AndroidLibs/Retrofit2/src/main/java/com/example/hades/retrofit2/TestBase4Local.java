package com.example.hades.retrofit2;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public abstract class TestBase4Local {
    private String BASE_URL = "http://localhost:8888/";

    protected void init() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestService service = retrofit.create(TestService.class);
        request(service);
    }

    protected abstract void request(TestService service) throws IOException;
}
