package Tests.TestCases.Dashboard;
import Tests.APIs.Login_API;
import Tests.APIs.dashboard_API;
import Tests.Base.BaseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dashboard extends BaseClass
{
    public void dash() throws FileNotFoundException {
        try (
            Scanner scanner = new Scanner(new File("auth_token.txt"))) {
            String authToken;
            authToken = scanner.nextLine();
            System.out.println(authToken);

            dashboard_API Object = new dashboard_API();
            Object.CheckUpgrade();

            dashboard_API myInstance = new dashboard_API();
            int StatusCode = myInstance.CheckUpgrade();
            System.out.println("STATUSCODE:"+" "+StatusCode);

        }

    }
}
