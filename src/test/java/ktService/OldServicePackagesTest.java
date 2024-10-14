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

import java.time.Duration;

@RunWith(Parameterized.class)
public class OldServicePackagesTest {
    private WebDriver driver;
    private final String oldProductUrl;
    private final String orderButtonXpath;

    public OldServicePackagesTest(String oldProductUrl, String orderButtonXpath) {
        this.oldProductUrl = oldProductUrl;
        this.orderButtonXpath = orderButtonXpath;
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"//a[contains(text(), ' Облачное видеонаблюдение для бизнеса ')]", "//div[@id ='home']//a"},
                {"//a[contains(text(), ' Интернет вещей (IoT) ')]", "//div[@data-service-id ='3759']"},
                // { "//a[contains(text(), ' Защита от DDos-атак ')]", "//div[@id='seriktes-bailanysta-s-moblinoy-svazu']" },
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //TODO url ка должна переходить с мейнпеджа
        driver.get("https://www.ismet.kz/ru/katalog-uslug");
    }


    @Test
    @DisplayName("Потенциальная сделка в старом дизайне")
    @Description("Пакеты на которых не сделали новый лендинг")
    public void checkTarriifsOrder() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        CataloguePage objCataloguePage = new CataloguePage(driver);
        objCataloguePage.goToOrderPage(oldProductUrl, orderButtonXpath);
        OldServicePackagesForm oldServicePackagesForm = new OldServicePackagesForm(driver);
        oldServicePackagesForm.fillPackageForm();
        Assert.assertTrue(oldServicePackagesForm.isConfirmed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }


}
