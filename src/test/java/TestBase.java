import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

/**
 * Created by Tirex on 16.11.2016.
 */
public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() throws Exception  {
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


    }

    @After
    public void tearDown(){
        driver.quit();
        driver = null;
    }


    protected void doLiginByAdmin() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected Object gRandomName() {

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


}
