package base;

public record Config(
 String BaseUrl,
 String appPackageId,
 String appActivity,
 String platform
) {

    public Config() {
        this(
                "http://localhost:4723/",
                "com.alfamart.alfagift.debug",
                "com.hehe.presentation.splashscreen.SplashScreenActivity",
                "iOS"
        );
    };
}