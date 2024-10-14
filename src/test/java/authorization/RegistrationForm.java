package authorization;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class RegistrationForm {

    private final WebDriver driver;

    //Локаторы для регистрации
    private final By registrationButton = By.xpath("//div[contains(text(),'Зарегистрироваться')]");
    private final By registrationPhoneNumberField = By.xpath("//input[@name='phone-reg']");
    private final By registrationEmailField = By.xpath("//input[@name='email']");
    private final By registrationPasswordField = By.xpath("//input[@name='password-reg']");
    private final By registrationConfirmButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");

    //Локаторы когда нужно заполнить поля смс кода и отправки
    private final By smsCodeField = By.xpath("//input[@type='tel']");

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнения формы для регистрации")
    public void fillRegistrationForm(String regPhoneNumber, String regEmail, String regPassword) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        IsmetHomePage objIsmetHomePage = new IsmetHomePage(driver);
        objIsmetHomePage.clickLogInButton();
        driver.findElement(registrationButton).click();
        driver.findElement(registrationPhoneNumberField).sendKeys(regPhoneNumber);
        driver.findElement(registrationEmailField).sendKeys(regEmail);
        driver.findElement(registrationPasswordField).sendKeys(regPassword);
        driver.findElement(registrationConfirmButton).click();
        //тут надо подумать как будем вытаскивать каждый элемент поля и кидать туда смс код
        SmsCodeFetcher smsCodeFetcher = new SmsCodeFetcher();
        String str = smsCodeFetcher.getLastSmsCode("7" + regPhoneNumber);
        String[] digits = str.split("");
        List<WebElement> smsCodeFields = driver.findElements(smsCodeField);

        if (smsCodeFields.size() != digits.length) {
            throw new IllegalArgumentException("Количество ячеек не соответствует длине SMS-кода");
        }

        for (int i = 0; i < digits.length; i++) {
            smsCodeFields.get(i).sendKeys(digits[i]);
        }
    }


}
