import base.BaseTestModule;
import base.Config;
import models.Device;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.DeviceFarmService;
import testmodule.AndroidTest;
import testmodule.IOSTest;

import java.util.ArrayList;
import java.util.List;

public class TestEntryPoint {

    private final Config config = new Config();
    private final List<BaseTestModule> modules = new ArrayList<>();

//    @DataProvider(name = "device-provider", parallel = true)
//    public Object[] devicesProvider() {
//        DeviceFarmService deviceService = new DeviceFarmService(config);
//        Device[] devices = deviceService.getAvailableDevices();
//        return devices;
//    }

    @Test
    public void testMethod() {
        modules.add(new IOSTest(config));
//        modules.add(new AndroidTest(config));

        modules.forEach(item ->
                item.runTest()
        );
    }

    @AfterTest
    void tearDown() {
        modules.forEach(BaseTestModule::afterTest);
    }
}
