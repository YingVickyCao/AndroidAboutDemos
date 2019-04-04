package com.example.hades.retrofit2;

import com.example.hades.retrofit2._1_request_method.Repo;
import com.example.hades.retrofit2.example.ChunkingConverter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * https://developer.github.com/v3
 */
public interface GitHubService {
    // https://api.github.com/users/YingVickyCao/repos
    @GET("users/{login}/repos")
    Call<List<Repo>> listRepos(@Path("login") String user);

    // https://api.github.com/users/YingVickyCao/repos?page=2&per_page=2&id=60006312
    @GET("users/{login}/repos?page=2&per_page=2&id=60006312")
    Call<List<Repo>> listRepos(@Path("login") String user, @Query("page") int page, @Query("per_page") int per_page, @Query("id") long id);

    // https://api.github.com/users/YingVickyCao/repos?page=2&per_page=2&id=60006312
    @GET("users/{login}/repos")
    Call<List<Repo>> listRepos(@Path("login") String user, @QueryMap Map<String, Integer> map);

    // https://api.github.com/users/list
    @GET("users/list")
    Call<User> listUsers();

    // https://api.github.com/users/list?sort=asc
    @GET("users/list?sort=desc")
    Call<User> listUsersWithSpecifyParametersInURL();

    /**
     * https://api.github.com/users?since=135
     * Ref:
     * https://developer.github.com/v3/users/#get-a-single-user
     */
    @GET("users")
    Call<List<User>> listUsersSinceID(@Query("since") int id);

    // https://developer.github.com/v3/issues/#create-an-issue
    // https://www.jianshu.com/p/a0c7d0482415
//    @POST("/repos/{login}/{repo}/issues")
//    @POST("/YingVickyCao/GitTest/issues")
    @POST("repos/YingVickyCao/GitTest/issues")
//    @POST("repos/{login}/{repo}/issues")
//    Call<ResponseIssue> createIssue(@Path("login") String login, @Path("repo") String repo, @Body Issue issue);
//    Call<ResponseIssue> createIssue(@Path("login") String login, @Path("repo") String repo, @Body Issue issue);
//    Call<ResponseBody> createIssue(@Body Repo repo);
    Call<Integer> createIssue(@Body Repo repo);
}