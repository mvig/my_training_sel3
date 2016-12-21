package tests;

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

        app.getAdminPageHelper().doLiginByAdmin();

        getApp().wait.until(visibilityOfElementLocated(By.id("box-apps-menu")));
        assertThat(getApp().driver.getCurrentUrl().toString(), equalTo("http://localhost/litecart/admin/"));

        getApp().driver.findElement(By.className("fa-sign-out")).click();

        getApp().wait.until(visibilityOfElementLocated(By.id("box-login-wrapper")));
        assertThat(getApp().driver.getCurrentUrl().toString(), equalTo("http://localhost/litecart/admin/login.php"));

    }

}
