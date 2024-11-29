package base;

public class Config {
    public String baseUrl;
    public String appPackageId;
    public String appActivity;
    public String platform;

    public Config() {
        this.baseUrl = "http://localhost:4723/";
        this.appPackageId = "com.alfamart.alfagift.debug";
        this.appActivity = "com.hehe.presentation.splashscreen.SplashScreenActivity";
        this.platform = "iOS";
    }

    public Config(String baseUrl, String appPackageId, String appActivity, String platform) {
        this.baseUrl = baseUrl;
        this.appPackageId = appPackageId;
        this.appActivity = appActivity;
        this.platform = platform;
    }
}