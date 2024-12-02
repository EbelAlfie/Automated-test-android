package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

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
        options.setCapability("deviceName", "e1sxxx");
        options.setCapability("udid", "RRCX30BP22A");
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
//            login(driver);
//            addToCart(driver);
//
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
        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/iv_basket")).click();
    }

    private void addToCart(AndroidDriver driver) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.alfamart.alfagift.debug:id/tv_see_all\"))"));

        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/tv_see_all")).click();
    }

    private void login(AndroidDriver driver) {
        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.alfamart.alfagift.debug:id/btn_login")));
        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/btn_login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.alfamart.alfagift.debug:id/et_phone")));
        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/et_phone")).sendKeys("081586196863");
        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/et_password")).sendKeys("Kelsha123!");
        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/btn_next")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.alfamart.alfagift.debug:id/btn_later")));
        driver.findElement(AppiumBy.id("com.alfamart.alfagift.debug:id/btn_later")).click();
    }

//            driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();

    @Override
    public void afterTest() {
        driver.quit();
    }
}
