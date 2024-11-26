import base.Config;
import models.Device;
import service.DeviceFarmService;
import testmodule.AndroidTest;
import testmodule.IOSTest;

import java.util.Arrays;

public class TestEntryPoint {

    public static void main(String[] args) {
        Config config = new Config();

        //Services
        DeviceFarmService deviceService = new DeviceFarmService(config);
        Device[] devices = deviceService.getAvailableDevices();

        //Appium instance
        if (config.platform().equals("iOS")) {
            IOSTest iosTest = new IOSTest(config);
            Arrays.stream(devices).forEach(iosTest::newThread);
        } else {
            AndroidTest android = new AndroidTest(config);
            Arrays.stream(devices).forEach(android::newThread);
        }
    }
}
