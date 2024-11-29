package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import models.Device;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class IOSTest extends ConfigConsumer implements BaseTestModule {
    IOSDriver driver = null;

    public IOSTest(Config config) {super(config);}

    @Override
    public void beforeTest() {

    }

    @Override
    public boolean runTest(Device device) {
        boolean testStatus = false;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:automationName", "XCUITest");

        caps.setCapability("appium:bundleId", config.appPackageId);
        caps.setCapability("appium:udid", device.udid);

        try {
            driver = new IOSDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    caps
            );
            driver.findElement(new AppiumBy.ByAccessibilityId("ic chat")).click();
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
