package models;

public record Device(
        boolean userBlocked,
        boolean offline,
        boolean realDevice,
        String udid,
        boolean busy,
        String platform,
        boolean liveStreaming
) {

    /** Default values */
    public Device() {
        this(
                false,
                false,
                false,
                "",
                false,
                "",
                false
        );
    }
}
