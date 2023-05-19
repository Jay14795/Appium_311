package Tests.TestCases.WetWasteCollection;
import Tests.APIs.Compost_Pit.WetWaste_API;
import Tests.Base.BaseClass;
import Tests.Utilities.ReadExcelData;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.DecimalFormat;
import java.time.Duration;
public class Wet_Waste extends BaseClass
{
    @Test(dataProviderClass = ReadExcelData.class, dataProvider = "WetWasteData",priority = 1)
    public void Wet_Waste_Collection(String VehicleNumber, String Weight,String Description, String AuthToken) {

        String token= BaseClass.getAuthToken();
        Wet_Waste_Collection(VehicleNumber, Weight,Description,AuthToken);

        try {

            double Weight_double = Double.parseDouble(Weight);
            DecimalFormat df = new DecimalFormat("#");
            String Weight1 = df.format(Weight_double);

            driver.findElement(By.xpath(locators.getProperty("Wet_waste_Collection"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            //Create New Report
            driver.findElement(By.xpath(locators.getProperty("Create_New_Report"))).click();
            driver.runAppInBackground(Duration.ofSeconds(3));

            driver.findElement(By.xpath(locators.getProperty("Next"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            WebElement compostID = driver.findElement(By.xpath(locators.getProperty("CompostID")));
            String text = compostID.getText();
            if (text.equals("compost Id")) {
                compostID.sendKeys("QA_automation");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            } else {
                System.out.println("CompostID:" + " " + text);
            }

            driver.findElement(By.xpath(locators.getProperty("Vehicle_Number"))).sendKeys(VehicleNumber);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(By.xpath(locators.getProperty("Weight_Kg"))).sendKeys(Weight1);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(By.xpath(locators.getProperty("Description"))).sendKeys(Description);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("photos"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("Select_ImageFrom"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("shutter"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("Done"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("Check"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            WebElement submit = driver.findElement(By.xpath(locators.getProperty("submit")));
            submit.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));

            WetWaste_API myInstance = new WetWaste_API();
            String ResponseBody = myInstance.wetWasteAPI(VehicleNumber, Weight, Description);
            System.out.println("ResponseBody:" + ResponseBody);

            if (ResponseBody.equals("Your Report Created Successfully")) {
                System.out.println("Response is " + ResponseBody);
            }
            driver.findElement(By.xpath(locators.getProperty("Error"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            Assert.assertEquals(ResponseBody, "Your Report Created Successfully");
        } catch (SessionNotCreatedException e) {
            // Restart the Appium server and retry the action
            driver.closeApp();
            driver.launchApp();
            // Perform the action again
            // ...
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
