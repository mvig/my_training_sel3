import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by Tirex on 16.11.2016.
 */
public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() throws Exception  {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();

        //Старая схема запуска FireFox  c указанием пути к установленному FireFox ESR 45.5.0
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        //driver =new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox ESR45\\firefox.exe")),new FirefoxProfile(),caps);

        //Новая схема запуска FireFox  c указанием пути к установленному FireFox Nightly
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, true);
        driver =new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Nightly\\firefox.exe")),new FirefoxProfile(),caps);


        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);


    }

    @After
    public void tearDown(){
        driver.quit();
        driver = null;
    }


}
