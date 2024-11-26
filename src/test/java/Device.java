public class Device {

    public boolean userBlocked = false;
    public boolean offline = false;
    public boolean realDevice = false;
    public String udid = "";
    public boolean busy = false;
    public String platform = "android";
    public boolean liveStreaming = true ;

    public Device(
        boolean userBlocked,
        boolean offline,
        boolean realDevice,
        String udid,
        boolean busy,
        String platform,
        boolean liveStreaming
    ) {
        this.userBlocked = userBlocked;
        this.offline = offline;
        this.realDevice = realDevice;
        this.udid = udid ;
        this.platform = platform ;
        this.busy = busy;
        this.liveStreaming = liveStreaming ;
    }
}
