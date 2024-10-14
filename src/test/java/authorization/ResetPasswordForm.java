package authorization;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResetPasswordForm {

    private final WebDriver driver;
    //Локаторы если забыл пароль
    private final By forgotPasswordButton = By.xpath("//div[contains(text(),'Забыли пароль?')]");
    private final By forgottenPhoneNumberField = By.xpath("//input[@id='phone-forgot']");
    private final By forgottenPasswordField = By.xpath("//input[@type='password']");
    private final By changePasswordButton = By.xpath("//button[contains(text(),'Сменить пароль')]");
    private final By changedPasswordNotification = By.xpath("//div[contains(text(),'Пароль для вашей учётной записи был успешно изменён')]");

    //Локаторы для ввода номера телефона и кнопки отправки
    private final By smsCodeField = By.xpath("//input[@type='tel']");
    private final By sendCodeButton = By.xpath("//div[text()='Получить код' and contains(@class, 'auth-content__btn ng-star-inserted')]");


    public ResetPasswordForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы для сброса пароля")
    public void fillForgotPasswordForm(String forgottenPhoneNumber, String forgottenPassword) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        IsmetHomePage objIsmetHomePage = new IsmetHomePage(driver);
        objIsmetHomePage.clickLogInButton();
        driver.findElement(forgotPasswordButton).click();
        driver.findElement(forgottenPhoneNumberField).sendKeys(forgottenPhoneNumber);
        driver.findElement(sendCodeButton).click();
        //TODO тут надо подумать как будем вытаскивать каждый элемент поля и кидать туда смс код
        SmsCodeFetcher smsCodeFetcher = new SmsCodeFetcher();
        String str = smsCodeFetcher.getLastSmsCode(forgottenPhoneNumber);
        String[] digits = str.split("");
        List<WebElement> smsCodeFields = driver.findElements(smsCodeField);
        if (smsCodeFields.size() != digits.length) {
            throw new IllegalArgumentException("Количество ячеек не соответствует длине SMS-кода");
        }
        for (int i = 0; i < digits.length; i++) {
            smsCodeFields.get(i).sendKeys(digits[i]);
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(forgottenPasswordField));
        element.sendKeys(forgottenPassword);
        driver.findElement(changePasswordButton).click();
    }

    @Step("Отображение уведомления о сбросе пароля")
    public boolean getChangePasswordNotificationText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(changedPasswordNotification));
        return element.isDisplayed();
    }
}
