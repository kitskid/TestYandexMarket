import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

public class YandexMarketCatalogNoutbuki {
    private WebDriver driver;
    private String manufacturer;

    public YandexMarketCatalogNoutbuki(WebDriver driver) {
        this.driver = driver;
    }
    public void setOptionsForSampleOfLaptops(int from, int to, String manufacturer) {

        this.manufacturer = manufacturer;
        String manufacturerFormatForXpath = "//input[@class=\"_24XUl\"][@value=\""+ manufacturer + "\"]";
        By xpathManufacturer = By.xpath(manufacturerFormatForXpath);

        WebElement buttonAllFilters = driver.findElement(By.xpath("//button[@class='_2AMPZ _1N_0H _1ghok']"));
        buttonAllFilters.click();
        WebElement inputFrom = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\"_2xtC2\"]")));
        inputFrom.sendKeys(String.valueOf(from));
        WebElement inputTo = driver.findElement(By.xpath("//input[@class=\"_2xtC2\"][@value=\"\"]"));
        inputTo.sendKeys(String.valueOf(to));

        WebElement factoryElement = driver.findElement(xpathManufacturer);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", factoryElement);

        WebElement buttonResult = driver.findElement(By.xpath("//a[@class=\"_2qvOO _3qN-v _1Rc6L\"]"));
        buttonResult.click();
    }

    public void checkSampleOfLaptops() {
        String title;
        String reference = "Ноутбук " + manufacturer;
        int count = 0;
        List<WebElement> articlesTest = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@class=\"_2f75n _24Q6d cia-cs\"]")));
        System.out.println(articlesTest.size());
        List<WebElement> articles = driver.findElements(By.xpath("//a[@class=\"_2f75n _24Q6d cia-cs\"]"));
        for(WebElement article: articles) {
            title = article.getAttribute("title");
            System.out.println(title);
            if(title.toLowerCase().contains(reference.toLowerCase())){
                count++;
            } else {
                System.out.println("Данный товар (в выборке стоит на " + articles.indexOf(article) + " позиции) не подходит под описание выборки: " + title);
            }
        }
        if(count == articles.size() && count != 0) {
            System.out.println("Товары попавшие в выборку являются ноутбуками " + manufacturer);
        } else if (articles.size() == 0){
            System.out.println("Товаров попадающих в границы заданного диапазона не найдено");
            exit(0);
        } else {
            System.out.println("были обнаружены ошибки в выборке");
        }

        int countForPrice = 0;
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='_3NaXx _33ZFz _2m5MZ']/child::span/span[1]"));
        for(WebElement price: prices) {
            title = price.getAttribute("innerText");
            System.out.println(title);
            int priceInt = parseInt(title.replaceAll("\\s+",""));
            if (priceInt >= 25000 && priceInt <= 30000 ){
                countForPrice++;
            } else {
                System.out.println("Данный товар (в выборке стоит на " + prices.indexOf(price) + " позиции) не подходит под заданный диапазон цены: " + title);
            }
        }
        if(countForPrice == prices.size()){
            System.out.println("Товары попавшие в выборку по стоимости укладываются в заданный диапазон цены");
        } else {
            System.out.println("были обнаружены ошибки в выборке");
        }
    }
    public boolean atPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.attributeToBe(By.xpath("//title"), "innerText", "Ноутбуки — купить по низкой цене с быстрой доставкой на Яндекс Маркете"));
            if(driver.getTitle().equals("Ноутбуки — купить по низкой цене с быстрой доставкой на Яндекс Маркете")){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.attributeToBe(By.xpath("//title"), "innerText", "Ноутбуки — купить по низкой цене на Яндекс Маркете"));
                if(driver.getTitle().equals("Ноутбуки — купить по низкой цене на Яндекс Маркете")){
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Страница имеет некорректный заголовок");
                return false;
            }

        }

    }
}


