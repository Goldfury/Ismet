package authorization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IsmetHomePage {
    private final WebDriver driver;

    public IsmetHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By logInButton = By.xpath("//button[contains(text(),'Войти')]");


    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
}
