package com.inetbanking;
import com.inetbanking.utility.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
//import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClass {

   ReadConfig readConfig = new ReadConfig();
    public static Logger logger;
    public  String baseUrl =  readConfig.getApplicationURL();  //"https://demo.guru99.com/v4/";
    public  String username =  readConfig.getUsername();//"mngr267550";
    public  String password = readConfig.getPassword(); //"YgYtAsU";
    public static WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) throws InterruptedException {
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

        if(browser.equals("chrome")) {
           System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
           driver = new ChromeDriver();
        }else  if(browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
            driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
        }else if(browser.equals("ie")){
            System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
            driver = new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        Thread.sleep(4000);

    }

    public void captureScreen(WebDriver driver, String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/screenshot/" + testCaseName + ".png");
        FileUtils.copyFile(source, target);
        logger.info("Screenshot taken");
    }

    public static String  randomString(){
       return RandomStringUtils.randomAlphabetic(8);
    }

    public static String randomNumber() {
        return RandomStringUtils.randomNumeric(4);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
