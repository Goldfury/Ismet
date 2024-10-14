package authorization;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AuthorizationFormPage {
    private final WebDriver driver;

    public AuthorizationFormPage(WebDriver driver) {
        this.driver = driver;
    }

    //Дефолт поля авторизации
    private final By usernameField = By.xpath("//input[@id='login']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By logInFormButton = By.xpath("//div[contains(text(),'Войти')]");

    //Локаторы для одноразового кода
    private final By smsCodeAuthorizationButton = By.xpath("//div[contains(text(),'Одноразовый код')]");
    private final By phoneNumberSendCodeField = By.xpath("//input[@id='phone']");

    //Локаторы когда нужно заполнить поля смс кода и отправки
    private final By sendCodeButton = By.xpath("//div[text()='Получить код' and contains(@class, 'auth-content__btn ng-star-inserted')]");
    private final By smsCodeField = By.xpath("//input[@type='tel']");

    //Локатор в котором отображается модалка Лицевые счета
    private final By account = By.xpath("//p[contains(text(), 'Лицевые счета')]");

    @Step("Авторизация по логину паролю")
    public void fillLogInForm(String login, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        IsmetHomePage objIsmetHomePage = new IsmetHomePage(driver);
        objIsmetHomePage.clickLogInButton();
        driver.findElement(usernameField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(logInFormButton).click();
    }

    @Step("Авторизация по одноразовому коду")
    public void fillSMSAuthorizationForm(String phoneNumber) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        IsmetHomePage objIsmetHomePage = new IsmetHomePage(driver);
        objIsmetHomePage.clickLogInButton();
        driver.findElement(smsCodeAuthorizationButton).click();
        driver.findElement(phoneNumberSendCodeField).clear();
        driver.findElement(phoneNumberSendCodeField).sendKeys(phoneNumber);
        driver.findElement(sendCodeButton).click();
        SmsCodeFetcher smsCodeFetcher = new SmsCodeFetcher();
        String str = smsCodeFetcher.getLastSmsCode(phoneNumber);
        String[] digits = str.split("");
        List<WebElement> smsCodeFields = driver.findElements(smsCodeField);

        if (smsCodeFields.size() != digits.length) {
            throw new IllegalArgumentException("Количество ячеек не соответствует длине SMS-кода");
        }

        for (int i = 0; i < digits.length; i++) {
            smsCodeFields.get(i).sendKeys(digits[i]);
        }
    }

    @Step("Отображение модалки с надписью Лицевые счета для потверждения если авторизовался")
    public boolean accountId() {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(account));
        return wait.isDisplayed();
    }
}