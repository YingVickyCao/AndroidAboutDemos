package com.example.hades.retrofit2;

import com.example.hades.retrofit2._1_request_method._post.LoginResult;
import com.example.hades.retrofit2._1_request_method._post.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface TestService {
    // http://localhost:8888/getSum?num1=5&num2=15
    @GET("getSum")
    Call<Integer> getSum(@Query("num1") int num1, @Query("num2") int num2);

    /**
     * <pre>
     * Server:
     * http://localhost:8888/login
     * POST
     * name=test&pwd=123456
     *
     * HTML submit:
     * name=test&pwd=123456
     * </pre>
     */
    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> login(@FieldMap Map<String, String> map);

    /**
     * <pre>
     * Server:
     * http://localhost:8888/login
     * POST
     * name=test&pwd=123456
     *
     * HTML submit:
     * name=test&pwd=123456
     * </pre>
     */
    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> login(@Field("name") String name, @Field("pwd") String pwd);

    /**
     * <pre>
     * Server:
     * http://localhost:8888/login
     * POST
     * {"name":"test","pwd":"123456"}
     *
     * HTML submit:
     * name=test&pwd=123456
     * </pre>
     */
    @POST("login")
    Call<LoginResult> login(@Body User user);
}