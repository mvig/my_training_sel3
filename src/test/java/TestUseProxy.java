import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.proxy.auth.AuthType;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Tirex on 17.12.2016.
 */


public class TestUseProxy {


    private WebDriver driver;


    @Test
    public void testAccessProtectedSite() throws Exception {

        String domenNameForTest = "software-testing.ru";

        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0); // порт выберает автоматически, когда указан 0
        //proxy.start(8888); // указан порт 8888

        int port = proxy.getPort();
        System.out.println(port);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
        // driver = new FirefoxDriver(capabilities);
        // driver = new ChromeDriver(capabilities);
        driver = new ChromeDriver(capabilities);

        //server.autoBasicAuthorization("доменное имя без протокола", "username", "password");
        //proxy.autoAuthorization(domenNameForTest, "admin", "lazo8k3", AuthType.BASIC);
        proxy.autoAuthorization(domenNameForTest, "username", "password", AuthType.BASIC);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label
        proxy.newHar(domenNameForTest);

        //Указываем урл
        driver.get("http://" + domenNameForTest);

        // get the HAR data
        Har har = proxy.getHar();

        // обработка полученных данных
        // use http://www.softwareishard.com/har/viewer/ for view Har file.");
        try {
            File file = new File("results\\" + domenNameForTest + ".har");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            try {
                har.writeTo(fos);
            } finally {
                driver.close();
            }
        } catch (IOException e) {
            // обработка ошибки
            e.printStackTrace();
        } finally {

            driver.quit();
            proxy.stop();
        }
    }

}


