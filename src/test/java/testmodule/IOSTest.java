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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

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
        caps.setCapability("appium:wdaLocalPort", device.wdaLocalPort);

        try {
            driver = new IOSDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    caps
            );

            login(driver);
           // addToCart(driver);

            testStatus = true ;
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        }

        return testStatus;
    }

    private void login(IOSDriver driver) {
        driver.findElement(By.id("LOGIN")).click();
        String phoneNumber = "type == 'XCUIElementTypeTextField' AND value BEGINSWITH[c] 'Phone/Membership card number' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(phoneNumber)).click();
        driver.findElement(AppiumBy.iOSNsPredicateString(phoneNumber)).sendKeys("081586196863");

        String password = "type == 'XCUIElementTypeSecureTextField' AND value BEGINSWITH[c] 'Password' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(password)).click();
        driver.findElement(AppiumBy.iOSNsPredicateString(password)).sendKeys("Kelsha123!");

        driver.findElement(By.id("Done")).click();

        String btnNext = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Next' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(btnNext)).click();

        String maps = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Next' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(btnNext)).click();

        String savePassword = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Next' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(btnNext)).click();


        //maps
        //pass

        String btnLater = "type == 'XCUIElementTypeButton' AND value BEGINSWITH[c] 'Later' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(btnLater)).click();
    }

    private void addToCart(IOSDriver driver) {
        String promo = "type == 'XCUIElementTypeButton' AND value BEGINSWITH[c] 'Promo' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(promo)).click();

        String addToCart = "type == 'XCUIElementTypeButton' AND value BEGINSWITH[c] 'Add to Cart' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(addToCart)).click();

        String basket = "type == 'XCUIElementTypeButton' AND value BEGINSWITH[c] 'Basket' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(basket)).click();





//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("clickable_promo")));
//        driver.findElement(AppiumBy.id("clickable_promo")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("buttonCancel")));
//        driver.findElement(AppiumBy.id("buttonCancel")).click();
//        driver.findElement(AppiumBy.id("btn_add_to_cart")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("btn_add_to_cart")));
//        driver.findElement(AppiumBy.id("btn_increase_qty")).click();
    }

    @Override
    public void afterTest() {
        driver.quit();
    }
}
