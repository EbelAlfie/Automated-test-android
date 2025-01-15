//import org.example.test.base.BaseTestModule;
//import org.example.test.base.Config;
//import org.example.test.models.Device;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import org.example.test.service.DeviceFarmService;
//import org.example.test.testdriver.AndroidTest;
//import org.example.test.testdriver.IOSTest;
//
//import java.util.Arrays;
//
//public class TestEntryPoint {
//
//    private final Config config = new Config();
////    private final List<BaseTestModule> modules = new ArrayList<>();
//
//    @DataProvider(name = "device-provider", parallel = true)
//    public Object[] devicesProvider() {
//        DeviceFarmService deviceService = new DeviceFarmService(config);
//        Device[] devices = deviceService.getAvailableDevices();
//        System.out.println(">>> Available Devices");
//        Arrays.stream(devices).forEach(item -> System.out.println(item.udid));
//        return Arrays.stream(devices).toArray() ;
//    }
//
//    @Test(
//            dataProvider = "device-provider",
//            threadPoolSize = 4
//    )
//    public void testMethod(Device device) {
//        BaseTestModule testModule;
//        if(device.platform.equals("ios")) {
//            testModule = new IOSTest(config);
//        } else {
//           testModule = new AndroidTest(config);
//        }
//
//       testModule.runTest(device);
//    }
//
//    @AfterTest
//    void tearDown() {
////        modules.forEach(BaseTestModule::afterTest);
//    }
//}
