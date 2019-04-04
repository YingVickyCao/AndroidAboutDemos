package com.example.hades.retrofit2._2_url_manipulation._querry_parameters;

import com.example.hades.retrofit2.TestBase4GitHUb;
import com.example.hades.retrofit2.GitHubService;
import com.example.hades.retrofit2._1_request_method.Repo;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test3 extends TestBase4GitHUb {

    public static void main(String[] args) throws IOException {
        new Test3().init();
    }

    /**
     * https://api.github.com/users/YingVickyCao/repos?page=2&per_page=2&id=60006312
     */
    protected void request(GitHubService service) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Integer> parmeters = new HashMap<>();
                parmeters.put("page", 2);
                parmeters.put("per_page", 2);
                parmeters.put("id", 60006312);

                Call<List<Repo>> call = service.listRepos(USER_NAME, parmeters);
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