package Tests.APIs.Compost_Pit;

import Tests.Base.BaseClass;
import Tests.Utilities.ReadExcelData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class WetWaste_API {
    String CompostPit= "http://api-officer.silicontechnolabs.com:9017/api/v1/compost_pit_reports/create";
    int fromapp= 2;
    int appid= 16;

    @Test(dataProviderClass = ReadExcelData.class, dataProvider = "WetWasteData")
    public String wetWasteAPI(String VehicleNumber, String Weight, String Description) throws JSONException {
        int StatusCode;
        String ResponseBody;
        String token = BaseClass.getAuthToken();

        RequestSpecification request = RestAssured.given();
            request.header("accept","application/json");
            request.header("apikey","g9d54312a5c53ce30738dcd8838c5128");
            request.header("fromapp",fromapp);
            request.header("appid",appid);
            request.header("accept","application/json");
            request.header("Content-Type","application/json");
            request.header("Authorization","Bearer "+token);

            JSONObject json = new JSONObject();
            json.put("vehicle_no",VehicleNumber);
            json.put("weight",Weight);
            json.put("description",Description);
            json.put("compost_pit_code","And 111");
            json.put("report_type","1");
            json.put("final_disposal","Other");
            json.put("longitude","0912252");
            json.put("latitude","28.6008162");
            json.put("compost_pit_id","");

            request.body(json.toString());
            Response response = request.post(CompostPit);
            StatusCode = response.getStatusCode();
            System.out.println(StatusCode);
            ResponseBody = response.getBody().asPrettyString();
            System.out.println(ResponseBody);

        return ResponseBody;
    }
}

