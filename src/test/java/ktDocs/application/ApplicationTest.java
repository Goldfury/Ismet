package ktDocs.application;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ktDocs.KtDocsToken;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ApplicationTest {

    KtDocsToken ktDocsToken;

    List<AdditionalContent> additionalContent = new ArrayList<AdditionalContent>(
    );
    AdditionalContent additionalContent10 = new AdditionalContent(10, "Город Астана, улица Кунаева 35/1, квартира 111");
    AdditionalContent additionalContent11 = new AdditionalContent(11, "Город Астана, улица Сейфуллина 40, квартира 3");
    AdditionalContent additionalContent12 = new AdditionalContent(12, "10.10.2024");
    AdditionalContent additionalContent13 = new AdditionalContent(13, "15.10.2024");
    AdditionalContent additionalContent14 = new AdditionalContent(14, "Топ 10 вопросов от расула");

    public  List<AdditionalContent> addAddionalContent() {
        additionalContent.add(additionalContent10);
        additionalContent.add(additionalContent11);
        additionalContent.add(additionalContent12);
        additionalContent.add(additionalContent13);
        additionalContent.add(additionalContent14);
        return  additionalContent;

    }

    CustomerData customerData = new CustomerData(
            11456664,
            "Город Астана, улица Кунаева 35/1, квартира 111",
            "Город Астана, улица Кунаева 35/1, квартира 111",
            11456664,
            1,
            "Астана",
            "ИП Иванов Иван Иванович",
            "ИП Иванов Иван Иванович",
            "960812351404"
    );

    List<FeedbackAddress> feedbackAddresses = new ArrayList<FeedbackAddress>();
    FeedbackAddress feedbackAddress = new FeedbackAddress("87077877070", 1);
    public void addFeedback(){
        feedbackAddresses.add(feedbackAddress);
    }

    ApplicationBody applicationBody = new ApplicationBody(
            additionalContent,
            20927,
            customerData,
            feedbackAddresses,
            1,
            1,
            2
    );



    @Before
    public void setUp() {
        ktDocsToken = new KtDocsToken();
        RestAssured.baseURI = "https://ktdocs.i-smet.kz/api/application/v1";
    }

    @Test
    public void createApplication() {
        addAddionalContent();
        addFeedback();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .header("Authorization", "Bearer " + ktDocsToken.getKtDocsAdminToken())
                        .and()
                        .body(applicationBody)
                        .when()
                        .post("/abonent/generate");

        System.out.println(response.asString());
        response
                .then()
                .statusCode(200)
                .and()
                .body("response",notNullValue());
    }


}
