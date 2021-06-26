package citrus.tests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;



public class FirstTest extends TestNGCitrusTestRunner{

    @Autowired
    private HttpClient restClient;
    private TestContext context;



    /**
     * В тесте getSingleUser() происходит отправка запроса на получение информации о пользователе
     * Осуществляется проверка контракта получения существующего пользователя
     */
    @Test(description = "GET SINGLE USER")
    @CitrusTest
    public void getSingleUser(){
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users/2")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "   \"data\":{\n" +
                        "      \"id\": 2,\n" +
                        "      \"email\":\"janet.weaver@reqres.in\",\n" +
                        "      \"first_name\":\"Janet\",\n" +
                        "      \"last_name\":\"Weaver\",\n" +
                        "      \"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"\n" +
                        "   },\n" +
                        "   \"support\":{\n" +
                        "      \"url\":\"https://reqres.in/#support-heading\",\n" +
                        "      \"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                        "   }\n" +
                        "}")
        );

    }


    /**
     * В тесте getSingleUserNotFound() происходит отправка запроса на получение информации о несуществующем пользователе
     * Осуществляется проверка контракта получения несуществующего пользователя
     */
    @Test(description = "GET SINGLE USER NOT FOUND")
    @CitrusTest
    public void getSingleUserNotFound(){
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users/23")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{}")
        );
    }


    /**
     * В тесте getListUsers() происходит отправка запроса на получение информации о списке существующих пользователей
     * Осуществляется проверка контракта получения списка существующих пользователей
     */
    @Test(description = "GET LIST USERS")
    @CitrusTest
    public void getListUsers(){
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("users?page=2")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
                        "    \"page\": 2,\n" +
                        "    \"per_page\": 6,\n" +
                        "    \"total\": 12,\n" +
                        "    \"total_pages\": 2,\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"id\": 7,\n" +
                        "            \"email\": \"michael.lawson@reqres.in\",\n" +
                        "            \"first_name\": \"Michael\",\n" +
                        "            \"last_name\": \"Lawson\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 8,\n" +
                        "            \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                        "            \"first_name\": \"Lindsay\",\n" +
                        "            \"last_name\": \"Ferguson\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 9,\n" +
                        "            \"email\": \"tobias.funke@reqres.in\",\n" +
                        "            \"first_name\": \"Tobias\",\n" +
                        "            \"last_name\": \"Funke\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/9-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 10,\n" +
                        "            \"email\": \"byron.fields@reqres.in\",\n" +
                        "            \"first_name\": \"Byron\",\n" +
                        "            \"last_name\": \"Fields\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/10-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 11,\n" +
                        "            \"email\": \"george.edwards@reqres.in\",\n" +
                        "            \"first_name\": \"George\",\n" +
                        "            \"last_name\": \"Edwards\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 12,\n" +
                        "            \"email\": \"rachel.howell@reqres.in\",\n" +
                        "            \"first_name\": \"Rachel\",\n" +
                        "            \"last_name\": \"Howell\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/12-image.jpg\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"support\": {\n" +
                        "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                        "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                        "    }\n" +
                        "}")
        );

    }


    /**
     * В тесте getSingleResource() происходит отправка запроса на получение информации о ресурсе
     * Осуществляется проверка контракта получения существующего ресурса
     */
    @Test(description = "GET SINGLE <RESOURCE>")
    @CitrusTest
    public void getSingleResource(){
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("unknown/2")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{\n" +
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
                        "}")
        );
    }


    /**
     * В тесте getSingleResourceNotFound() происходит отправка запроса на получение информации о несуществующем ресурсе
     * Осуществляется проверка контракта получения несуществующего ресурса
     */
    @Test(description = "GET SINGLE <RESOURCE> NOT FOUND")
    @CitrusTest
    public void getSingleResourceNotFound(){
        this.context = citrus.createTestContext();

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .send()
                .get("unknown/23")
        );

        http(httpActionBuilder -> httpActionBuilder
                .client(restClient)
                .receive()
                .response()
                .messageType(MessageType.JSON)
                .payload("{}")
        );
    }
}
