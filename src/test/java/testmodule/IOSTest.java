package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import models.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestStatus;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.net.URL;
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
        caps.setCapability("appium:wdaLocalPort", device.wdaLocalPort);

        try {
            driver = new IOSDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    caps
            );

            //Test Case 1: Login
            login(driver);

            //Test Case: Add to Cart
            addToCart(driver);

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
        driver.findElement(By.id("LOGIN")).click();
        String phoneNumber = "type == 'XCUIElementTypeTextField' AND value BEGINSWITH[c] 'Phone/Membership card number' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(phoneNumber)).click();
        driver.findElement(AppiumBy.iOSNsPredicateString(phoneNumber)).sendKeys("082138894119");

        String password = "type == 'XCUIElementTypeSecureTextField' AND value BEGINSWITH[c] 'Password' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(password)).click();
        driver.findElement(AppiumBy.iOSNsPredicateString(password)).sendKeys("Axel123456789");

        driver.findElement(By.id("Done")).click();

        String btnNext = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Next' AND visible == 1";
        driver.findElement(AppiumBy.iOSNsPredicateString(btnNext)).click();
    }

    private void addToCart(IOSDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement promoButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.iOSNsPredicateString("name == 'Promo' AND label == 'Promo' AND type == 'XCUIElementTypeButton'")
        ));
        promoButton.click();

        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.iOSNsPredicateString("name == '+ Basket' AND label == '+ Basket' AND type == 'XCUIElementTypeButton'")
        ));
        addToCart.click();

        WebElement addItem = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.iOSNsPredicateString("name == 'ic plus new' AND label == 'ic plus new' AND type == 'XCUIElementTypeButton'")
        ));
        addItem.click();
    }

    @Override
    public void afterTest() {
        driver.quit();
    }
}
