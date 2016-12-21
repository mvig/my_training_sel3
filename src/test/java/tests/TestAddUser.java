package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 04.12.2016.
 */
public class TestAddUser extends TestBase {

    @Test
    public void testAddNewUser() throws InterruptedException {

        app.getMainPageHelper().gotoMainPage();
        getApp().driver.findElement(By.xpath("//a[contains(.,'New customers click here')]")).click();
        getApp().wait.until(visibilityOfElementLocated(By.className("content")));
        //регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями)
        String passwd = "123456";
        String email = (app.gRandomName() + "@gmail.com");
        getApp().driver.findElement(By.name("customer_form")).click();
        getApp().driver.findElement(By.name("tax_id")).click();
        getApp().driver.findElement(By.name("tax_id")).clear();
        getApp().driver.findElement(By.name("tax_id")).sendKeys("3212312312");
        getApp().driver.findElement(By.name("company")).click();
        getApp().driver.findElement(By.name("company")).clear();
        getApp().driver.findElement(By.name("company")).sendKeys("Test company");
        getApp().driver.findElement(By.name("firstname")).click();
        getApp().driver.findElement(By.name("firstname")).clear();
        getApp().driver.findElement(By.name("firstname")).sendKeys("Name");
        getApp().driver.findElement(By.name("lastname")).click();
        getApp().driver.findElement(By.name("lastname")).clear();
        getApp().driver.findElement(By.name("lastname")).sendKeys("Lastname");
        getApp().driver.findElement(By.name("address1")).click();
        getApp().driver.findElement(By.name("address1")).clear();
        getApp().driver.findElement(By.name("address1")).sendKeys("This is address1");
        getApp().driver.findElement(By.name("address2")).click();
        getApp().driver.findElement(By.name("address2")).clear();
        getApp().driver.findElement(By.name("address2")).sendKeys("This is address2");
        getApp().driver.findElement(By.name("postcode")).click();
        getApp().driver.findElement(By.name("postcode")).clear();
        getApp().driver.findElement(By.name("postcode")).sendKeys("12345");
        getApp().driver.findElement(By.name("city")).click();
        getApp().driver.findElement(By.name("city")).clear();
        getApp().driver.findElement(By.name("city")).sendKeys("Cityname");
        getApp().driver.findElement(By.name("email")).click();
        getApp().driver.findElement(By.name("email")).clear();
        getApp().driver.findElement(By.name("email")).sendKeys(email);
        getApp().driver.findElement(By.name("phone")).click();
        getApp().driver.findElement(By.name("phone")).clear();
        getApp().driver.findElement(By.name("phone")).sendKeys("+380971234567");
        getApp().driver.findElement(By.name("password")).click();
        getApp().driver.findElement(By.name("password")).clear();
        getApp().driver.findElement(By.name("password")).sendKeys(passwd);
        getApp().driver.findElement(By.name("confirmed_password")).click();
        getApp().driver.findElement(By.name("confirmed_password")).clear();
        getApp().driver.findElement(By.name("confirmed_password")).sendKeys(passwd);
        getApp().driver.findElement(By.name("create_account")).click();
        getApp().driver.findElement(By.linkText("Logout")).click();
        //выход (logout), потому что после успешной регистрации автоматически происходит вход
        getApp().driver.findElement(By.linkText("Login")).click();
        getApp().wait.until(visibilityOfElementLocated(By.className("content")));
        //вход (login)
        getApp().driver.findElement(By.name("email")).click();
        getApp().driver.findElement(By.name("password")).clear();
        getApp().driver.findElement(By.name("email")).sendKeys(email);
        getApp().driver.findElement(By.name("password")).click();
        getApp().driver.findElement(By.name("password")).clear();
        getApp().driver.findElement(By.name("password")).sendKeys(passwd);
        if (!getApp().driver.findElement(By.name("remember_me")).isSelected()) {
            getApp().driver.findElement(By.name("remember_me")).click();
        }

        getApp().driver.findElement(By.name("login")).click();
        getApp().wait.until(visibilityOfElementLocated(By.className("content")));
        //выход (logout)
        getApp().driver.findElement(By.linkText("Logout")).click();

    }
}