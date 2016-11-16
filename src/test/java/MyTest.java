import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Tirex on 16.11.2016.
 */
public class MyTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("test");
        driver.findElement(By.name("btnG")).click();

        wait.until(titleIs("test - Поиск в Google"));
        List<WebElement> list = driver.findElements(By.className("s"));
        assertThat(9, equalTo(list.size()));


    }

}

