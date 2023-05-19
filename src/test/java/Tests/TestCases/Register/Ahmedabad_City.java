package Tests.TestCases.Register;

import Tests.APIs.Register_API;
import Tests.Base.BaseClass;
import Tests.Utilities.ReadExcelData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.time.Duration;

public class Ahmedabad_City extends BaseClass
{
 @Test(dataProviderClass = ReadExcelData.class,dataProvider = "RegisterData")
    public void Ahmedabad(String email, String Confirm_Email, String first_name, String last_name, String password, String confirm_password, String device_token, String app_version, String platform, String os_version, String device_model, String mobile_number, String designation_id, String zone_id, String ward_id, String face_id, String department_id, String sub_department_id, String employee_id, String paybill_no) throws InterruptedException {

        double mobile_number_double = Double.parseDouble(mobile_number);

        DecimalFormat df = new DecimalFormat("#");
        String mobile_NUMBER = df.format(mobile_number_double);
        System.out.println(mobile_NUMBER); // Output: 123456789

        driver.findElement(By.xpath(locators.getProperty("register"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("Select_City_Ahmedabad"))).click(); // Change the name of City from locators If you want to change. Currently is for Ahmedabad
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("EmployerID"))).sendKeys("15");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("sUbmit"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        //Upload Avatar
        driver.setFileDetector(new LocalFileDetector());
        WebElement fileInput = driver.findElement(By.xpath(locators.getProperty("Avatar_Image")));
        fileInput.click();

        driver.findElement(By.id(locators.getProperty("ShutterButton"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(By.id(locators.getProperty("DoneButton"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.id(locators.getProperty("MenuCrop"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("AhmedabadFirstName"))).sendKeys(first_name);
        driver.findElement(By.xpath(locators.getProperty("AhmedabadLastName"))).sendKeys(last_name);
        driver.findElement(By.xpath(locators.getProperty("EMAIL"))).sendKeys(email);
        driver.findElement(By.xpath(locators.getProperty("ConfirmEmail"))).sendKeys(Confirm_Email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("verify"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebElement OTP1= driver.findElement(By.xpath(locators.getProperty("Enter_OTP1")));
        OTP1.click();
        OTP1.sendKeys("1");

        WebElement OTP2= driver.findElement(By.xpath(locators.getProperty("Enter_OTP2")));
        OTP2.click();
        OTP2.sendKeys("1");

        WebElement OTP3= driver.findElement(By.xpath(locators.getProperty("Enter_OTP3")));
        OTP3.click();
        OTP3.sendKeys("1");

        WebElement OTP4= driver.findElement(By.xpath(locators.getProperty("Enter_OTP4")));
        OTP4.click();
        OTP4.sendKeys("1");

        driver.findElement(By.xpath(locators.getProperty("verify"))).click();
        Thread.sleep(7000);

        WebElement Pass_word= driver.findElement(By.xpath(locators.getProperty("pass")));
        Pass_word.click();
        Pass_word.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        driver.findElement(By.xpath(locators.getProperty("conf_pass"))).sendKeys(confirm_password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        WebElement MNo = driver.findElement(By.xpath(locators.getProperty("mobileNo")));
        MNo.sendKeys(mobile_NUMBER);

        driver.findElement(By.xpath(locators.getProperty("verify"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(locators.getProperty("verify"))).click();
        Thread.sleep(3000);

        WebElement MobileOTP1= driver.findElement(By.xpath(locators.getProperty("Enter_OTP1")));
        MobileOTP1.click();
        MobileOTP1.sendKeys("1");

        WebElement MobileOTP2= driver.findElement(By.xpath(locators.getProperty("Enter_OTP2")));
        MobileOTP2.click();
        MobileOTP2.sendKeys("1");

        WebElement MobileOTP3= driver.findElement(By.xpath(locators.getProperty("Enter_OTP3")));
        MobileOTP3.click();
        MobileOTP3.sendKeys("1");

        WebElement MobileOTP4= driver.findElement(By.xpath(locators.getProperty("Enter_OTP4")));
        MobileOTP4.click();
        MobileOTP4.sendKeys("1");

        driver.findElement(By.xpath(locators.getProperty("verify"))).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath(locators.getProperty("AhmedabadSelectZone"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.findElement(By.xpath(locators.getProperty("AhmedabadZonePath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("AhmedabadSelectWard"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.findElement(By.xpath(locators.getProperty("AhmedabadWardPath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("AhmedabadSelectDesignation"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.findElement(By.xpath(locators.getProperty("AhmedabadDesignationPath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("AhmedabadSelectDepartment"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.findElement(By.xpath(locators.getProperty("AhmedabadDepartmentPath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("Register_button"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("UserNotExist"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Register_API Object= new Register_API();
        Object.registerAPI(email,Confirm_Email,first_name,last_name,password, confirm_password, device_token, app_version,platform,os_version,device_model, mobile_number, designation_id, zone_id, ward_id, face_id, department_id,sub_department_id, employee_id, paybill_no);

        Register_API myInstance = new Register_API();
        int statusCode = myInstance.registerAPI(email,Confirm_Email,first_name,last_name,password, confirm_password, device_token, app_version,platform,os_version,device_model, mobile_number, designation_id, zone_id, ward_id, face_id, department_id,sub_department_id, employee_id, paybill_no);

        if (statusCode==200)
        {
            driver.findElement(By.xpath(locators.getProperty("UserNotExist"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }else {
            driver.findElement(By.xpath(locators.getProperty("Error"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }
        Assert.assertEquals(statusCode,200);
    }

}
