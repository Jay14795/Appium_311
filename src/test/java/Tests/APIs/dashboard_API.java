package Tests.APIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class dashboard_API
{
   String CheckUpgrade = "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/checkupgrade";
   String GetStaticData= "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/getstaticdata";

   @Test
    public int CheckUpgrade() {

        int StatusCode = 0;
        try (Scanner scanner = new Scanner(new File("auth_token.txt"))) {
            String authToken;
            authToken = scanner.nextLine();
            System.out.println(authToken);

            RequestSpecification request = RestAssured.given();
            request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
            request.header("fromapp", "2");
            request.header("appid", "3");
            request.header("Authorization", "Bearer" + " " + authToken);

            Response response = request.post(CheckUpgrade);
            StatusCode = response.getStatusCode();
            System.out.println(StatusCode);
            String ResponseBody = response.getBody().asPrettyString();
            System.out.println(ResponseBody);
        } catch (FileNotFoundException e) {
            // Handle the file not found exception
        }
        return StatusCode;
    }


    @Test
    public void Get_StaticData() throws FileNotFoundException {

        String[] types = {"acl_officer_lists"};
        //We can Find sub_zones, departments,categories,sub_categories, offices, jurisdictions, designation, issue_status_codes, priorities, issue_tags,channels, leave_types, user_roles, task_status_codes, task_categories, task_sub_categories, vendor_status, vendor_relations, magistrate_offices, challan_status_codes, rwa_offices, toilets, checklists, dustbins, parkings, data_capture_entities, stf_police_stations, gvp_points, work_types, swachta_survey_questions, checklist_categories, agencies, acl_officer_lists, navigation_menu

        try (Scanner scanner = new Scanner(new File("auth_token.txt"))) {
            String authToken;
            authToken = scanner.nextLine();
            System.out.println(authToken);

            RequestSpecification request = RestAssured.given();
            request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
            request.header("fromapp", "2");
            request.header("appid", "3");
            request.header("Authorization", "Bearer" +" "+authToken);

            for (String value : types) {
                request.param("types", value);
            }

            Response response = request.post(GetStaticData);
            int StatusCode = response.getStatusCode();
            System.out.println(StatusCode);
            String ResponseBody = response.getBody().asPrettyString();
            System.out.println(ResponseBody);
        }
    }
}
