package authorization;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class AuthorizationTest {
    private WebDriver driver;
    private final String url = "https://ismet.kz/";
    private final String phoneNumber = "77055463022";
    private final String password = "brothers87";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    @DisplayName("Авторизация по паролю")
    @Description("Проверка состоит что при авторизации он ищет элемент который отображает текст Лицевые счета")
    public void authorizationTest() {
        AuthorizationFormPage objAuthorizationForm = new AuthorizationFormPage(driver);
        objAuthorizationForm.fillLogInForm(phoneNumber, password);
        Assert.assertTrue(objAuthorizationForm.accountId());
    }

    @Test
    @DisplayName("Авторизация по смс коду")
    @Description("Поочередно вводит цифры для авторизации")
    public void authorizationBySmsCodeTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        AuthorizationFormPage objAuthorizationForm = new AuthorizationFormPage(driver);
        objAuthorizationForm.fillSMSAuthorizationForm(phoneNumber);
        Assert.assertTrue(objAuthorizationForm.accountId());
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
