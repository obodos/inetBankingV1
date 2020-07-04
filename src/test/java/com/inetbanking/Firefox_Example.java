package com.inetbanking;
//https://www.browserstack.com/guide/run-selenium-tests-using-firefox-driver
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Firefox_Example{
    public static void main(String[] args) {
        String propertyPath = System.getProperty("user.dir");
        String Path_of_Firefox_Driver = propertyPath+"/src/test/resources/driver/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver",Path_of_Firefox_Driver);
                WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Browserstack Guide"); //name locator for text box
        WebElement searchbutton = driver.findElement(By.name("btnK"));//name locator for google search
        searchbutton.click();
        driver.quit();
    }
}