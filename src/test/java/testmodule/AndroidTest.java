package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.List;

public class AndroidTest extends ConfigConsumer implements BaseTestModule {
    AndroidDriver driver = null;

    public AndroidTest(Config config) {super(config);}

    @Override
    public void beforeTest() {

    }

    @Override
    public boolean runTest() {
        boolean testStatus = false;

        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage(config.appPackageId);

        options.setCapability("platformName", "Android");
        options.setCapability("appium:automationName","UiAutomator2");
        options.setCapability("deviceName", "jackpotltexx");
        options.setCapability("udid", "5200802feeb735cf");
        options.setCapability("appPackage", "com.alfamart.alfagift.debug");
        options.setCapability("appActivity", "com.alfamart.alfagift.screen.splash.SplashActivity");


        try {
            driver = new AndroidDriver(
                    URI.create(this.config.baseUrl ).toURL(),
                    options
            );

            //Test Case 1: Login
            login(driver);

//            //Test Case 2: Add to Cart
            addToCart(driver);

//            //Test Case 3: Check Out
//            login(driver);
//            addToCart(driver);
//            checkout(driver);
            

            testStatus = true ;
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        }

        return testStatus;
    }

    private void checkout(AndroidDriver driver) {
        driver.findElement(AppiumBy.id("iv_basket")).click();
    }

    private void addToCart(AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("clickable_promo")));
        driver.findElement(AppiumBy.id("clickable_promo")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("buttonCancel")));
        driver.findElement(AppiumBy.id("buttonCancel")).click();
        driver.findElement(AppiumBy.id("btn_add_to_cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_add_to_cart")));
        driver.findElement(AppiumBy.id("btn_increase_qty")).click();
    }

    private void login(AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_login")));
        driver.findElement(AppiumBy.id("btn_login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button")));
        driver.findElement(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_phone")));
        driver.findElement(AppiumBy.id("et_phone")).sendKeys("081586196863");
        driver.findElement(AppiumBy.id("et_password")).sendKeys("Kelsha123!");
        driver.findElement(AppiumBy.id("btn_next")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_later")));
        driver.findElement(AppiumBy.id("btn_later")).click();
    }

    @Override
    public void afterTest() {
        driver.quit();
    }
}
