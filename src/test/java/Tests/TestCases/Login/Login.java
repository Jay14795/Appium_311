package Tests.TestCases.Login;

import Tests.Base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.lang.reflect.Method;
import java.time.Duration;
public class Login extends BaseClass {

    String apiUrl = "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/sign_in";

    @DataProvider(name = "LoginData")
    public Object[][] getData(Method m) throws IOException {
        String excelSheetName = m.getName();
        System.out.println("ExcelSheetName is" + " " + excelSheetName);
        File f = new File(System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\TestData\\LoginData.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet SheetName = wb.getSheet(excelSheetName);

        int totalRows = SheetName.getLastRowNum();
        System.out.println(totalRows);
        Row rowCells = SheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();
        System.out.println(totalCols);

        DataFormatter format = new DataFormatter();

        String[][] testData = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = format.formatCellValue(SheetName.getRow(i).getCell(j));
                System.out.println(testData[i - 1][j]);
            }
        }
        return testData;

    }

    @Test(dataProvider = "LoginData")
    public void login(String login_id, String password, String expectedResponse) {
        WebElement emailInput = driver.findElement(By.xpath(locators.getProperty("Email")));
        emailInput.sendKeys(login_id);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebElement passwordInput = driver.findElement(By.xpath(locators.getProperty("Password")));
        passwordInput.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebElement loginButton = driver.findElement(By.xpath(locators.getProperty("LoginButton")));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        loginAPI(login_id,password,expectedResponse);
        
    }


    public void loginAPI(String login_id, String password, String expectedResponse) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
        request.header("fromapp", "2");

        JSONObject json = new JSONObject();
        json.put("device_token", "e7J66IWsToCrxtMZLmeYge:APA91bElYLLmvZ3Mtai6RvRrRsIHZXO-lNABKqN56bMaZCuuE2LTCcoX2M1t9WAzmQ19eUGhQmxsWHgAp8eGSRNilALD9H7ifsaKwIVLgc1D2Du1NtQYkJaRfQcmLM6pb0XflvJWU0ld");
        json.put("app_version", "124");
        json.put("platform", "300");
        json.put("os_version", "12");
        json.put("device_model", "Redmi note 11");
        json.put("app_version", "124");
        json.put("platform", "300");
        json.put("os_version", "12");
        json.put("device_model", "Redmi note 11");
        json.put("login_id", login_id);
        json.put("password", password);

        String jsonString = json.toString();
        request.body(jsonString);

        Response response = request.post(apiUrl);
        int res = response.getStatusCode();
        String ResponseCode = Integer.toString(res);

        System.out.println("Response Code is" +" "+ResponseCode);
        String body = response.getBody().asPrettyString();
       System.out.println(body);

        System.out.println("Expected Response is"+expectedResponse);
        System.out.println("Actual response is" +res);

        if (res!=200) {
            driver.findElement(By.xpath(locators.getProperty("Error"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            Assert.fail("Login Failed");
        }else {
            System.out.println("Logged In successfully");
        }
        Assert.assertEquals(ResponseCode, expectedResponse);
    }
}

