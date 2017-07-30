# Automated web tests using scats

A simple example of some BDD-style

By default, the tests run with chrome, so you will need to download chrome driver here https://chromedriver.storage.googleapis.com/index.html?path=2.31/, then run test with command:

mvn clean verify -Dwebdriver.chrome.driver=${chromedriverpath}
Example: mvn clean verify -Dwebdriver.chrome.driver=src/test/resources/chromedriver

The reports will be generated in target/site/ by default

Note: Please check more configuration in file serenity.properties

