package tests;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 11.12.2016.
 */
public class TestOpenInNewWindow extends TestBase {
    @Test
    public void addNewProductTest() throws InterruptedException {
        app.getAdminPageHelper().doLiginByAdmin();
        getApp().driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        getApp().wait.until(visibilityOfElementLocated(By.id("content")));
        List<WebElement> editLink = getApp().driver.findElements(By.xpath("//a[@title='Edit']"));//получить список урл на редатирование страны
        System.out.println("Всего стран: " + editLink.size());
        getRandomItem(editLink.size(), editLink).click(); //открыть на редактирование слоучайно-выбранную страну
        List<WebElement> forClick = getApp().driver.findElements(By.xpath("//a[@target='_blank']"));
        System.out.println("Ссылок открывающих новое окно у с страницы редактирования" + forClick.size());
        for (WebElement l : forClick) {
            System.out.println(l);
            newBrowserWindowDoOenClose(l);
        }
    }

    public static WebElement getRandomItem(int amount, final List<WebElement> list) {
        //Возвращаем случайный элемент списка
        return list.get(RandomUtils.nextInt(0, amount - 1));

    }

    public void newBrowserWindowDoOenClose(WebElement l) throws InterruptedException {
        String mainWindow = getApp().driver.getWindowHandle();
        Set<String> oldWindowsSet = getApp().driver.getWindowHandles();
        l.click(); // открывает новое окно
        //sleep (5000);
        // ожидаем открытия и получаем дескриптор нового окна
        String newWindowHandle = (new WebDriverWait(getApp().driver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               // получаем новый набор дескрипторов, включающий уже и новое окно
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               // получаем дескриптор нового окна
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );

        getApp().driver.switchTo().window(newWindowHandle);
        getApp().driver.close();
        getApp().driver.switchTo().window(mainWindow);
    }


}
