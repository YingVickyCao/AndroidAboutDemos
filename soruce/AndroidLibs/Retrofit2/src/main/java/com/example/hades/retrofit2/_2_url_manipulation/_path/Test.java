package com.example.hades.retrofit2._2_url_manipulation._path;

import com.example.hades.retrofit2.TestBase4GitHUb;
import com.example.hades.retrofit2.GitHubService;
import com.example.hades.retrofit2._1_request_method.Repo;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Test extends TestBase4GitHUb {

    public static void main(String[] args) throws IOException {
        new Test().init();
    }

    /**
     * https://api.github.com/users/YingVickyCao/repos
     */
    protected void request(GitHubService service) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<Repo>> call = service.listRepos(USER_NAME);
                Response<List<Repo>> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private void response(Response<List<Repo>> response) {
        if (null != response && response.isSuccessful()) {
            List<Repo> info = response.body();
            if (null != info && !info.isEmpty()) {
                Collections.sort(info);
                for (Repo repo : info) {
                    System.out.println(repo.toString());
                }
            }
        }
    }
}