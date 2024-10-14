package authorization;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class RegistrationTest {
    private WebDriver driver;
    private final String url = "https://ismet.kz/";
    private final String phoneNumber = "7089214128";
    private final String password = "brothers87";
    private final String email = "konyskairova@gmail.com";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    @DisplayName("Создание учетки и удаление учетки через Delete запрос")
    public void checkRegAndDeleteTest() {
        RegistrationForm registrationForm = new RegistrationForm(driver);
        registrationForm.fillRegistrationForm(phoneNumber, email, password);
        try {
            deleteAccount();
        } catch (NullPointerException e) {
            System.out.println("Учетка не создалась");
        }
    }

    @Step("Удаление учетки по почте")
    public void deleteAccount() {
        RestAssured.baseURI = "https://idp.ismet.kz";
        UserIdFetcher userIdFetcher = new UserIdFetcher();
        String id = userIdFetcher.getUserIdByEmail(email);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(getAdminToken())
                        .when()
                        .delete("/auth/admin/realms/ocp/users/" + id);
        response.then()
                .and()
                .statusCode(204);
    }

    @Step("Создает токен который используется для авторизации в методе deleteAccount")
    public String getAdminToken() {
        RestAssured.baseURI = "https://idp.ismet.kz";
        String bearerToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiRVQydEc0R29fQW9BVlJId282WlE4M3h0akJlTUlJYWpQUXRZcGwzMnp3In0.eyJqdGkiOiIxYzA1MjJjOC1iZjU1LTQ2ZTAtYTM0NC00YWIzMzk2ZDAwYjEiLCJleHAiOjE3MTM3ODg5NjUsIm5iZiI6MCwiaWF0IjoxNzEzNzg4OTA1LCJpc3MiOiJodHRwczovL2lkcC5pc21ldC5rei9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhZG1pbi1jbGkiLCJzdWIiOiJjZGY4MjZkNC1mMDg5LTQ4YjgtOGI4MC0wOTM0MTQxMTQ3ZmEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhZG1pbi1jbGkiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiIzNjM4ZGU1My0yZWI2LTQ5NmQtYjgzMC01OTZmN2E0NzdlYWQiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbXSwicmVzb3VyY2VfYWNjZXNzIjp7fSwibmFtZSI6IkFkbWluIEFkbWluIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYWRtaW4yIiwiZ2l2ZW5fbmFtZSI6IkFkbWluIiwiZmFtaWx5X25hbWUiOiJBZG1pbiIsImVtYWlsIjoiZGFuaWtAc2Zlay5reiJ9.AwHgQ7KZTK1sEwBJQtxZBsECUGgqyxBWLDUSi8woWkPwO3dohY2SHeNpTs5NjPf0jfMTWpqH5_GyuauvEP10KZnM-oe6G26EUBP7PJWDAQvc5EuqrhW11_1rNNpTIeFt5mbz4puLTeF-qVjb2bXsAKV8A9GW_gDUmYtXhfd40rhXGICwn1YLHi3DAGoSbSDp35tDCCg7tul9vlgciezEmmOpgCKubPmq2C3BzsO07Iu78Yl8R92oOffJffsrpNMdl3i2StEuTkVNb5AkumSxXuCkcWoQC6a8f5R_4XQZMzqcMUCIAOSfBVJXfEJB-_ggt_7WIUdRhy5KG9YOFv6PTg";
        AdminToken response =
                given()
                        .header("Authorization", "Bearer " + bearerToken)
                        .header("Content-Type", "application/x-www-form-urlencoded") // Указываем тип контента
                        .header("User-Agent", "PostmanRuntime/7.42.0") // Замените на нужный User-Agent
                        .header("Accept", "*/*") // Указываем Accept заголовок
                        .header("Cache-Control", "no-cache") // Указываем Cache-Control
                        .header("Connection", "keep-alive") // Указываем Connection
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .formParam("client_id", "admin-cli")
                        .formParam("grant_type", "password")
                        .formParam("username", "admin2")
                        .formParam("password", "m!BU*K40N8")
                        .when()
                        .post("/auth/realms/master/protocol/openid-connect/token")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(AdminToken.class);
        return response.getAccess_token();
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
