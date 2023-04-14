package Tests.TestCases.Register;

import Tests.Base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.text.DecimalFormat;
import java.time.Duration;

public class Registration extends BaseClass
{
    String apiUrl = "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/sign_up";
    String filePath= "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\Registration.xlsx";

   @DataProvider(name = "RegisterData")
    public Object[][] testData() {
       return readExcelData(filePath);
    }

    private Object[][] readExcelData(String filePath) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Register_Data");
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            data = new Object[rowCount][colCount];
            for (int i = 1; i <= rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {

                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                        } else {
                            data[i - 1][j] = cell.getStringCellValue();
                        }
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }
            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Test(dataProvider = "RegisterData")
    public void register(String email, String Confirm_Email, String first_name, String last_name, String password, String confirm_password, String device_token, String app_version, String platform, String os_version, String device_model, String mobile_number, String designation_id, String zone_id, String ward_id, String face_id, String department_id, String sub_department_id, String employee_id, String paybill_no) throws InterruptedException {

        double mobile_number_double = Double.parseDouble(mobile_number);

        DecimalFormat df = new DecimalFormat("#");
        String mobile_NUMBER = df.format(mobile_number_double);
        System.out.println(mobile_NUMBER); // Output: 123456789

        driver.findElement(By.xpath(locators.getProperty("register"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.findElement(By.xpath(locators.getProperty("SelectCity"))).click(); // Change the name of City from locators If you want to change. Currently is for Ahmedabad
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

        driver.findElement(By.xpath(locators.getProperty("FirstName"))).sendKeys(first_name);
        driver.findElement(By.xpath(locators.getProperty("LastName"))).sendKeys(last_name);
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

        driver.findElement(By.xpath(locators.getProperty("VErify"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(locators.getProperty("VErify"))).click();
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

        driver.findElement(By.xpath(locators.getProperty("SelectZone"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("ZonePath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("SelectWard"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("WardPath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));


        driver.findElement(By.xpath(locators.getProperty("SelectDesignation"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("DesignationPath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));


        driver.findElement(By.xpath(locators.getProperty("SelectDepartment"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("DepartmentPath"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        driver.findElement(By.xpath(locators.getProperty("Register_button"))).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

      WebElement Success=  driver.findElement(By.xpath(locators.getProperty("UserNotExist")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Success.click();

        if(Success.isDisplayed()) {
            driver.findElement(By.xpath(locators.getProperty("UserNotExist"))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }else {
            driver.findElement(By.xpath(locators.getProperty(""))).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        }
        register_API(email,Confirm_Email,first_name,last_name,password, confirm_password, device_token, app_version,platform,os_version,device_model, mobile_number, designation_id, zone_id, ward_id, face_id, department_id,sub_department_id, employee_id, paybill_no );


    }


    public void register_API(String email,String Confirm_Email ,String first_name, String last_name, String password, String confirm_password, String device_token, String app_version, String platform, String os_version, String device_model, String mobile_number, String designation_id, String zone_id, String ward_id, String face_id,String department_id, String sub_department_id, String employee_id, String paybill_no ){

        String filePath = "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\Profile\\QA1.jpg";
        File file = new File(filePath);

        RequestSpecification request = RestAssured.given();
        request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
        request.header("fromapp", "2");
        request.header("appid", "3");

        // Create the multipart request body
        RequestSpecification multiPart = request.multiPart("avatar", file, "image/jpeg");
        multiPart.multiPart("email", email);
        multiPart.multiPart("Confirm_Email",Confirm_Email);
        multiPart.multiPart("first_name", first_name);
        multiPart.multiPart("last_name", last_name);
        multiPart.multiPart("password", password);
        multiPart.multiPart("confirm_password", confirm_password);
        multiPart.multiPart("device_token", device_token);
        multiPart.multiPart("app_version", app_version);
        multiPart.multiPart("platform", platform);
        multiPart.multiPart("os_version", os_version);
        multiPart.multiPart("device_model", device_model);
        multiPart.multiPart("mobile_number", mobile_number);
        multiPart.multiPart("designation_id", designation_id);
        multiPart.multiPart("zone_id", zone_id);
        multiPart.multiPart("ward_id", ward_id);
        multiPart.multiPart("face_id", face_id);
        multiPart.multiPart("department_id", department_id);
        multiPart.multiPart("employee_id", employee_id);
        multiPart.multiPart("paybill_no", paybill_no);

        Response res1 = multiPart.post(apiUrl);
        int res = res1.getStatusCode();
        System.out.println(res);
        String D = res1.getBody().asPrettyString();
        System.out.println(D);
        Assert.assertEquals(res,200);
    }
}
