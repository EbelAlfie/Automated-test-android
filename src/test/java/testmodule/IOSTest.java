package testmodule;

import base.BaseTestModule;
import base.Config;
import base.ConfigConsumer;
import models.Device;

public class IOSTest extends ConfigConsumer implements BaseTestModule {

    protected IOSTest(Config config) {super(config);}

    @Override
    public boolean runTest(Device device) {
        return false;
    }

    @Override
    public void newThread(Device device) {

    }
}
