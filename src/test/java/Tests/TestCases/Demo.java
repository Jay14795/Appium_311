package Tests.TestCases;

import Tests.APIs.Login_API;
import Tests.Base.BaseClass;
import Tests.Utilities.ReadExcelData;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
public class Demo extends BaseClass
{
        @Test(dataProviderClass = ReadExcelData.class, dataProvider = "LoginData")
        public void login(String login_id, String password, String expectedResponse) throws JSONException {
            // Make the API call
            Login_API myInstance = new Login_API();
            String Body = myInstance.loginAPI(login_id, password, expectedResponse);

            // Parse the response
            JSONObject jsonObject = new JSONObject(Body);
            JSONArray notificationArray = jsonObject.getJSONArray("NOTIFICATION");
            String notification = notificationArray.getString(0);
            System.out.println("Notification: " + notification);

            JsonPath jsonPath = new JsonPath(Body);
            String AuthToken = jsonPath.getString("user.authtoken");
            System.out.println("AuthTOKEN:" + AuthToken);

            // Enter login details and click login button
            WebElement emailInput = driver.findElement(By.xpath(locators.getProperty("Email")));
            emailInput.sendKeys(login_id);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            WebElement passwordInput = driver.findElement(By.xpath(locators.getProperty("Password")));
            passwordInput.sendKeys(password);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            WebElement loginButton = driver.findElement(By.xpath(locators.getProperty("LoginButton")));
            loginButton.click();

            // Validate the response and save AuthToken if successful
            if (notification.equals("The email or password is invalid")) {
                driver.findElement(By.xpath(locators.getProperty("Error"))).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                Assert.fail("Actual Body is" + " " + Body);
            } else {
                System.out.println("Logged In Successfully");
                BaseClass.AuthToken = AuthToken;
                // Write the Auth token to a file
                try (PrintWriter out = new PrintWriter("authtoken.txt")) {
                    out.println(AuthToken);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
