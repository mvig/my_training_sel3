package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Tirex on 27.11.2016.
 */
public class TestStickers extends TestBase {
    @Test
    public void stickerExistTest() {
        app.getMainPageHelper().gotoMainPage();


        List<WebElement> list = getApp().driver.findElements(By.className("image-wrapper"));
        System.out.println("Картинок товара: " + list.size());

        for (WebElement item : list) {
            Assert.assertTrue(item.findElement(By.cssSelector("div.sticker")).isDisplayed());
            System.out.println(item.findElement(By.cssSelector("div.sticker")).getAttribute("title").toString());
            Assert.assertTrue(item.findElements(By.cssSelector("div.sticker")).size()==1);
        }



    }


}
