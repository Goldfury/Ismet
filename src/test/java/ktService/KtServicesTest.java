package ktService;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class KtServicesTest {

    private WebDriver driver;
    private final String url = "https://www.ismet.kz/ru/katalog-uslug";
    private final String name = "Тест тестов";
    private final String phoneNumber = "7055463022";
    private final String email = "akylbek.testspace@gmail.com";
    private final String reason = "Тестовый";
    private final String bin = "170540018519";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
    }


    @Test
    @DisplayName("Сервис починки интернета")
    @Description("Сервис починки интернета, но какого то хуя не всегда inputы находятся")
    public void repairInternetTest() {
        InternetRepairForm objInternetRepairForm = new InternetRepairForm(driver);
        objInternetRepairForm.fillRepairForm(name, phoneNumber, email, reason);
        Assert.assertTrue("Окно подтверждения не открылось", objInternetRepairForm.isConfirmed());
    }


    @Test
    @DisplayName("Сервис заказа доп роутера")
    @Description("Сервис заказа доп роутера, но какого то хуя не всегда inputы находятся")
    public void additionalRouterOrderTest() {
        AdditionalRouterOrderForm objAdditionalRouterOrderForm = new AdditionalRouterOrderForm(driver);
        objAdditionalRouterOrderForm.fillAdditionalRouterOrderForm(bin, phoneNumber);
        Assert.assertTrue("Заявка не принята", objAdditionalRouterOrderForm.isConfirmed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
