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

    public IOSTest(Config config) {super(config);}

    @Override
    public boolean runTest(Device device) {
        Boolean testStatus = false;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:automationName", "XCUITest");

        caps.setCapability("appium:bundleId", config.appPackageId());
        caps.setCapability("appium:udid", device.udid());

        IOSDriver driver = null;
        try {
            driver = new IOSDriver(
                    URI.create(this.config.BaseUrl() + "wd/hub").toURL(),
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
    public void newThread(Device device) {
        Runnable runnable = () -> runTest(device);

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
