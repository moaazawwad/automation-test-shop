package testcases;

import com.aventstack.chaintest.plugins.ChainTestListener;

import com.github.javafaker.Faker;
import common.MyScreenRecorder;
import drivers.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static drivers.DriverHolder.getDriver;
import static drivers.DriverHolder.setDriver;
import static pages.PageBase.quitBrowser;
import static util.Utility.openBrowserNetworkTab;

@Listeners(ChainTestListener.class)
public class TestBase {

    Faker faker = new Faker();
    private static String PROJECT_URL = null;
    static Properties prop;
    static FileInputStream readProperty;

    @BeforeSuite
    public void beforeSuite() throws Exception {
        // MyScreenRecorder.startRecording("taskname-TestCases");
        setProjectDetails();
    }

    private void setProjectDetails() throws IOException {
        // TODO: Step1: define object of properties file
        readProperty = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/properties/environment.properties");
        prop = new Properties();
        prop.load(readProperty);

        PROJECT_URL = prop.getProperty("url");
    }

    @Parameters({"browsername"})

    @BeforeTest
    public void OpenBrower(@Optional String browsername) throws AWTException, InterruptedException {
//      Thread.sleep(500);
        setDriver(DriverFactory.getNewInstance(browsername));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        getDriver().get(PROJECT_URL);
        // open browser network
         openBrowserNetworkTab();
    }

    @AfterTest
    public void tearDown() {
        try {
            WebDriver d = drivers.DriverHolder.getDriver();
            d.quit();
        } catch (IllegalStateException ignore) {
            // no driver for this thread
        } finally {
            drivers.DriverHolder.unload(); // دي اللي كانت ناقصة
        }
    }

    @AfterSuite
    public void afterSuite() throws Exception {
        // MyScreenRecorder.stopRecording();
    }
}
