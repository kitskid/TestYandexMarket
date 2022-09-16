import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//для запуска теста в браузере Chrome (Версия: 105.0.5195.102)
//необходимо подключить соответствующий драйвер (https://chromedriver.chromium.org/downloads)
//входные данные для теста можно изменять с помощью параметров метода  класса YandexMarketCatalogNoutbuki
//setOptionsForSampleOfLaptops(int from, int to, String manufacturer)
//где from - нижний порог цены, to - верхний порог цены, String manufacturer - производитель ноутбуков,
//для позитивного теста название производителя должно совпадать с представленным на сайте


public class MainTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\kitskid\\Desktop\\JAVA\\Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        YandexMarket yandexMarket = new YandexMarket(driver);
        driver.get("https://market.yandex.ru/");



           if(yandexMarket.atPage()){
               try {
                   yandexMarket.clickCatalogButton();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           } else {
               System.out.println("Попытка выполнить тест не на целевой странице 1");
           }
        YandexMarketCatalogKompiuternaiaTechnika yandexMarketCatalogKompiuternaiaTechnika = new YandexMarketCatalogKompiuternaiaTechnika(driver);

        if(yandexMarketCatalogKompiuternaiaTechnika.atPage()){
              try {
                   yandexMarketCatalogKompiuternaiaTechnika.clickOnCatalogKompiuternaiaTeknika();
               } catch (Exception e) {
                   e.printStackTrace();
               }
        } else {
               System.out.println("Попытка выполнить тест не на целевой странице2");
        }
        YandexMarketCatalogNoutbuki yandexMarketCatalogNoutbuki = new YandexMarketCatalogNoutbuki(driver);

        if(yandexMarketCatalogNoutbuki.atPage()) {
           try {
               yandexMarketCatalogNoutbuki.setOptionsForSampleOfLaptops(25000,30000, "Lenovo");
               yandexMarketCatalogNoutbuki.checkSampleOfLaptops();
           } catch (Exception e) {
               e.printStackTrace();
           }
        } else {
           System.out.println("Попытка выполнить тест не на целевой странице");
        }

        driver.quit();
    }
}
