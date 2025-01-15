package org.example.test.testdriver;

import org.example.test.base.Config;
import org.example.test.base.ConfigConsumer;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.test.models.Device;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URI;

@Configuration
public class AndroidTest extends ConfigConsumer {
    public static Device device;

    public static AndroidDriver driver = null;

    public AndroidTest() {
        super(new Config());
    }

    public void runTest(Device device) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage(config.appPackageId);

        options.setCapability("platformName", "Android");
        options.setCapability("appium:udid", device.udid);
        options.setCapability("appium:systemPort", device.systemPort);
        options.setCapability("appium:adbPort", device.adbPort);
        options.setCapability("appPackage", config.appPackageId);
        options.setCapability("appActivity", config.appActivity);
        options.setCapability("mobile: startActivity", config.appActivity);

        try {
            driver = new AndroidDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    options
            );

        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
            System.out.println("Yahh " + e1.getMessage());
        }

    }

//    private void addToCart(AndroidDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("clickable_promo")));
//        driver.findElement(AppiumBy.id("clickable_promo")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("buttonCancel")));
//        driver.findElement(AppiumBy.id("buttonCancel")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_add_to_cart")));
//        driver.findElement(AppiumBy.id("btn_add_to_cart")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_add_to_cart")));
//        driver.findElement(AppiumBy.id("btn_increase_qty")).click();
//    }
//
//    private void login(AndroidDriver driver) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
////        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")));
////        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_login")));
//        driver.findElement(AppiumBy.id("btn_login")).click();
////        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button")));
////        driver.findElement(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_phone")));
//        driver.findElement(AppiumBy.id("et_phone")).sendKeys("08978579751");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_password")));
//        driver.findElement(AppiumBy.id("et_password")).sendKeys("Cacing007");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_next")));
//        driver.findElement(AppiumBy.id("btn_next")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_later")));
//        driver.findElement(AppiumBy.id("btn_later")).click();
//    }
}
