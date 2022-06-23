package ru.stqa.jtl.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.jtl.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("mantis-tests/src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
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
