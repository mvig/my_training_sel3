import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 17.12.2016.
 */
public class TestLogFromBrowser extends TestBase {
    @Test
    public void errLogBrowserTest() throws InterruptedException {

        doLiginByAdmin();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait.until(visibilityOfElementLocated(By.id("content")));
        List<WebElement> editLink = driver.findElements(By.xpath("//a[@title='Edit']"));//получить список урл на редатирование товара
        List<String> productUrlList = new ArrayList<String>();
        int countUrl = editLink.size();
        System.out.println("Всего Url на проверку: " + countUrl);
        for (WebElement l : editLink) {
            productUrlList.add(l.getAttribute("href"));
        }
        int i = 0;
        for (String url : productUrlList) {
            driver.navigate().to(url);
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                System.out.println(l);
                i++;
            }
            System.out.println(url);
            Assert.assertTrue(i < 1);
            System.out.println(i);
            wait.until(visibilityOfElementLocated(By.className("content")));
            driver.navigate().back();
            wait.until(visibilityOfElementLocated(By.id("content")));

        }

    }

    @Test ()
    public void errTest() throws InterruptedException {

        doLiginByAdmin();
        driver.navigate().to("http://localhost/litecart/admin/?app=orders&doc=orders");
        wait.until(visibilityOfElementLocated(By.id("content")));
        driver.findElement(By.xpath("//a[contains(.,' Create New Order')]")).click();
        int i = 0;
        for (LogEntry l : driver.manage().logs().get("browser").filter(Level.SEVERE)) {
            System.out.println(l);
            i++;
        }
        Assert.assertTrue(i < 1);
    }
}


