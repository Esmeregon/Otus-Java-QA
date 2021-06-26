package services;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {

    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    private final RequestSpecification spec;
    private static final String USER = "/user";

    public UserApi() {
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }


    /**
     * В методе createUser() содержится api для создания нового пользователя
     * @param user Объект, сощдержащий информацию о пользователе
     * @return POST-запрос на создание нового пользователя
     */
    public Response createUser(User user){
        return
                given(spec)
                        .with()
                        .body(user)
                        .log().all()
                        .when()
                        .post(USER);
    }


    /**
     * В методе getUser() содержится api для получения детальной информации о пользователе
     * @param userName имя пользователя
     * @return GET-запрос на получение информации о пользователе
     */
    public Response getUser(String userName){
        return
                given(spec)
                        .when()
                        .get(USER + "/" + userName);
    }
}
