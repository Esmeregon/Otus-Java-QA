package com.example.apihelpers;

import com.example.apihelpers.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;

import java.io.IOException;


public class RetrofitApplicationTests {

    @Test
    void retrofitTest() throws IOException {

        Response<User> response;

        APIInterface service = APIClientHelper.getClient().create(APIInterface.class);

        response = service.getUser().execute();

        if (response.isSuccessful()){
            System.out.println("Response Success");
            assert response.body() != null;
            System.out.println(response.body().getData());
            System.out.println(response.body());
        } else {
            System.out.println("Response Error");
        }

    }
}
