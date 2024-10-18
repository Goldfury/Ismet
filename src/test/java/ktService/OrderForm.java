package ktService;

import authorization.SmsCodeFetcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderForm {

    private final WebDriver driver;

    public OrderForm(WebDriver driver) {
        this.driver = driver;
    }

    private final String testBin = "170540018519";
    private final String phoneNumber = "7055463022";
    private final String region = "Астана";

    private final By binField = By.xpath("//*[@id=\"bin-iin\"]/input");
    private final By phoneNumberField = By.xpath("//*[@id='phone']/input");
    private final By regionField = By.xpath("//*[@id='region']//input");
    private final By regionDropDownOption = By.xpath("//li[@role='option']");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By smsCodeField = By.xpath("//*[@id='code']/input");

    private final By tariffConfirmationText = By.xpath("//div[@class='thank-you-block-title']");
    private final By orderConfirmationText = By.xpath("//div[@class='text-success-create-cart ng-star-inserted']");

    @Step("Форма заполнения заявки")
    public void fillOrderForm() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement binFieldElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(binField));
        binFieldElement.click();
        binFieldElement.sendKeys(testBin);
        driver.findElement(phoneNumberField).click();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        driver.findElement(regionField).sendKeys(region);
        WebElement chooseRegion = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(regionDropDownOption));
        chooseRegion.click();
        driver.findElement(submitButton).click();
        SmsCodeFetcher smsCodeFetcher = new SmsCodeFetcher();
        String smsCode = smsCodeFetcher.getLastSmsCode("77055463022");
        WebElement useSmsCode = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(smsCodeField));
        useSmsCode.sendKeys(smsCode);
    }

    @Step("Проверка отображения модального окна подтверждения пакета")
    public boolean isTariffConfirmationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(tariffConfirmationText));
        return element.isDisplayed();
    }

    @Step("Проверка отображения модального окна подтверждения закзаа")
    public boolean isOrderConfirmationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orderConfirmationText));
        return element.isDisplayed();
    }


}
