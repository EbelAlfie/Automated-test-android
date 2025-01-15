package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.example.test.testdriver.AndroidTest;
import org.example.test.utils.TestStatus;
import java.util.Map;

public class TestDriver {

  @Before
  public void beforeTest() {

  }

  @After
  public void afterTest(Scenario scenario) {
    AndroidTest.driver.executeScript(
            "devicefarm: setSessionStatus",
            Map.of("status", TestStatus.getTestStatus(!scenario.isFailed()))
    ) ;
  }
}
