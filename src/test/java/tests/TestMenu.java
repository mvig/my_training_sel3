package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 25.11.2016.
 */
public class TestMenu extends TestBase {

    @Test
    public void testMenuItem() throws Exception {
        app.getAdminPageHelper().doLiginByAdmin();
        getApp().wait.until(visibilityOfElementLocated(By.id("box-apps-menu")));

        int sizeMenu = getApp().driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();
        for (int i = 0; i < sizeMenu; i++) {
            getApp().driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(i).click();

            Assert.assertTrue(app.getCardPageHelper().isElementPresent(By.tagName("h1")));
            System.out.println("");
            System.out.println(i+". "+ getApp().driver.findElement(By.tagName("h1")).getText());


            if (getApp().driver.findElements(By.cssSelector("ul#box-apps-menu li.selected ul li")).size() != 0) {

                int newCount = getApp().driver.findElements(By.cssSelector("ul#box-apps-menu li.selected ul li")).size();
                for (int l = 0; l < newCount; l++) {
                    getApp().driver.findElements(By.cssSelector("ul#box-apps-menu li.selected ul li")).get(l).click();

                    Assert.assertTrue(app.getCardPageHelper().isElementPresent(By.tagName("h1")));
                    System.out.println(i+"."+l+". "+ getApp().driver.findElement(By.tagName("h1")).getText());
                }
            }

        }
    }


}
