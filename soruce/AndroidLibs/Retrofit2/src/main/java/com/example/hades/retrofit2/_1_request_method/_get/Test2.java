package com.example.hades.retrofit2._1_request_method._get;

import com.example.hades.retrofit2.TestBase4Local;
import com.example.hades.retrofit2.TestService;
import com.example.hades.retrofit2.User;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Test2 extends TestBase4Local {
    private static final String TAG = Test2.class.getSimpleName();

    public static void main(String[] args) throws IOException {
        /**
         * http://localhost:8888/getSum?num1=5&num2=15
         */
        new Test2().init();
    }

    @Override
    protected void request(TestService service) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<Integer> call = service.getSum(5, 15);
                Response<Integer> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response(response);
            }
        }).start();
    }

    private void response(Response<Integer> response) {
        if (null != response && response.isSuccessful()) {
            Integer result = response.body();
            if (null != result) {
                System.out.println(TAG + ",result=" + result.toString());
            }
        }
    }

}
