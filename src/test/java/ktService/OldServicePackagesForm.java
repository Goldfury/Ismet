package ktService;

import authorization.SmsCodeFetcher;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OldServicePackagesForm {
    private final WebDriver driver;

    public OldServicePackagesForm(WebDriver driver) {
        this.driver = driver;
    }

    private final By binField = By.xpath("//input[@id ='biniin']");
    private final By radioButton = By.xpath("//label[contains(text(),'Самозанятый')]");
    private final By phoneNumberField = By.xpath("//input[@id='phone']");
    private final By nameField = By.xpath("//input[@id='lastName']");
    private final By emailField = By.xpath("//input[@id='email']");
    private final By regionButton = By.xpath("//button[@data-id='region-call']");
    private final By astanaRegion = By.xpath("//span[contains(text(), 'Астана')]");
    private final By confirmButton = By.xpath("//button[@type='submit']");
    private final By smsCodeField = By.xpath("//input[@name='code']");
    private final By smsCodeConfirmButton = By.xpath("//button[@type='submit' and @class='it-services']");
    private final By confirmModalBlock = By.xpath("//div[@class='success-check-callback']");

    private final String bin = "170540018519";
    private final String phoneNubmer = "7055463022";
    private final String name = "ТЕСТ не трогать";
    private final String email = "akylbek.testspace@gmail.com";

    @Step("Заполнение формы заявления на подключение услуги")
    public void fillPackageForm() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(binField).click();
        driver.findElement(binField).sendKeys(bin);
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(radioButton));
        wait.click();
        WebElement waitPhoneField = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(phoneNumberField));
        waitPhoneField.click();
        waitPhoneField.sendKeys(phoneNubmer);
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(regionButton).click();
        WebElement waitForRegion = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(astanaRegion));
        waitForRegion.click();
        driver.findElement(confirmButton).click();
        SmsCodeFetcher smsCodeFetcher = new SmsCodeFetcher();
        String smsCode = smsCodeFetcher.getLastSmsCode("7" + phoneNubmer);
        WebElement useSmsCode = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(smsCodeField));
        useSmsCode.click();
        useSmsCode.sendKeys(smsCode);
        driver.findElement(smsCodeConfirmButton).click();
    }

    @Step("Проверка отображения модального окна после оформления заказа")
    public boolean isConfirmed() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(120)).until(ExpectedConditions.elementToBeClickable(confirmModalBlock));
        return wait.isDisplayed();
    }


}
