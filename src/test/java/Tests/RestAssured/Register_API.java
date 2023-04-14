package Tests.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
public class Register_API {
        @Test
        public void registerAPI() throws IOException {

                String filePath = "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\Profile\\QA1.jpg";
                File file = new File(filePath);

                RequestSpecification request = RestAssured.given();
                request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
                request.header("fromapp", "2");
                request.header("appid", "3");

                // Create the multipart request body
                RequestSpecification multiPart = request.multiPart("avatar", file, "image/jpeg");
                multiPart.multiPart("email", "abraham_bartell2@gmail.com");
                multiPart.multiPart("first_name", "Abraham");
                multiPart.multiPart("last_name", "Bartell");
                multiPart.multiPart("password", "Qa@123456");
                multiPart.multiPart("confirm_password", "Qa@123456");
                multiPart.multiPart("device_token", "abcdefghijklmnopqrstuvwxy");
                multiPart.multiPart("app_version", "3.0");
                multiPart.multiPart("platform", "300");
                multiPart.multiPart("os_version", "3.0");
                multiPart.multiPart("device_model", "Redmi Note 11");
                multiPart.multiPart("mobile_number", "9988552201");
                multiPart.multiPart("designation_id", "38");
                multiPart.multiPart("zone_id", "161");
                multiPart.multiPart("ward_id", "263");
                multiPart.multiPart("face_id", "abcdefgh");
                multiPart.multiPart("department_id", "26");
                multiPart.multiPart("employee_id", "15");
                multiPart.multiPart("paybill_no", "25885");

                Response res1 = multiPart.post("http://api-officer.silicontechnolabs.com:9017/api/v1/auth/signup");
                int res = res1.getStatusCode();
                System.out.println(res);
                String D = res1.getBody().asPrettyString();
                System.out.println(D);
                Assert.assertEquals(res,200);
        }
}