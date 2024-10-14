package ktService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CataloguePage {
    private final WebDriver driver;

    public CataloguePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By catalogueButton = By.xpath("//button[contains(text(), 'Все услуги')]");

    //Строительство сети
    private final By techUsloviyaOnline = By.xpath("//a[contains(text(), ' Технические условия онлайн ')]");
    private final By ktBecamePartner = By.xpath("//a[contains(text(), ' Cтаньте партнёром АО «Казахтелеком» ')]");

    public void showCatalogue() {
        driver.findElement(catalogueButton).click();
    }

    public void goToOrderPage(String tariffUrl, String orderButtonXpath) {
        showCatalogue();
        driver.findElement(By.xpath(tariffUrl)).click();
        driver.findElement(By.xpath(orderButtonXpath)).click();
    }
}
