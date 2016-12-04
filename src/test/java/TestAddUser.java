import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 04.12.2016.
 */
public class TestAddUser extends TestBase {

    @Test
    public void testAddNewUser() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/en/");
        wait.until(visibilityOfElementLocated(By.className("content")));
        driver.findElement(By.xpath("//a[contains(.,'New customers click here')]")).click();
        wait.until(visibilityOfElementLocated(By.className("content")));
        //регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями)
        String passwd = "123456";
        String email = (gRandomName() + "@gmail.com");
        driver.findElement(By.name("customer_form")).click();
        driver.findElement(By.name("tax_id")).click();
        driver.findElement(By.name("tax_id")).clear();
        driver.findElement(By.name("tax_id")).sendKeys("3212312312");
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("Test company");
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("Name");
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("Lastname");
        driver.findElement(By.name("address1")).click();
        driver.findElement(By.name("address1")).clear();
        driver.findElement(By.name("address1")).sendKeys("This is address1");
        driver.findElement(By.name("address2")).click();
        driver.findElement(By.name("address2")).clear();
        driver.findElement(By.name("address2")).sendKeys("This is address2");
        driver.findElement(By.name("postcode")).click();
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Cityname");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("+380971234567");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(passwd);
        driver.findElement(By.name("confirmed_password")).click();
        driver.findElement(By.name("confirmed_password")).clear();
        driver.findElement(By.name("confirmed_password")).sendKeys(passwd);
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.linkText("Logout")).click();
        //выход (logout), потому что после успешной регистрации автоматически происходит вход
        driver.findElement(By.linkText("Login")).click();
        wait.until(visibilityOfElementLocated(By.className("content")));
        //вход (login)
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(passwd);
        if (!driver.findElement(By.name("remember_me")).isSelected()) {
            driver.findElement(By.name("remember_me")).click();
        }

        driver.findElement(By.name("login")).click();
        wait.until(visibilityOfElementLocated(By.className("content")));
        //выход (logout)
        driver.findElement(By.linkText("Logout")).click();

    }
}