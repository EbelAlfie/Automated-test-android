import base.BaseTestModule;
import base.Config;
import models.Device;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.DeviceFarmService;
import testmodule.AndroidTest;
import testmodule.IOSTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEntryPoint {

    private final Config config = new Config();
//    private final List<BaseTestModule> modules = new ArrayList<>();

    @DataProvider(name = "device-provider", parallel = true)
    public Object[] devicesProvider() {
        DeviceFarmService deviceService = new DeviceFarmService(config);
        Device[] devices = deviceService.getAvailableDevices();
        System.out.println(">>> Device providerrr");
        return devices;
    }

    @Test(dataProvider = "device-provider")
    public void testMethod(Device device) {
        BaseTestModule testModule;
        if(device.platform.equals("ios")) {
            testModule = new IOSTest(config);
        } else {
           testModule = new AndroidTest(config);
        }

       testModule.runTest(device);
    }

    @AfterTest
    void tearDown() {
//        modules.forEach(BaseTestModule::afterTest);
    }
}
