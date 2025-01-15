import org.example.test.models.Device;
import org.example.test.service.DeviceFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.example.test.utils.XmlCreator;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DeviceFarmRunner extends AbstractTestNGSpringContextTests {
  private Device[] availableDevices = new Device[0] ;
  private String[] scenarioTags = new String[0];

  @Autowired
  private DeviceFarmService service;

  @Autowired
  private XmlCreator factory;

  @Value("${suite.file.path}")
  private String filePath ;

  @BeforeClass
  @Parameters({ "cucumber.filter.tags" })
  public void getConnectedDevice(@Optional(value = "cucumber.filter.tags") String tag) {
    scenarioTags = tag.split("\\.");
    Arrays.stream(scenarioTags).forEach(System.out::println);
    availableDevices = service.getAvailableDevices();
  }

  @Test
  public void runTest() {
    generateXml(scenarioTags);
  }

  private void generateXml(String[] tags) {
    int totalDevice = availableDevices.length;

//        if (totalDevice == 0) throw new Exception("No device found");
//        if (tags.length == 0) throw new Exception("No test tag specified");

    XmlSuite xmlSuite = factory.createXmlSuite(totalDevice);
    for (int i = 0; i < totalDevice; i++) {

      String tag ;
      try {
        tag = tags[i];
      } catch (IndexOutOfBoundsException e) {
        tag = tags[tags.length - 1] ;
      }

      XmlTest test = factory.createXmlTest(tag, xmlSuite, availableDevices, i);

      XmlClass xmlClass = new XmlClass();
      xmlClass.setName("MainRunner");

      test.setXmlClasses(List.of(xmlClass));
    }
    factory.saveXml(xmlSuite, filePath);
  }
}
