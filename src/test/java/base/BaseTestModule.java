package base;

import models.Device;

public interface BaseTestModule {

    public void beforeTest() ;
    /**
     *
     * @param device as single device instance
     * @return true if test is run and false if it fails to run
     */
    public boolean runTest(Device device);

    public void afterTest() ;
}
