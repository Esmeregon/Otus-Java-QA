import dto.NonExistentUserOutGet;
import dto.User;
import dto.UserOutPost;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.TestData;
import services.UserApi;

import static org.hamcrest.Matchers.lessThan;

public class Tests {

    private final UserApi userApi = new UserApi();
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final TestData testData = new TestData();

    private final Long id = System.currentTimeMillis();


    /**
     * В тесте checkingUser() осуществляется отправка запроса на создание нового пользователя
     * со всеми заполненными параметрами;
     * Шаг 1: Отправить запрос на создание нового пользователя со всеми заполненными параметрами
     *    Ожидаемый результат: Возвращается ответ об успешном создании нового пользователя
     */
    @Test
    @DisplayName("checking user creation")
    public void checkingUser() {

        User user = User.builder()
                .userStatus(200L)
                .email(testData.getTestData("email"))
                .firstName(testData.getTestData("firstName"))
                .id(id)
                .lastName(testData.getTestData("lastName"))
                .password(testData.getTestData("password"))
                .phone(cfg.phone())
                .username(testData.getTestData("username"))
                .build();

        Response response = userApi.createUser(user);

        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        UserOutPost userOutPost = response.as(UserOutPost.class);

        Assertions.assertEquals(id.toString(), userOutPost.getMessage());
        Assertions.assertEquals(cfg.type(), userOutPost.getType());
        Assertions.assertEquals(cfg.code(), userOutPost.getCode());
    }


    /**
     * В тесте checkingUserWithoutParam() осуществляется отправка запроса на создание нового пользователя
     * только с обязательными для заполнения параметрами;
     * Шаг 1: Отправить запрос на создание нового пользователя только с обязательными для заполнения параметрами
     *    Ожидаемый результат: Запрос только с обязательными параметрами выполнен успешно
     */
    @Test
    @DisplayName("checking user creation with required parameters")
    public void checkingUserWithoutParam() {
        User user = User.builder()
                .id(id)
                .password(testData.getTestData("password"))
                .build();

        Response response = userApi.createUser(user);

        response
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        UserOutPost userOutPost = response.as(UserOutPost.class);

        Assertions.assertEquals(id.toString(), userOutPost.getMessage());
        Assertions.assertEquals(cfg.type(), userOutPost.getType());
        Assertions.assertEquals(cfg.code(), userOutPost.getCode());
    }


    /**
     * В тесте checkingResending() осуществляется отправка запроса на создание нескольких пользователей
     * с одинаковыми параметрами;
     * Шаг 1:Отправить запрос на создание нового пользователя
     *    Ожидаемый результат: Пользователь успешно создан
     * Шаг 2: Отправить запрос на создание нового пользователя с параметрами уже существующего пользователя
     *    Ожидаемый результат: Новый пользователь с параметрами уже существующего пользователя успешно создан
     */
    @Test
    @DisplayName("user re-creation")
    public void checkingResending(){
        User user = User.builder()
                .userStatus(200L)
                .email(testData.getTestData("email"))
                .firstName(testData.getTestData("firstName"))
                .id(id)
                .lastName(testData.getTestData("lastName"))
                .password(testData.getTestData("password"))
                .phone(cfg.phone())
                .username(testData.getTestData("username"))
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

        UserOutPost firstUserOutPost = firstResponse.as(UserOutPost.class);
        UserOutPost secondUserOutPost = secondResponse.as(UserOutPost.class);

        Assertions.assertEquals(firstUserOutPost.getMessage(), secondUserOutPost.getMessage());
        Assertions.assertEquals(firstUserOutPost.getType(), secondUserOutPost.getType());
        Assertions.assertEquals(firstUserOutPost.getCode(), secondUserOutPost.getCode());
    }


    /**
     * В тесте getUserByUsername() осуществляется отправка запроса на получение информации о пользователе;
     *  Шаг 1: Отправить запрос на получение информации о пользователе по имени пользователя
     *     Ожидаемый результат: В ответе возвращается информация о найденном пользователе
     */
    @Test
    @DisplayName("get user by username")
    public void getUserByUsername(){
        Response getUser = userApi.getUser(cfg.username());

        getUser
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_OK);

        User user = getUser.as(User.class);

        Assertions.assertEquals(cfg.id(), user.getId());
        Assertions.assertEquals(cfg.username(), user.getUsername());
        Assertions.assertEquals(cfg.firstName(), user.getFirstName());
        Assertions.assertEquals(cfg.lastName(), user.getLastName());
        Assertions.assertEquals(cfg.email(), user.getEmail());
        Assertions.assertEquals(cfg.password(), user.getPassword());
        Assertions.assertEquals(cfg.phone(), user.getPhone());
        Assertions.assertEquals(cfg.userStatus(), user.getUserStatus());
    }


    /**
     * В тесте getNonExistentUser() осуществляется отправка запроса на получение информации о пользователе;
     *  Шаг 1: Отправить запрос на получение информации о пользователе по несуществующему имени пользователя
     *     Ожидаемый результат: В ответе возвращается ошибка о том, что пользователь не найден
     */
    @Test
    @DisplayName("get error \"User not found\"")
    public void getNonExistentUser(){

        Response getUser = userApi.getUser("User" + System.currentTimeMillis());

        getUser
                .then()
                .log().all()
                .time(lessThan(5000L))
                .statusCode(HttpStatus.SC_NOT_FOUND);

        NonExistentUserOutGet user = getUser.as(NonExistentUserOutGet.class);

        Assertions.assertEquals(1, user.getCode());
        Assertions.assertEquals("error", user.getType());
        Assertions.assertEquals("User not found", user.getMessage());
    }
}
