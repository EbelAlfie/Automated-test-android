package testmodule;

import base.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import models.Device;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.DeviceFarmService;

import java.net.MalformedURLException;
import java.net.URI;

public class ParallelDataProviderTest {
    private Config config = new Config();
    IOSDriver driver = null;

    @DataProvider(name = "data-provider", parallel = true)
    public Object[] dataProviderMethod() {
        Config config = new Config();
        DeviceFarmService deviceService = new DeviceFarmService(config);
        Device[] devices = deviceService.getAvailableDevices();
        return devices;
    }

    @Test(dataProvider = "data-provider")
    public void testMethod(Device device) {
        IOSTest iosTest = new IOSTest(config);

        System.out.println("Test data: " + "device" + " running on thread: " + Thread.currentThread().getId());
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:automationName", "XCUITest");

        caps.setCapability("appium:bundleId", config.appPackageId);
        caps.setCapability("appium:udid", device.udid);

        IOSDriver driver = null;
        try {
            driver = new IOSDriver(
                    URI.create(config.baseUrl + "wd/hub").toURL(),
                    caps
            );
            driver.findElement(new AppiumBy.ByAccessibilityId("ic chat")).click();
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        }
    }

    @AfterTest
    void tearDown(){
        if (null != driver) {
            driver.quit();
        }
    }
}

