package base;

import models.Device;

public interface BaseTestModule {

    public void beforeTest() ;
    /**
     *
     * @return true if test is run and false if it fails to run
     */
    public boolean runTest();

    public void afterTest() ;
}
