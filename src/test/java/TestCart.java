import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 08.12.2016.
 */
public class TestCart extends TestBase {
    public String nt = "";

    @Test
    public void cartTest() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/en/");
        wait.until(visibilityOfElementLocated(By.className("content")));
        int toPrice = 3; //Сколько уникальных товаров добавить в корзину
        List<String> namesList = new ArrayList<String>();
        System.out.println(" 0 namesList.size: " + namesList.size());
        do {

            List<WebElement> t1 = driver.findElements(By.cssSelector("#box-most-popular li"));
            if (t1.size() > 0 && namesList.size() < toPrice) {
                for (int i = 0; i < t1.size() - 1; i++) {
                    if (!namesList.contains(t1.get(i).getText())) {
                        namesList.add(t1.get(i).getText());
                        t1.get(i).click();
                        wait.until(visibilityOfElementLocated(By.className("content")));
                        System.out.println("Выбираем товар в корзину из box-most-popular");
                        addToCard();
                        wait.until(ExpectedConditions.attributeToBe(By.xpath("//span[@class='quantity']"),
                                "textContent", String.valueOf(namesList.size())));
                        driver.navigate().back();
                        wait.until(visibilityOfElementLocated(By.className("content")));
                        break;
                    }
                }
            }
            List<WebElement> t2 = driver.findElements(By.cssSelector("#box-campaigns li"));
            if (t2.size() > 0 && namesList.size() < toPrice) {
                for (int i = 0; i < t2.size() - 1; i++) {
                    if (!namesList.contains(t2.get(i).getText())) {
                        namesList.add(t2.get(i).getText());
                        t2.get(i).click();
                        wait.until(visibilityOfElementLocated(By.className("content")));
                        System.out.println("Выбираем товар в корзину из box-campaigns");
                        addToCard();
                        wait.until(ExpectedConditions.attributeToBe(By.xpath("//span[@class='quantity']"),
                                "textContent", String.valueOf(namesList.size())));
                        driver.navigate().back();
                        wait.until(visibilityOfElementLocated(By.className("content")));
                        break;
                    }
                }
            }

            List<WebElement> t3 = driver.findElements(By.cssSelector("#box-latest-products li"));
            if (t3.size() > 0 && namesList.size() < toPrice) {
                for (int i = 0; i < t3.size() - 1; i++) {
                    if (!namesList.contains(t3.get(i).getText())) {
                        namesList.add(t3.get(i).getText());
                        t3.get(i).click();
                        wait.until(visibilityOfElementLocated(By.className("content")));
                        System.out.println("Выбираем товар в корзину из box-latest-products");
                        addToCard();
                        wait.until(ExpectedConditions.attributeToBe(By.xpath("//span[@class='quantity']"),
                                "textContent", String.valueOf(namesList.size())));
                        driver.navigate().back();
                        wait.until(visibilityOfElementLocated(By.className("content")));
                        break;
                    }
                }
            }

        } while (namesList.size() < toPrice);

        //sleep(1000);

        driver.findElement(By.linkText("Checkout »")).click();

        for (int i = 0; i < namesList.size(); i++) {
            driver.findElement(By.name("remove_cart_item")).click();
            System.out.println("Удаляем товар из корзины");
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"))));

        }
        driver.navigate().back();


    }


    public void addToCard() {
        if (isElementPresent(By.xpath("//select[contains(@name,'options[Size]')]"))) {
            Select select = new Select(driver.findElement(By.xpath("//select[@name='options[Size]']")));
            select.selectByIndex(1);
        }
        driver.findElement(By.name("add_cart_product")).click();

    }
}