package Tests.Base;

import android.content.SharedPreferences;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static android.content.Context.MODE_PRIVATE;

public class BaseClass {
    public static AndroidDriver driver;
    public static Properties prop = new Properties();
    public static Properties locators = new Properties();
    public static FileReader fr;
    public static FileReader fr1;
    public static String AuthToken;

    public static String getAuthToken() {
        return AuthToken;
    }

    @BeforeClass
    public void Setup() throws IOException {
        if (driver == null) {
            fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\ConfigFiles\\config.properties");
            fr1 = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\ConfigFiles\\locators.properties");
            prop.load(fr);
            locators.load(fr1);

            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("DEVICE_NAME", "Redmi note 11"); // Android Emulator
            dc.setCapability("PLATFORM_NAME", "Android");
            dc.setCapability("UDID", "cb1dfd9a");
            dc.setCapability("PLATFORM_VERSION", "12 SKQ1.211103.001");
            dc.setCapability("appPackage", "com.infoweb.smartcity311.demo");
            dc.setCapability("appActivity", "com.infoweb.smartcity311.demo.MainActivity");
            dc.setCapability("autoGrantPermissions", true);
            dc.setCapability("newCommandTimeout", 0);
            dc.setCapability("noReset", true);
            dc.setCapability("–session-override",true);
            dc.setCapability("authToken", AuthToken);

            // (you can get it from apk info app)

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(url, dc);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            String currentPackage = driver.getCurrentPackage();
            driver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + currentPackage + " android.permission.READ_EXTERNAL_STORAGE"));
            driver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + currentPackage + " android.permission.WRITE_EXTERNAL_STORAGE"));
            driver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + currentPackage + " android.permission.CAMERA"));

            // Set up the network interception using Charles Proxy
            // driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver instance
        driver.quit();
    }
}
