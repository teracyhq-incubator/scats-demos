# run on mobile (run test on MacOSX)
1. Install appium: 
```
npm install -g appium
```
2. To run test on android, install adb
Install homebrew
```
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
Install adb
```
brew cask install android-platform-tools
```
Start using adb
```
adb devices
```
3. Connect mobile device with computer by turning on "USB debugging" mode
```
Go to Settings -> Developer Options -> Check "USB debugging"
```
4. Update serenity.properties
```
appium.hub = http://127.0.0.1:4723/wd/hub
appium.platformName = Android
appium.platformVersion = 5.1
appium.deviceName = 0217da38
appium.browserName = Chrome
```
5. Open command line, type
```
appium
```
6. Run test by maven command or eclipse
```
mvn test
```

The reports will be generated in target/site/ by default

Note: Please check more configuration in file serenity.properties

