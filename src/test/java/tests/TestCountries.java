package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by Tirex on 28.11.2016.
 */
public class TestCountries extends TestBase {

    @Test
    public void countriesSortTest() {
        app.getAdminPageHelper().doLiginByAdmin();
        getApp().driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        getApp().wait.until(visibilityOfElementLocated(By.id("content")));
        List<WebElement> listCountries = getApp().driver.findElements(By.xpath("//div/div/div/table/tbody/tr/td[3]/form/table/tbody//td[5]/a"));
        int countries = listCountries.size();
        System.out.println("Стран в списке: " + countries);
        String[] unSotrArray = new String[countries];
        String[] sotrArray = new String[countries];
        int i = 0;
        for (WebElement item : listCountries) {
            String c = item.getAttribute("innerText");
            System.out.println(c);
            unSotrArray[i] = c;
            i++;
        }

        System.arraycopy(unSotrArray, 0, sotrArray, 0, countries);
        Arrays.sort(unSotrArray);
        Assert.assertArrayEquals(unSotrArray, sotrArray);

    }

    @Test
    public void zoneCountriesSortTest() throws InterruptedException {

        app.getAdminPageHelper().doLiginByAdmin();
        getApp().driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        getApp().wait.until(visibilityOfElementLocated(By.id("content")));

        List<WebElement> zones = getApp().driver.findElements(By.cssSelector("tr.row"));

        String[] countryWithZones = new String[zones.size()];
        for (int i = 0; i < zones.size(); i++)
            countryWithZones[i] = "";

        int i = 0;
        for (WebElement zone : zones) {
            if (!zone.findElements(By.cssSelector("td")).get(5).getText().equals("0")) {
                System.out.println("get(4): " + zone.findElements(By.cssSelector("td")).get(4).getText());
                System.out.println("get(5): " + zone.findElements(By.cssSelector("td")).get(5).getText());
                System.out.println(!zone.findElements(By.cssSelector("td")).get(5).getText().equals("0"));
                countryWithZones[i] = zone.findElements(By.cssSelector("td")).get(4).getText();
                i++;
            }
        }
        for (String z : countryWithZones) {
            if (!(z.isEmpty())) {
                System.out.println("Проверяем сортировку стран в зонах для: " + z);
                getApp().driver.findElement(By.linkText(z)).click();
                sleep(1000);

                List<WebElement> zonesSelectCountry = getApp().driver.findElements(By.cssSelector("table#table-zones tr + tr td:nth-of-type(3) input"));
                int zonesCount = (zonesSelectCountry.size() - 1);
                String[] unSotrArray = new String[zonesCount];
                System.out.println(zonesCount);

                for (int l = 0; l < zonesCount; l++) {
                    unSotrArray[l] = zonesSelectCountry.get(l).getAttribute("value");
                    System.out.println(unSotrArray[l]);
                }


                String[] sotrArray = new String[unSotrArray.length];
                System.arraycopy(unSotrArray, 0, sotrArray, 0, unSotrArray.length);


                Arrays.sort(sotrArray);

                Assert.assertArrayEquals(sotrArray, unSotrArray);
                getApp().driver.navigate().back();
                sleep(1000);
            }
        }
    }

    @Test
    public void geoZoneSortCountriesTest() throws InterruptedException {
        app.getAdminPageHelper().doLiginByAdmin();
        getApp().driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        getApp().wait.until(visibilityOfElementLocated(By.id("content")));
        sleep(5000);
        List<WebElement> geoZonesExist = getApp().driver.findElements(By.xpath("//table[@class='dataTable']//td[3]"));
        System.out.println(geoZonesExist.size());
        String[] geoZonesCountry = new String[geoZonesExist.size()];
        for (int i = 0; i < geoZonesExist.size(); i++)
            geoZonesCountry[i] = "";

        int i = 0;
        for (WebElement geoZone : geoZonesExist) {
            geoZonesCountry[i] = geoZone.getText();
            i++;
        }
        for (String z : geoZonesCountry) {
            if (!(z.isEmpty())) {
                System.out.println("Проверяем сортировку стран в geo зоне для: " + z);
                getApp().driver.findElement(By.linkText(z)).click();
                sleep(1000);

                List<WebElement> selectCountry = getApp().driver.findElements(By.xpath("//*[@id=\"table-zones\"]/tbody//td[3]/select "));

                int zonesCount = (selectCountry.size());
                String[] unSotrArray = new String[zonesCount];
                for (int l = 0; l < zonesCount; l++) {
                    WebElement selectElem = selectCountry.get(l);
                    Select select = new Select(selectElem);
                    unSotrArray[l] = select.getFirstSelectedOption().getText();
                    System.out.println(unSotrArray[l]);
                }
                String[] sotrArray = new String[unSotrArray.length];
                System.arraycopy(unSotrArray, 0, sotrArray, 0, unSotrArray.length);


                Arrays.sort(sotrArray);

                Assert.assertArrayEquals(sotrArray, unSotrArray);
                getApp().driver.navigate().back();
                sleep(1000);
            }
        }

    }
}