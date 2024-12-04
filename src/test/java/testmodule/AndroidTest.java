package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import models.Device;
import utils.TestStatus;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class AndroidTest extends ConfigConsumer implements BaseTestModule {
    AndroidDriver driver = null;

    public AndroidTest(Config config) {
        super(config);
    }

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
        options.setCapability("appium:systemPort", device.systemPort);
        options.setCapability("appium:adbPort", device.adbPort);
        options.setCapability("appPackage", config.appPackageId);
        options.setCapability("appActivity", config.appActivity);

        try {
            driver = new AndroidDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    options
            );
            driver.findElement(new AppiumBy.ByAccessibilityId("btn_chat")).click();
            testStatus = true;
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
            System.out.println("Yahh " + e1.getMessage());
        } finally {
            driver.executeScript(
                    "devicefarm: setSessionStatus",
                    Map.of("status", TestStatus.getTestStatus(testStatus))
            ) ;
        }

        return testStatus;
    }

    @Override
    public void afterTest() {
        driver.quit();
    }
}
