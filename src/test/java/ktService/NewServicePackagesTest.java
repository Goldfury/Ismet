package ktService;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;


@RunWith(Parameterized.class)
public class NewServicePackagesTest {
    private WebDriver driver;
    private final String tariffUrl;
    private final String orderButtonXpath;
    private DevTools devTools;


    public NewServicePackagesTest(String tariffUrl, String orderButtonXpath) {
        this.tariffUrl = tariffUrl;
        this.orderButtonXpath = orderButtonXpath;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"//a[contains(text(), 'Kasipker')]", "//div[@id='kasipker-bailanysta-s-moblinoy-svazu']"},
                {"//a[contains(text(), 'Nomad')]", "//div[@id='nomad-bailanysta-s-moblinoy-svazu']"},
                {"//a[contains(text(), 'Seriktes')]", "//div[@id='seriktes-bailanysta-s-moblinoy-svazu']"},
                {"//a[contains(text(), 'Tabys')]", "//div[@id='undefined']"},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();

        //TODO url ка должна переходить с мейнпеджа
        driver.get("https://www.ismet.kz/ru/katalog-uslug");

    }

    @Test
    @DisplayName("Тестирование пакетов услуг в новом дизайне")
    @Description("Проверяются новые пакеты услуг: Касипкер, Номад, Сериктес, Табыс")
    public void checkTarriifsOrder() {
        CataloguePage objCataloguePage = new CataloguePage(driver);
        objCataloguePage.goToOrderPage(tariffUrl, orderButtonXpath);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);
        objOrderFormPage.fillOrderForm();
        Assert.assertTrue(objOrderFormPage.isTariffConfirmationDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
