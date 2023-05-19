package Tests.TestCases.Login;

import Tests.Base.BaseClass;
import Tests.APIs.ForgotPw_API;
import Tests.Utilities.ReadExcelData;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import static Locators.locators.*;

public class ForgotPw extends BaseClass {
    ForgotPw_API myInstance = new ForgotPw_API();

    @Test(dataProviderClass = ReadExcelData.class,dataProvider = "ForgotPw")
    public void ForgotPassword(String email, String expectedResponse) throws JSONException {

        driver.findElement(By.xpath(ForgotPw)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(ForgotPw_Email)).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement Reset = driver.findElement(By.xpath(ResetButton));
        Reset.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        String ResponseBody = myInstance.forgotPw_Api(email, expectedResponse);

        if (ResponseBody.equals("[Email is not valid]"))
        {
            driver.findElement(By.xpath(Close)).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            Assert.fail("Email is not valid");

        }else if(ResponseBody.equals("[Entered email does not exists]"))
        {
            driver.findElement(By.xpath(UserNotExist)).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            Assert.fail("Entered email does not exists");
        } else
        {

            driver.findElement(By.id(OK)).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            //Reset Password

            driver.findElement(By.xpath(OTP)).sendKeys("1111");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(password1)).sendKeys("Qa@123456");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(ConfirmPassword1)).sendKeys("Qa@123456");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(reset)).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(By.id(OK)).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            System.out.println(ResponseBody);
        }
    }
}


