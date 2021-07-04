package com.example.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;


@SpringBootTest
public class StubsApplicationTests {

    private static WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(5050));

/**
 * В методе setUpMockServer() происходит конфигурирование заглушек для API
 */
    @BeforeAll
    public static void setUpMockServer(){
        wireMockServer.start();
        WireMock.configureFor("localhost", 5050);

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/users/2"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("{\n" +
                                "    \"data\": {\n" +
                                "        \"id\": 2,\n" +
                                "        \"email\": \"janet.weaver@reqres.in\",\n" +
                                "        \"first_name\": \"Janet\",\n" +
                                "        \"last_name\": \"Weaver\",\n" +
                                "        \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n" +
                                "    },\n" +
                                "    \"support\": {\n" +
                                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                                "    }\n" +
                                "}")));

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/unknown/2"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("{\n" +
                                "    \"data\": {\n" +
                                "        \"id\": 2,\n" +
                                "        \"name\": \"fuchsia rose\",\n" +
                                "        \"year\": 2001,\n" +
                                "        \"color\": \"#C74375\",\n" +
                                "        \"pantone_value\": \"17-2031\"\n" +
                                "    },\n" +
                                "    \"support\": {\n" +
                                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                                "    }\n" +
                                "}")));

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/users"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("{\n" +
                                "    \"name\": \"morpheus\",\n" +
                                "    \"job\": \"leader\",\n" +
                                "    \"id\": \"349\",\n" +
                                "    \"createdAt\": \"2021-07-04T16:08:45.876Z\"\n" +
                                "}")));
    }


/**
 * В методе checkingSingleUser() происходит проверка существующего пользователя
 * Шаг 1: Отправить запрос на поиск пользователя
 *      Ожидаемый результат: Возвращается успешный ответ с информацией о пользователе
 */
    @Test
    void checkingSingleUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:5050/api/users/2")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        Assertions.assertEquals("Weaver", response.jsonPath().getString("data.last_name"));
        System.out.println(response.getBody().prettyPrint());
    }


/**
 * В методе checkingSingleResource() происходит проверка существующего ресурса
 * Шаг 1: Отправить запрос на поиск ресурса
 *      Ожидаемый результат: Возвращается успешный ответ с информацией о ресурсе
 */
    @Test
    void checkingSingleResource() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:5050/api/unknown/2")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response.jsonPath().getString("data.id"));
        Assertions.assertNotNull(response.jsonPath().getString("data.name"));
        System.out.println(response.getBody().prettyPrint());
    }


/**
 * В методе checkingUserCreation() происходит проверка создания пользователя
 * Шаг 1: Отправить запрос на создание нового пользователя
 *      Ожидаемый результат: Возвращается ответ об успешном создании нового пользователя
 */
    @Test
    void checkingCreateUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .post("http://localhost:5050/api/users")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response.jsonPath().getString("id"));
        Assertions.assertNotNull(response.jsonPath().getString("name"));
        System.out.println(response.getBody().prettyPrint());
    }


/**
 * В методе tearDownMockServer() происхдит остановка wireMockServer
 */
    @AfterAll
    public static void tearDownMockServer(){
        wireMockServer.stop();
    }
}
