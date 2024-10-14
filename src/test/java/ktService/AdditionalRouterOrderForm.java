package ktService;

import authorization.SmsCodeFetcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdditionalRouterOrderForm {
    private final WebDriver driver;

    private final By connectInternetButton = By.xpath("//*[contains(text(), 'Подключить интернет')]");

    private final By modemFormOpenButton = By.xpath("//div[@class='modem-banner-text-bottom']");
    private final By binField = By.xpath("//*[@id='bin-iin']/input");
    private final By phoneNumber = By.xpath("//*[@id='phone']/input");
    private final By submitButton = By.xpath("//button[@type='submit' and @class ='btn btn-md primary-btn-color']");

    private final By smsCodeField = By.xpath("//*[@id='code']/input");

    private final By confirmedModule = By.xpath("//div[@class='title-success-order ng-star-inserted']");

    public AdditionalRouterOrderForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы заказа доп роутера")
    public void fillAdditionalRouterOrderForm(String bin, String phone) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(connectInternetButton));
        driver.findElement(connectInternetButton).click();
        driver.findElement(modemFormOpenButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(binField));
        element.click();
        element.sendKeys(bin);
        driver.findElement(phoneNumber).click();
        driver.findElement(phoneNumber).sendKeys(phone);
        driver.findElement(submitButton).click();
        SmsCodeFetcher smsCodeFetcher = new SmsCodeFetcher();
        String smsCode = smsCodeFetcher.getLastSmsCode("77055463022");
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(smsCodeField));
        element2.sendKeys(smsCode);
    }

    @Step("Проверка отображения окна подтверждения заявки")
    public boolean isConfirmed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmedModule));
            return driver.findElement(confirmedModule).isDisplayed();

        } catch (TimeoutException e) {
            System.out.println("Подтверждение не было найдено.");
            return false;
        }
    }
}
