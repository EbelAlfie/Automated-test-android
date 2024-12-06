package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import models.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestStatus;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

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
        caps.setCapability("appium:systemPort", device.wdaLocalPort);

        try {
            driver = new IOSDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    caps
            );

            login(driver);
//            addToCart(driver);

            testStatus = true ;
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        } finally {
            driver.executeScript(
                    "devicefarm: setSessionStatus",
                    Map.of("status", TestStatus.getTestStatus(testStatus))
            ) ;
        }

        return testStatus;
    }

    private void login(IOSDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LOGIN")));
        driver.findElement(By.id("LOGIN"));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button")));
//        driver.findElement(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_phone")));
//        driver.findElement(AppiumBy.id("et_phone")).sendKeys("087889940451");
//        driver.findElement(AppiumBy.id("et_password")).sendKeys("Fachreza31");
//        driver.findElement(AppiumBy.id("btn_next")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_later")));
//        driver.findElement(AppiumBy.id("btn_later")).click();
    }

//    private void addToCart(IOSDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("clickable_promo")));
//        driver.findElement(AppiumBy.id("clickable_promo")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("buttonCancel")));
//        driver.findElement(AppiumBy.id("buttonCancel")).click();
//        driver.findElement(AppiumBy.id("btn_add_to_cart")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_add_to_cart")));
//        driver.findElement(AppiumBy.id("btn_increase_qty")).click();
//    }

    @Override
    public void afterTest() {
        driver.quit();
    }
}
