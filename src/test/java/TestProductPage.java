import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 29.11.2016.
 */
public class TestProductPage extends TestBase {
    @Test
    public void testSalePage() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/en/");
        wait.until(visibilityOfElementLocated(By.className("content")));

        WebElement mainT = driver.findElements(By.cssSelector("#box-campaigns li")).get(0);
        String titleProduct = mainT.findElement(By.tagName("a")).getAttribute("title");
        String nameProduct = mainT.findElement(By.className("name")).getText();
        String nameManufacturer = mainT.findElement(By.className("manufacturer")).getText();
        String regularPrice = mainT.findElement(By.cssSelector(".price-wrapper .regular-price")).getText();
        String campaignPrice = mainT.findElement(By.cssSelector(".price-wrapper .campaign-price")).getText();

        Assert.assertEquals("Проверяем font-size: ", "14.4px", mainT.findElement(By.cssSelector(".price-wrapper .regular-price")).getCssValue("font-size"));
        Assert.assertEquals("Проверяем color: ","rgba(119, 119, 119, 1)", mainT.findElement(By.cssSelector(".price-wrapper s.regular-price")).getCssValue("color"));
        Assert.assertEquals("Проверяем font-size: ", "18px", mainT.findElement(By.cssSelector(".price-wrapper strong.campaign-price")).getCssValue("font-size"));
        Assert.assertEquals("Проверяем color: ","rgba(204, 0, 0, 1)", mainT.findElement(By.cssSelector(".price-wrapper strong.campaign-price")).getCssValue("color"));

        mainT.click();
        wait.until(visibilityOfElementLocated(By.className("content")));

        Assert.assertEquals("Проверяем title: ", titleProduct, driver.findElement(By.xpath("//h1[@class='title']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='" + titleProduct + "']")).isDisplayed());
        Assert.assertEquals("Проверяем name: ", nameProduct, driver.findElement(By.xpath("//h1[@class='title']")).getText());
        Assert.assertEquals("Проверяем nameManufacturer: ", nameManufacturer, driver.findElement(By.xpath("//div[@id='box-product']/div[2]/div[2]/div[1]/a/img")).getAttribute("title"));
        Assert.assertEquals("Проверяем regularPrice: ", regularPrice, driver.findElement(By.cssSelector("div#box-product.box .regular-price")).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("div#box-product.box s.regular-price")).isDisplayed());
        Assert.assertEquals("Проверяем color: ","rgba(102, 102, 102, 1)", driver.findElement(By.cssSelector("div#box-product.box s.regular-price")).getCssValue("color"));
        Assert.assertEquals("Проверяем font-size: ", "16px", driver.findElement(By.cssSelector("div#box-product.box s.regular-price")).getCssValue("font-size"));
        Assert.assertEquals("Проверяем campaignPrice: ", campaignPrice, driver.findElement(By.cssSelector("div#box-product.box .campaign-price")).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("div#box-product.box strong.campaign-price")).isDisplayed());
        Assert.assertEquals("Проверяем color: ","rgba(204, 0, 0, 1)", driver.findElement(By.cssSelector("div#box-product.box strong.campaign-price")).getCssValue("color"));
        Assert.assertEquals("Проверяем font-size: ", "22px", driver.findElement(By.cssSelector("div#box-product.box strong.campaign-price")).getCssValue("font-size"));

        driver.navigate().back();
        wait.until(visibilityOfElementLocated(By.className("content")));
    }
}
