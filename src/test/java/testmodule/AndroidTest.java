package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import models.Device;

import java.net.MalformedURLException;
import java.net.URI;

public class AndroidTest extends ConfigConsumer implements BaseTestModule {

    public AndroidTest(Config config) {super(config);}

    @Override
    public boolean runTest(Device device) {
        Boolean testStatus = false ;

        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage(config.appPackageId());

        options.setCapability("platformName", "Android");
        options.setCapability("appium:udid", device.udid());
        options.setCapability("appPackage", config.appPackageId());
        options.setCapability("appActivity", config.appActivity());

        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(
                    URI.create(this.config + "wd/hub").toURL(),
                    options
            );
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
