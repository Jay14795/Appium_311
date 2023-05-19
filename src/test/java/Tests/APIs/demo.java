package Tests.APIs;

import Tests.Base.BaseClass;
import Tests.Utilities.ReadExcelData;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.time.Duration;

public class demo extends BaseClass {
    @Test(dataProviderClass = ReadExcelData.class, dataProvider = "LoginData")
    public void login(String login_id, String password, String expectedResponse) throws JSONException {
        WebElement emailInput = driver.findElement(By.xpath(locators.getProperty("Email")));
        emailInput.sendKeys(login_id);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebElement passwordInput = driver.findElement(By.xpath(locators.getProperty("Password")));
        passwordInput.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebElement loginButton = driver.findElement(By.xpath(locators.getProperty("LoginButton")));
        loginButton.click();
    }
}