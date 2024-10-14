package ktService;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ObtainingTechnicalSpecifications {
    private final WebDriver driver;

    public ObtainingTechnicalSpecifications(WebDriver driver) {
        this.driver = driver;
    }

    //TODO: Что невозможно протестировать это создание нового лс
    private final By binField = By.xpath("");
    private final By phoneNumberField = By.xpath("");
    private final By addressField = By.xpath("");
    private final By chooseListAddress = By.xpath("");
    private final By homeNumberField = By.xpath("");
    private final By internetPointNumberField = By.xpath("");
    private final By internetSpeedField = By.xpath("");
    private final By tvPointNumberField = By.xpath("");
    private final By ipTelephonyPointNumberField = By.xpath("");
    private final By checkButton = By.xpath("");
    private final By chooseCompanyButton = By.xpath("");
    private final By choosePersonalAccount = By.xpath(""); //Выбор ЛС
    private final By invoiceForPayment = By.xpath("");

    @Step("Заполнение формы для ТУ")
    public void fillForm() {

    }

}
