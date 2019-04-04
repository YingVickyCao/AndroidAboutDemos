package com.example.hades.retrofit2._1_request_method._get;

import com.example.hades.retrofit2.TestBase4GitHUb;
import com.example.hades.retrofit2.GitHubService;
import com.example.hades.retrofit2.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test extends TestBase4GitHUb {
    private static final String TAG = Test.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        /**
         * https://api.github.com/users/list
         */
        new Test().init();
    }

    @Override
    protected void request(GitHubService service) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<User> call = service.listUsers();
                Response<User> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private void response(Response<User> response) {
        if (null != response && response.isSuccessful()) {
            User user = response.body();
            if (null != user) {
                System.out.println(TAG + ",login=" + user.toString());
            }
        }
    }

}
