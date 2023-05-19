package Tests.APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;

public class Register_API {

        public int registerAPI(String email, String Confirm_Email, String first_name, String last_name, String password, String confirm_password, String device_token, String app_version, String platform, String os_version, String device_model, String mobile_number, String designation_id, String zone_id, String ward_id, String face_id, String department_id, String sub_department_id, String employee_id, String paybill_no) throws InterruptedException {
                String filePath = "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\Profile\\QA1.jpg";
                File file = new File(filePath);

                RequestSpecification request = RestAssured.given();
                request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
                request.header("fromapp", "2");
                request.header("appid", "3");

                // Create the multipart request body
                RequestSpecification multiPart = request.multiPart("avatar", file, "image/jpeg");
                multiPart.multiPart("email", email);
                multiPart.multiPart("Confirm_Email", Confirm_Email);
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

                Response res1 = multiPart.post("http://api-officer.silicontechnolabs.com:9017/api/v1/auth/signup");
                int StatusCode = res1.getStatusCode();
                System.out.println(StatusCode);
                String ResponseBody = res1.getBody().asPrettyString();
                System.out.println(ResponseBody);

                return StatusCode;
        }
}