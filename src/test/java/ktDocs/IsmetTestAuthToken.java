package ktDocs;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class IsmetTestAuthToken {

    IsmetAuthorizationBody ismetAuthorizationBody = new IsmetAuthorizationBody("konyskairova@gmail.com", "brothers87","","","ru");

    @Test
    public void getToken(){
        RestAssured.baseURI = "https://www.test.i-smet.kz";
        IsmetAuthorizationToken response =
                given()
                        .header("Content-type", "application/json")
                        .body(ismetAuthorizationBody)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(IsmetAuthorizationToken.class);
        System.out.println(response.getAccess_token());
    }


}
