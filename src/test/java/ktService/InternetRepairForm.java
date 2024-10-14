package ktService;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InternetRepairForm {

    private final WebDriver driver;
    private final By internetRepairButtom = By.xpath("//div[@class='catalog-info-button damage-claim']");
    private final By nameField = By.xpath("//input[@id='name']");
    private final By phoneNumberField = By.xpath("//*[@id='phone']/input");
    private final By emailField = By.xpath("//input[@id='email']");
    private final By reasonToRepairField = By.xpath("//textarea[@id='reason']");
    private final By submitbutton = By.xpath("//button[@type='submit' and @class ='btn btn-md primary-btn-color']");
    private final By confirmedModule = By.xpath("//div[@role='dialog']//span[@id='pr_id_4-label']");

    public InternetRepairForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Сервис подачи заявки на повреждение")
    public void fillRepairForm(String name, String phoneNumber, String email, String reason) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(internetRepairButtom).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(phoneNumberField).click();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(reasonToRepairField).sendKeys(reason);
        driver.findElement(submitbutton).click();
    }

    @Step("Проверка на отображения модуля подтверждения")
    public boolean isConfirmed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmedModule));
            return driver.findElement(confirmedModule).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Подтверждение не было найдено.");
            return false;
        }
    }
}
