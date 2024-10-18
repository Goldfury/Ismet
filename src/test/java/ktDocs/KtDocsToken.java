package ktDocs;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class KtDocsToken {

    @Step("Токен менеджера Астаны")
    public String getKtDocsToken() {
        RestAssured.baseURI = "https://keycloak.telecom.kz";
        KtDocsManagerBody response =
                given()
                        .header("Content-Type", "application/x-www-form-urlencoded") // Указываем тип контента
                        .formParam("client_id", "ktdocs-be")
                        .formParam("grant_type", "password")
                        .formParam("client_secret", "Epgzmx73vAyEOLqPRaJs0gI0up3Y6Y5F")
                        .formParam("username", "tyschenko.l")
                        .formParam("password", "TtLl123$$")
                        .when()
                        .post("/realms/KT/protocol/openid-connect/token")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(KtDocsManagerBody.class);
        return response.getAccess_token();
    }

    @Step("KT DOCS ADMIN TOKEN")
    public String getKtDocsAdminToken() {
        RestAssured.baseURI = "https://keycloak.telecom.kz";
        KtDocsManagerBody response =
                given()
                        .header("Content-Type", "application/x-www-form-urlencoded") // Указываем тип контента
                        .formParam("client_id", "ktdocs-be")
                        .formParam("grant_type", "password")
                        .formParam("client_secret", "Epgzmx73vAyEOLqPRaJs0gI0up3Y6Y5F")
                        .formParam("username", "techismetkz")
                        .formParam("password", "fZ8H2IYcbLq5UEOw")
                        .when()
                        .post("/realms/KT/protocol/openid-connect/token")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(KtDocsManagerBody.class);
        return response.getAccess_token();
    }

    @Test
    @DisplayName("Проверка что работает кейклоак и ответ не пустой")
    public void keycloakWorkCheck() {
        RestAssured.baseURI = "https://keycloak.telecom.kz";
        Response response =
                given()
                        .header("Content-Type", "application/x-www-form-urlencoded") // Указываем тип контента
                        .formParam("client_id", "ktdocs-be")
                        .formParam("grant_type", "password")
                        .formParam("client_secret", "Epgzmx73vAyEOLqPRaJs0gI0up3Y6Y5F")
                        .formParam("username", "techismetkz")
                        .formParam("password", "fZ8H2IYcbLq5UEOw")
                        .when()
                        .post("/realms/KT/protocol/openid-connect/token");
        response
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("access_token",notNullValue());
    }
}
