package base;

public record Config(
 String BaseUrl,
 String appPackageId,
 String appActivity
) {

    public Config() {
        this(
                "https://localhost:4723/",
                "com.hehe",
                "com.hehe.presentation.splashscreen.SplashScreenActivity"
        );
    };
}