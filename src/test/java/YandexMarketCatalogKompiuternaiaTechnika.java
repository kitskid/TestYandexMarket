import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YandexMarketCatalogKompiuternaiaTechnika {
    private WebDriver driver;

    public YandexMarketCatalogKompiuternaiaTechnika(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnCatalogKompiuternaiaTeknika() {

        WebElement refOnCatalogLaptop = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Ноутбуки']")));
        refOnCatalogLaptop.click();

    }
    public boolean atPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//title"), "innerText", "Компьютерная техника — купить на Яндекс Маркете"));
        if(driver.getTitle().equals("Компьютерная техника — купить на Яндекс Маркете")){
            return true;
        } else {
            return false;
        }
    }
}



