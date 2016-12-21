package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 04.12.2016.
 */
public class TestAddNewProduct extends TestBase {
    @Test
    public void addNewProductTest() throws InterruptedException {
        app.getAdminPageHelper().doLiginByAdmin();
        app.getMainPageHelper().goTo("http://localhost/litecart/admin/?app=catalog&doc=catalog");


        List<WebElement> driverElementsBefore = getApp().driver.findElements(By.xpath("//table[@class='dataTable']//td[3]"));
        String[] listArrayBefore = new String[driverElementsBefore.size()];

        int i = 0;
        for (WebElement l : driverElementsBefore) {
            listArrayBefore[i] = l.getText();
            i++;
        }

        getApp().driver.findElement(By.linkText("Add New Product")).click();


        if (!getApp().driver.findElement(By.name("status")).isSelected()) {
            getApp().driver.findElement(By.name("status")).click();
        }


        getApp().driver.findElement(By.name("name[en]")).click();
        getApp().driver.findElement(By.name("name[en]")).clear();
        getApp().driver.findElement(By.name("name[en]")).sendKeys("Test");
        getApp().driver.findElement(By.name("code")).click();
        getApp().driver.findElement(By.name("code")).clear();
        getApp().driver.findElement(By.name("code")).sendKeys((String) app.gRandomName());
        getApp().driver.findElement(By.name("quantity")).click();
        getApp().driver.findElement(By.name("quantity")).clear();
        getApp().driver.findElement(By.name("quantity")).sendKeys("1");


        if (!getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[2]/td[1]/input")).isSelected()) {
            getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[2]/td[1]/input")).click();
        }

        if (!getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[3]/td[1]/input")).isSelected()) {
            getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[3]/td[1]/input")).click();
        }

        if (!getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[7]/td/div/table/tbody/tr[4]/td[1]/input")).isSelected()) {
            getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[7]/td/div/table/tbody/tr[4]/td[1]/input")).click();
        }
        getApp().driver.findElement(By.name("quantity")).click();
        if (!getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[8]/td/table/tbody/tr/td[4]/select//option[3]")).isSelected()) {
            getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[8]/td/table/tbody/tr/td[4]/select//option[3]")).click();
        }
        if (!getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[8]/td/table/tbody/tr/td[4]/select//option[2]")).isSelected()) {
            getApp().driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[8]/td/table/tbody/tr/td[4]/select//option[2]")).click();
        }

        Calendar cal = Calendar.getInstance();
        //Date date1 = Calendar.getInstance().getTime();
        Date date1 = cal.getTime();
        System.out.println("Now date: " + date1);
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(date1);
        System.out.println("curStringDate: " + curStringDate);
        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.name("date_valid_from")), 1, 1)
                .click()
                .release()
                .perform();
        getApp().driver.findElement(By.name("date_valid_from")).sendKeys(curStringDate);
        cal.add(Calendar.DATE, 5);
        Date date2 = cal.getTime();
        System.out.println("20 days later: " + date2);
        String stringDateTo = new SimpleDateFormat("dd.MM.yyyy").format(date2);
        System.out.println("stringDateTo: " + stringDateTo);
        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.name("date_valid_to")), 1, 1)
                .click()
                .release()
                .perform();
        getApp().driver.findElement(By.name("date_valid_to")).sendKeys(stringDateTo);
        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.xpath("//a[contains(.,'Information')]")))
                .click()
                .release()
                .perform();

        Select selectManufacturer = new Select(getApp().driver.findElement(By.xpath("//select[@name='manufacturer_id']")));
        if (selectManufacturer.getOptions().size() > 0) {
            selectManufacturer.selectByIndex(selectManufacturer.getOptions().size() - 1);
        }

        Select selectSupplier = new Select(getApp().driver.findElement(By.xpath("//select[@name='supplier_id']")));
        if (selectSupplier.getOptions().size() > 0) {
            selectSupplier.selectByIndex(selectSupplier.getOptions().size() - 1);
        }
        getApp().driver.findElement(By.xpath("//input[@name='keywords']")).click();
        getApp().driver.findElement(By.xpath("//input[@name='keywords']")).clear();
        getApp().driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("test_keywords");

        getApp().driver.findElement(By.xpath("//input[@name='keywords']")).click();
        getApp().driver.findElement(By.xpath("//input[@name='keywords']")).clear();
        getApp().driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("test_keywords");

        getApp().driver.findElement(By.xpath("//input[@name='short_description[en]']")).click();
        getApp().driver.findElement(By.xpath("//input[@name='short_description[en]']")).clear();
        getApp().driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("test_short_description");
        ;


        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.xpath("//span[@style='white-space: normal;']")))
                .click()
                .sendKeys("Test description")
                .release()
                .perform();

        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.xpath("//input[@name='head_title[en]']")))
                .click()
                .sendKeys("Test head_title")
                .release()
                .perform();

        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.xpath("//input[@name='meta_description[en]']")))
                .click()
                .sendKeys("Test meta_description")
                .release()
                .perform();
        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.xpath("//a[@href='#tab-prices']")))
                .click()
                .release()
                .perform();
        getApp().driver.findElement(By.xpath("//input[@name='purchase_price']")).click();
        getApp().driver.findElement(By.xpath("//input[@name='purchase_price']")).clear();
        getApp().driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("1");

        Select selectPurchasePriceCurrencyCode = new Select(getApp().driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")));
        if (selectPurchasePriceCurrencyCode.getOptions().size() > 0) {
            selectPurchasePriceCurrencyCode.selectByIndex(selectPurchasePriceCurrencyCode.getOptions().size() - 1);
        }

        Select selectTaxClassId = new Select(getApp().driver.findElement(By.xpath("//select[@name='tax_class_id']")));
        if (selectTaxClassId.getOptions().size() > 0) {
            selectTaxClassId.selectByIndex(selectTaxClassId.getOptions().size() - 1);
        }

        new Actions(getApp().driver)
                .moveToElement(getApp().driver.findElement(By.xpath("//button[@name='save']")))
                .click()
                .release()
                .perform();

        getApp().wait.until(visibilityOfElementLocated(By.id("content")));

        List<WebElement> driverElementsAfter = getApp().driver.findElements(By.xpath("//table[@class='dataTable']//td[3]"));
        String[] listArrayAfter = new String[driverElementsAfter.size()];

        int j = 0;
        for (WebElement l : driverElementsAfter) {
            listArrayAfter[j] = l.getText();
        }
        Assert.assertNotEquals(listArrayBefore.length, listArrayAfter.length);

    }


}