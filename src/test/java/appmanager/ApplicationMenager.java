package appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

/**
 * Created by Tirex on 21.12.2016.
 */
public class ApplicationMenager {
    public WebDriver driver;
    public WebDriverWait wait;
    public CardPageHelper cardPageHelper;
    public AdminPageHelper adminPageHelper;
    public MainPageHelper mainPageHelper;

    public void init() throws InterruptedException {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        //Старая схема запуска FireFox
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        //driver = new FirefoxDriver(caps);

        //driver = new InternetExplorerDriver();

        //Старая схема запуска FireFox  c указанием пути к установленному FireFox ESR 45.5.0
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        //driver =new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR45\\firefox.exe")),new FirefoxProfile(),caps);

        //Новая схема запуска FireFox  c указанием пути к установленному FireFox Nightly
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, true);
        //driver =new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Nightly\\firefox.exe")),new FirefoxProfile(),caps);


        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
        mainPageHelper = new MainPageHelper(driver, wait);
        adminPageHelper = new AdminPageHelper(driver, wait);
        cardPageHelper = new CardPageHelper(driver, wait);
    }

    public void stop() {
        driver.quit();
        driver = null;
    }

    public Object gRandomName() {

        UUID id = UUID.randomUUID();
        String f1 = id.toString().replace("-", "");
        System.out.println(f1);
        char[] symbols = new char[f1.length()];
        int i = 0;
        for (char letter : f1.toCharArray()) {
            symbols[i] = letter;
            i++;
        }

        int c=8;
        String l = new String();
        for (i = 0; i <= c; i++) {
            l = l + String.valueOf(symbols[i]);
        }

        return l;

    }

    public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        JavascriptExecutor.class.cast(driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public MainPageHelper getMainPageHelper() {
        return mainPageHelper;
    }

    public AdminPageHelper getAdminPageHelper() {
        return adminPageHelper;
    }

    public CardPageHelper getCardPageHelper() {
        return cardPageHelper;
    }
}
