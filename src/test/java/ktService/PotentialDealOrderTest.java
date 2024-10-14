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

@RunWith(Parameterized.class)
public class PotentialDealOrderTest {
    private WebDriver driver;
    private final String tariffUrl;
    private final String orderButtonXpath;

    public PotentialDealOrderTest(String tariffUrl, String orderButtonXpath) {
        this.tariffUrl = tariffUrl;
        this.orderButtonXpath = orderButtonXpath;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"//a[contains(text(), ' Интернет для бизнеса ')]", "//div[contains(@class, 'package-slide-id')][1]//div[contains(text(),'Подключить')]"},
                {"//a[contains(text(), ' Телевидение для бизнеса iD TV ')]", "//div[contains(text(), 'Подключить')]"},
                {"//a[contains(text(), ' SIP-телефония ')]", "//div[@id='undefined']"},
                {"//a[contains(text(), ' FXS')]", "//div[@id='undefined']"},
                {"//a[contains(text(), ' Универсальный номер для мобильного бизнеса ')]", "//div[@id='undefined' ]"},
                {"//a[contains(text(), ' Приватная сеть IP VPN ')]", "//div[@id='undefined' ]"},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.ismet.kz/ru/katalog-uslug");
    }

    @Test
    @DisplayName("Потенциальная сделка ")
    @Description("Простые услуги в которые входят: Интернет для бизнеса, Телевидение для бизнеса, SIP телефония, FXS, Универ. номер, IP VPN")
    public void checkTarriifsOrder() {
        CataloguePage objCataloguePage = new CataloguePage(driver);
        objCataloguePage.goToOrderPage(tariffUrl, orderButtonXpath);
        OrderFormPage objOrderFormPage = new OrderFormPage(driver);
        objOrderFormPage.fillOrderForm();
        Assert.assertTrue(objOrderFormPage.isOrderConfirmationDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
