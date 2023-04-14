package Tests.TestCases.Login;

import Tests.Base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class ForgotPw extends BaseClass {

    String apiUrl = "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/forgot_password";

    @DataProvider(name = "ForgotPw")
    public Object[][] getExcelData() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\ForgotPw.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Object[][] data = new Object[sheet.getLastRowNum() + 1][2];
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell emailCell = row.getCell(0);
            Cell expectedResponseCell = row.getCell(1);
            String email = null;
            String expectedResponse = null;
            if (emailCell != null) {
                emailCell.setCellType(CellType.STRING);
                email = emailCell.getStringCellValue();
            }
            if (expectedResponseCell != null) {
                expectedResponseCell.setCellType(CellType.STRING);
                expectedResponse = expectedResponseCell.getStringCellValue();
            }
            data[i][0] = email;
            data[i][1] = expectedResponse;
        }
        fis.close();
        workbook.close();
        return data;
    }

    @Test(dataProvider = "ForgotPw")
    public void ForgotPassword(String email, String expectedResponse) {

        driver.findElement(By.xpath(locators.getProperty("ForgotPw"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath(locators.getProperty("ForgotPw_Email"))).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement Reset = driver.findElement(By.xpath(locators.getProperty("ResetButton")));
        Reset.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        forgotPw_Api(email,expectedResponse);
    }

    public void forgotPw_Api(String email, String expectedResponse) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
        request.header("fromapp", "2");

        JSONObject json = new JSONObject();
        json.put("email", email);
        String jsonString = json.toString();
        request.body(jsonString);

        Response response = request.post(apiUrl);
        int res = response.getStatusCode();
        System.out.println("Int Response is" +res);
        String ResponseCode = Integer.toString(res);
        System.out.println("RESPONSE CODE IS" + " " + ResponseCode);

        String ResponseBody = response.getBody().jsonPath().getString("NOTIFICATION").toString();
        System.out.println("Response Body is" + " " + ResponseBody);


        if (ResponseBody.equals("[Email is not valid]"))
        {
            driver.findElement(By.xpath(locators.getProperty("Close"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        }else if(ResponseBody.equals("[Entered email does not exists]"))
        {
            driver.findElement(By.xpath(locators.getProperty("UserNotExist"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } else
        {

            driver.findElement(By.id(locators.getProperty("OK"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            //Reset Password

            driver.findElement(By.xpath(locators.getProperty("OTP"))).sendKeys("1111");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("password1"))).sendKeys("Qa@123456");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("ConfirmPassword1"))).sendKeys("Qa@123456");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            driver.findElement(By.xpath(locators.getProperty("reset"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.findElement(By.id(locators.getProperty("OK"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        }

        Assert.assertEquals(ResponseCode,expectedResponse);

        }
    }


