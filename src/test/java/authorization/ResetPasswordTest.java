package authorization;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ResetPasswordTest {

    private WebDriver driver;
    private final String phoneNumber = "77055463022";
    private final String password = "brothers87";
    private final String url = "https://ismet.kz/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    @DisplayName("Сброс пароля на исмете")
    @Description("меняет на тот же пароль что и был, но есть проверка отображается ли модальное окошка которая уведомляет изменил юзера пароль или нет")
    public void resetPassword() {
        ResetPasswordForm resetPasswordForm = new ResetPasswordForm(driver);
        resetPasswordForm.fillForgotPasswordForm(phoneNumber, password);
        Assert.assertTrue(resetPasswordForm.getChangePasswordNotificationText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
