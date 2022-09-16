import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class YandexMarket {

    private WebDriver driver;

    public YandexMarket(WebDriver driver) {
        this.driver = driver;

    }
    public void clickCatalogButton() {

        WebElement button = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.id("catalogPopupButton")));
        button.click();

        WebElement refOnCatalogTechnika = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href=\"/catalog--kompiuternaia-tekhnika/54425\" ]/parent::*")));
        refOnCatalogTechnika.click();

    }
    public boolean atPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//title"), "innerText", "Интернет-магазин Яндекс.Маркет — покупки с быстрой доставкой"));
        if(driver.getTitle().equals("Интернет-магазин Яндекс.Маркет — покупки с быстрой доставкой")){
            return true;
        } else {
            return false;
        }
    }
}
