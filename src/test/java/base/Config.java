package base;

/**
 * @field baseUrl This is the base url for appium server
 * @field appPackageId This is bundle id or app package name
 * @field appActivity Main activity to be launched in android
 * @field platform either android/ iOS to test each one
 * */
public class Config {
    public String baseUrl;
    public String appPackageId;
    public String appActivity;
    public String platform;

    public Config() {
        this.baseUrl = "http://host.docker.internal:4723/";
        this.appPackageId = "com.alfamart.alfagift.debug";
        this.appActivity = "com.alfamart.alfagift.screen.splash.SplashActivity";
        this.platform = "android";
    }

    public Config(String baseUrl, String appPackageId, String appActivity, String platform) {
        this.baseUrl = baseUrl;
        this.appPackageId = appPackageId;
        this.appActivity = appActivity;
        this.platform = platform;
    }
}