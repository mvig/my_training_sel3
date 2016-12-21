package tests;

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

        app.getAdminPageHelper().doLiginByAdmin();
        getApp().driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        getApp().wait.until(visibilityOfElementLocated(By.id("content")));
        List<WebElement> editLink = getApp().driver.findElements(By.xpath("//a[@title='Edit']"));//получить список урл на редатирование товара
        List<String> productUrlList = new ArrayList<String>();
        int countUrl = editLink.size();
        System.out.println("Всего Url на проверку: " + countUrl);
        for (WebElement l : editLink) {
            productUrlList.add(l.getAttribute("href"));
        }
        int i = 0;
        for (String url : productUrlList) {
            getApp().driver.navigate().to(url);
            for (LogEntry l : getApp().driver.manage().logs().get("browser").getAll()) {
                System.out.println(l);
                i++;
            }
            System.out.println(url);
            Assert.assertTrue(i < 1);
            System.out.println(i);
            getApp().wait.until(visibilityOfElementLocated(By.className("content")));
            getApp().driver.navigate().back();
            getApp().wait.until(visibilityOfElementLocated(By.id("content")));

        }

    }

    @Test ()
    public void errTest() throws InterruptedException {

        app.getAdminPageHelper().doLiginByAdmin();
        getApp().driver.navigate().to("http://localhost/litecart/admin/?app=orders&doc=orders");
        getApp().wait.until(visibilityOfElementLocated(By.id("content")));
        getApp().driver.findElement(By.xpath("//a[contains(.,' Create New Order')]")).click();
        int i = 0;
        for (LogEntry l : getApp().driver.manage().logs().get("browser").filter(Level.SEVERE)) {
            System.out.println(l);
            i++;
        }
        Assert.assertTrue(i < 1);
    }
}


