import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;

public class AndroidTest {
    private static final String BASE_URL = "http://localhost:4723/" ;

    public static void main(String[] args) throws MalformedURLException {
        Arrays.stream(new AndroidTest().getAvailableDevices()).forEach(it -> {
            Runnable runnable = () -> {
                try {
                    testApp(it.udid) ;
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            };

            new Thread(runnable).start();
        });

    }

    private static void testApp(String udid) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage("com.alfamart.apo");

        options.setCapability("platformName", "Android");
        options.setCapability("appium:udid", udid);

        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(
                    URI.create(BASE_URL + "wd/hub").toURL(),
                    options
            );
        } catch(MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        } finally {
            assert driver != null;
            driver.quit();
        }

    }

    private Device[] getAvailableDevices() throws MalformedURLException {
        URI devicesUrl = URI.create(BASE_URL + "device-farm/api/device") ;

        Request request = new Request
                .Builder()
                .get()
                .url(devicesUrl.toURL())
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response res = client.newCall(request).execute()){

            Gson gson = new GsonBuilder().create() ;
            assert res.body() != null;

            return gson.fromJson(res.body().string(), Device[].class);
        } catch (IOException e) {
            System.out.println("YAHHH " + e) ;
            return new Device[0];
        }
    }
}
