import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 16.11.2016.
 */
public class TestLitecart extends TestBase {
    @Test
    public void testAdmin() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        wait.until(visibilityOfElementLocated(By.id("box-apps-menu")));
        assertThat(driver.getCurrentUrl().toString(), equalTo("http://localhost/litecart/admin/"));

        driver.findElement(By.className("fa-sign-out")).click();

        wait.until(visibilityOfElementLocated(By.id("box-login-wrapper")));
        assertThat(driver.getCurrentUrl().toString(), equalTo("http://localhost/litecart/admin/login.php"));

    }
}
