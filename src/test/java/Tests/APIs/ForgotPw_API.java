    package Tests.APIs;

    import io.restassured.RestAssured;
    import io.restassured.response.Response;
    import io.restassured.specification.RequestSpecification;
    import org.json.JSONException;
    import org.json.JSONObject;
    public class ForgotPw_API
    {
        String apiUrl = "http://api-officer.silicontechnolabs.com:9017/api/v1/auth/forgot_password";

        public String forgotPw_Api(String email, String expectedResponse) throws JSONException {
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
            String ResponseCode = Integer.toString(res);
            System.out.println("RESPONSE CODE IS" + " " + ResponseCode);

            String ResponseBody = response.getBody().jsonPath().getString("NOTIFICATION").toString();
            System.out.println("Response Body is" + " " + ResponseBody);

            return ResponseBody;
        }
    }
