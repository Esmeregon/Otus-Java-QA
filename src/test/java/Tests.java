import dto.User;
import dto.UserOut;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.UserApi;

import static org.hamcrest.Matchers.lessThan;

public class Tests {

    private final UserApi userApi = new UserApi();
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);


    /**
     * В тесте checkingUser() осуществляется отправка запроса на создание нового пользователя
     * со всеми заполненными параметрами;
     * Осуществляется проверка ответа
     */
    @Test
    @DisplayName("checking user creation")
    public void checkingUser() {
        User user = User.builder()
                .userStatus(cfg.userStatus())
                .email(cfg.email())
                .firstName(cfg.firstName())
                .id(cfg.id())
                .lastName(cfg.lastName())
                .password(cfg.password())
                .phone(cfg.phone())
                .username(cfg.username())
                .build();

        Response response = userApi.createUser(user);

        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        Assertions.assertEquals(cfg.id().toString(), response.as(UserOut.class).getMessage());
        Assertions.assertEquals(cfg.type(), response.as(UserOut.class).getType());
        Assertions.assertEquals(cfg.code(), response.as(UserOut.class).getCode());
    }


    /**
     * В тесте checkingUserWithoutParam() осуществляется отправка запроса на создание нового пользователя
     * только с обязательными для заполнения параметрами;
     * Осуществляется проверка ответа
     */
    @Test
    @DisplayName("checking user creation with required parameters")
    public void checkingUserWithoutParam() {
        User user = User.builder()
                .id(cfg.id())
                .password(cfg.password())
                .build();

        Response response = userApi.createUser(user);

        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        Assertions.assertEquals(cfg.id().toString(), response.as(UserOut.class).getMessage());
        Assertions.assertEquals(cfg.type(), response.as(UserOut.class).getType());
        Assertions.assertEquals(cfg.code(), response.as(UserOut.class).getCode());
    }


    /**
     * В тесте checkingResending() осуществляется отправка запроса на создание нескольких пользователей
     * с одинаковыми параметрами;
     * Осуществляется проверка ответа
     */
    @Test
    @DisplayName("user re-creation ")
    public void checkingResending(){
        User user = User.builder()
                .userStatus(cfg.userStatus())
                .email(cfg.email())
                .firstName(cfg.firstName())
                .id(cfg.id())
                .lastName(cfg.lastName())
                .password(cfg.password())
                .phone(cfg.phone())
                .username(cfg.username())
                .build();

        Response firstResponse = userApi.createUser(user);
        Response secondResponse = userApi.createUser(user);

        firstResponse
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        secondResponse
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        Assertions.assertEquals(firstResponse.as(UserOut.class).getMessage(), secondResponse.as(UserOut.class).getMessage());
        Assertions.assertEquals(firstResponse.as(UserOut.class).getType(), secondResponse.as(UserOut.class).getType());
        Assertions.assertEquals(firstResponse.as(UserOut.class).getCode(), secondResponse.as(UserOut.class).getCode());
    }
}
