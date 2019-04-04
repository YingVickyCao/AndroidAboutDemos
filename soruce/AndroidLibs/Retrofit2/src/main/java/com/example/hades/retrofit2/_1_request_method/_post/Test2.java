package com.example.hades.retrofit2._1_request_method._post;

import com.example.hades.retrofit2.TestBase4Local;
import com.example.hades.retrofit2.TestService;
import retrofit2.Call;
import retrofit2.Response;
import sun.jvm.hotspot.tools.ObjectHistogram;

import java.io.IOException;

public class Test2 extends TestBase4Local {
    private static final String TAG = Test2.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        new Test2().init();
    }

    @Override
    protected void request(TestService service) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<LoginResult> call = service.login(new User("test", "123456"));
                Response<LoginResult> loginResult = null;
                try {
                    loginResult = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(loginResult);
            }
        }).start();
    }

    private void response(Response<LoginResult> loginResult) {
        if (null != loginResult && loginResult.isSuccessful()) {
            LoginResult result = loginResult.body();
            if (null != result) {
                System.out.println(TAG + ",result=" + result.toString());
            }
        }
    }

}
