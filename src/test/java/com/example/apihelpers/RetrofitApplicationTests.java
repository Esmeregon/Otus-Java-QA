package com.example.apihelpers;

import com.example.apihelpers.pojo.createSingleUser.CreateUserRequest;
import com.example.apihelpers.pojo.createSingleUser.CreateUserResponse;
import com.example.apihelpers.pojo.singleResource.Resource;
import com.example.apihelpers.pojo.singleUser.User;
import com.example.calculator.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;


public class RetrofitApplicationTests {

    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    APIInterface service = APIClientHelper.getClient().create(APIInterface.class);

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String job = RandomStringUtils.randomAlphanumeric(10);


/**
 * В методе checkingSingleUser() происходит проверка существующего пользователя
 * Шаг 1: Отправить запрос на поиск пользователя
 *      Ожидаемый результат: Возвращается успешный ответ с информацией о пользователе
 */
    @Test
    void checkingSingleUser() throws IOException {

        Response<User> response;
        User userResponse;

        response = service.getUser().execute();

        userResponse = response.body();

        if (response.isSuccessful()){
            assert userResponse != null;
            Assertions.assertNotNull(userResponse.getUserData().getId());
            Assertions.assertNotNull(userResponse.getUserData().getEmail());
            Assertions.assertEquals(cfg.userFirstName(), userResponse.getUserData().getFirstName());
            Assertions.assertEquals(cfg.userLastName(), userResponse.getUserData().getLastName());
            Assertions.assertNotNull(userResponse.getUserData().getAvatar());
            Assertions.assertNotNull(userResponse.getSupport().getUrl());
            Assertions.assertNotNull(userResponse.getSupport().getText());
        } else {
            System.out.println(response.headers());
        }
        System.out.println(response.body());
    }


/**
 * В методе checkingSingleResource() происходит проверка существующего ресурса
 * Шаг 1: Отправить запрос на поиск ресурса
 *      Ожидаемый результат: Возвращается успешный ответ с информацией о ресурсе
 */
    @Test
    void checkingSingleResource() throws IOException {

        Response<Resource> response;
        Resource resourceResponse;

        response = service.getResource().execute();

        resourceResponse = response.body();

        if (response.isSuccessful()){
            assert resourceResponse != null;
            Assertions.assertNotNull(resourceResponse.getResourceData().getId());
            Assertions.assertEquals(cfg.resourceName(), resourceResponse.getResourceData().getName());
            Assertions.assertEquals(cfg.resourceYear(), resourceResponse.getResourceData().getYear());
            Assertions.assertNotNull(resourceResponse.getResourceData().getColor());
            Assertions.assertNotNull(resourceResponse.getResourceData().getPantoneValue());
            Assertions.assertNotNull(resourceResponse.getSupport().getUrl());
            Assertions.assertNotNull(resourceResponse.getSupport().getText());
        } else {
            System.out.println(response.headers());
        }
        System.out.println(response.body());
    }


/**
 * В методе checkingUserCreation() происходит проверка создания пользователя
 * Шаг 1: Отправить запрос на создание нового пользователя
 *      Ожидаемый результат: Возвращается ответ об успешном создании нового пользователя
 */
    @Test
    void checkingUserCreation() throws IOException {
        CreateUserRequest requestNewUserData = new CreateUserRequest();
        Response<CreateUserResponse> responseCreateUser;
        CreateUserResponse createUserResponse;

        requestNewUserData.setName(name);
        requestNewUserData.setJob(job);

        responseCreateUser = service.createUser(requestNewUserData).execute();

        createUserResponse = responseCreateUser.body();
        assert createUserResponse != null;

        if(responseCreateUser.isSuccessful()){
            Assertions.assertEquals(name, createUserResponse.getName());
            Assertions.assertEquals(job, createUserResponse.getJob());
            Assertions.assertNotNull(createUserResponse.getId());
            Assertions.assertNotNull(createUserResponse.getCreatedAt());
        }else{
            System.out.println(responseCreateUser.headers());
        }
        System.out.println(responseCreateUser.body());
    }
}
