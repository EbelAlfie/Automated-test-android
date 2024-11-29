package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import models.Device;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;

public class AndroidTest extends ConfigConsumer implements BaseTestModule {
    AndroidDriver driver = null;

    public AndroidTest(Config config) {super(config);}

    @Override
    public void beforeTest() {

    }

    @Override
    public boolean runTest(Device device) {
        boolean testStatus = false;

        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage(config.appPackageId);

        options.setCapability("platformName", "Android");
        options.setCapability("appium:udid", device.udid);
        options.setCapability("appPackage", config.appPackageId);
        options.setCapability("appActivity", config.appActivity);

        try {
            driver = new AndroidDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    options
            );
            driver.findElement(new AppiumBy.ByAccessibilityId("btn_chat")).click();
            testStatus = true ;
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        }

        return testStatus;
    }

    @Override
    public void afterTest() {
        driver.quit();
    }
}
