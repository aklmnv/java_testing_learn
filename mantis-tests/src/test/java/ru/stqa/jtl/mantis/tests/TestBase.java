package ru.stqa.jtl.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.jtl.mantis.appmanadger.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void testStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void testStop(Method m){
        logger.info("Stop test " + m.getName());
    }


}
