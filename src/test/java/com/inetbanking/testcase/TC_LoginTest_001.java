package com.inetbanking.testcase;

import com.inetbanking.BaseClass;
import com.inetbanking.pageObject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001  extends BaseClass {

    @Test
    public void loginTest() throws IOException {
        logger.info("Opening URL ...");
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(username);
        loginPage.setPassword(password);

        loginPage.clickSubmit();

        if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
            logger.info("Login Test Passed");
            Assert.assertTrue(true);
        }else {
            logger.info("Login Test Failed");
            captureScreen(driver, "loginTest");
            Assert.assertTrue(false);
        }
    }

}
