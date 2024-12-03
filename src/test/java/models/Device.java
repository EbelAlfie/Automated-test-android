package models;

public class Device {
    public boolean userBlocked;
    public int adbPort;
    public int systemPort;
    public boolean offline;
    public boolean realDevice;
    public String udid;
    public boolean busy;
    public String platform;
    public boolean liveStreaming;

    public Device() {
        this.userBlocked = false;
        this.adbPort = 0;
        this.systemPort = 0;
        this.offline = false;
        this.realDevice = false;
        this.udid = "";
        this.busy = false;
        this.platform = "";
        this.liveStreaming = false;
    }

    public Device(
            boolean userBlocked,
            int adbPort,
            int systemPort,
            boolean offline,
            boolean realDevice,
            String udid,
            boolean busy,
            String platform,
            boolean liveStreaming
    ) {
        this.userBlocked = userBlocked;
        this.adbPort = adbPort;
        this.systemPort = systemPort;
        this.offline = offline;
        this.realDevice = realDevice;
        this.udid = udid;
        this.busy = busy;
        this.platform = platform;
        this.liveStreaming = liveStreaming;
    }
}
