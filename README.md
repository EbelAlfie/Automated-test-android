# Setup

## Prerequisite
- Node Js
- Appium
- InteliJ or any IDE that supports java
- Gradle
- Java JDK 22

## Setup Appium
Open your terminal/ command prompt and paste this command :
```
npm install --location=global appium
```
This will install appium to global node modules. Then, to start the server, you can simply run

[Setup](hthttps://appium.io/docs/en/2.0/quickstart/install/tp:// "Setup")

## Install Device farm plugins for appium
Install the device farm plugins with this command:
```
appium plugin install --source=npm appium-device-farm
```
After that, you can start the appium server and apply the device farm plugins with this
```
appium server -ka 800 --use-plugins=device-farm  -pa /wd/hub --plugin-device-farm-platform=android
```

for more information about what options you could use, you can see this docs [Appium CLI reference](https://appium.io/docs/en/2.3/cli/args/ "Appium CLI reference")

## Setup the project
After git checkingout the master branch, you should see the whole project and the directories.
navigate to  test/java/base/Config.java directory and then you should see this code
```
public Config() {
        this.baseUrl = "http://localhost:4723/";
        this.appPackageId = "appID";
        this.appActivity = "MainActivity";
        this.platform = "android";
    }
```

change the baseUrl to where your device farm is located (The default one is localhost:4723)
then, change the app package ID and the app activity that you want to test
