package ru.stqa.jtl.mantis.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private DbHelper dbHelper;
    private ResetPasswordHelper resetPasswordHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("mantis-tests/src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key){
      return properties.getProperty(key);
    }

    public RegistrationHelper registration(){
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }
    public ResetPasswordHelper resetPassword(){
        if (resetPasswordHelper == null) {
            resetPasswordHelper = new ResetPasswordHelper(this);
        }
        return resetPasswordHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail(){
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james(){
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db(){
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public SoapHelper soap(){
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public WebDriver getDriver() {
        if (wd == null){
            if (browser.equals(BrowserType.CHROME)){
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)){
                System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)){
                System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.manage().window().setSize(new Dimension(1920,1080));
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
