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
        doLiginByAdmin();
        wait.until(visibilityOfElementLocated(By.id("box-apps-menu")));

        int sizeMenu = driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();
        for (int i = 0; i < sizeMenu; i++) {
            driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).get(i).click();

            Assert.assertTrue(isElementPresent(By.tagName("h1")));
            System.out.println("");
            System.out.println(i+". "+driver.findElement(By.tagName("h1")).getText());


            if (driver.findElements(By.cssSelector("ul#box-apps-menu li.selected ul li")).size() != 0) {

                int newCount = driver.findElements(By.cssSelector("ul#box-apps-menu li.selected ul li")).size();
                for (int l = 0; l < newCount; l++) {
                    driver.findElements(By.cssSelector("ul#box-apps-menu li.selected ul li")).get(l).click();

                    Assert.assertTrue(isElementPresent(By.tagName("h1")));
                    System.out.println(i+"."+l+". "+driver.findElement(By.tagName("h1")).getText());
                }
            }

        }
    }


}
