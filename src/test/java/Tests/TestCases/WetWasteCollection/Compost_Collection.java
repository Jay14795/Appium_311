package Tests.TestCases.WetWasteCollection;

import Tests.APIs.Compost_Pit.WetWaste_API;
import Tests.Base.BaseClass;
import Tests.Utilities.ReadExcelData;
import android.content.Context;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.Duration;

public class Compost_Collection extends BaseClass
{
    @Test(dataProviderClass = ReadExcelData.class, dataProvider = "CompostData",priority = 1)
    public void Compost_Collection_module(String VehicleNumber, String Weight,String Description) throws InterruptedException, JSONException {

        String AuthToken= BaseClass.getAuthToken();
        System.out.println("authToken:"+AuthToken);

        driver.findElement(By.xpath(locators.getProperty("Compost_Production"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        double Weight_double = Double.parseDouble(Weight);
        DecimalFormat df = new DecimalFormat("#");
        String Weight1 = df.format(Weight_double);

        driver.findElement(By.xpath(locators.getProperty("Compost_Production_Module"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(locators.getProperty("Create_New_Report"))).click();
        driver.runAppInBackground(Duration.ofSeconds(3));
        Thread.sleep(2000);

        driver.findElement(By.xpath(locators.getProperty("Next"))).click();
        Thread.sleep(2000);

        WebElement compostID= driver.findElement(By.xpath(locators.getProperty("CompostID")));
        String text= compostID.getText();
        if(text.equals("compost Id"))
        {
            compostID.sendKeys("QA_automation");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }else {
            System.out.println("CompostID:"+" "+text);
        }

        driver.findElement(By.xpath(locators.getProperty("Vehicle_Number"))).sendKeys(VehicleNumber);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("Weight_Kg"))).sendKeys(Weight1);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("Final_Disposal"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("Description"))).sendKeys(Description);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("CompostPhotos"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("Select_ImageFrom"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("shutter"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("Done"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("Check"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebElement submit= driver.findElement(By.xpath(locators.getProperty("submit")));
        submit.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));

        WetWaste_API myInstance2 = new WetWaste_API();
        String ResponseBody = myInstance2.wetWasteAPI(VehicleNumber, Weight, Description);
        System.out.println("ResponseBody:"+ResponseBody);

        if (ResponseBody.equals("Your Report Created Successfully")) {
            System.out.println("Response is " + ResponseBody);
        }
        driver.findElement(By.xpath(locators.getProperty("Error"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        Assert.assertEquals(ResponseBody,"Your Report Created Successfully");
    }
 }
