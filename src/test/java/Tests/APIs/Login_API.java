package Tests.APIs;
import Tests.Base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_API {
        String apiUrl = "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/sign_in";
        String authToken= BaseClass.AuthToken;
        public String loginAPI(String login_id, String password, String expectedResponse) throws JSONException {

                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");
                request.header("apikey", "g9d54312a5c53ce30738dcd8838c5128");
                request.header("fromapp", "2");

                if(authToken != null) {
                        request.header("Authorization", "Bearer " + authToken);
                }else
                {
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
                json.put("login_id",login_id );
                json.put("password",password);

                String jsonString = json.toString();
                request.body(jsonString);
                }


                Response response = request.post(apiUrl);
                int StatusCode = response.getStatusCode();
                String ResponseCode = Integer.toString(StatusCode);
                System.out.println("Response Code is " + " " + ResponseCode);
                String body = response.getBody().asPrettyString();
                System.out.println("Body Response is " + " " + body);

                return body;

        }
}
