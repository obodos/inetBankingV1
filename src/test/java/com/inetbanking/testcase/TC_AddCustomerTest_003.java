package com.inetbanking.testcase;

import com.inetbanking.BaseClass;

import java.io.IOException;

import com.inetbanking.pageObject.AddCustomerPage;
import com.inetbanking.pageObject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AddCustomerTest_003 extends BaseClass{

    @Test
    public void addNewCustomer() throws InterruptedException, IOException
    {
        LoginPage loginPage =new LoginPage(driver);
        loginPage.setUserName(username);
        logger.info("User name is provided");
        loginPage.setPassword(password);
        logger.info("Passsword is provided");
        loginPage.clickSubmit();

        Thread.sleep(3000);

        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);

        addCustomerPage.clickAddNewCustomer();

        logger.info("providing customer details....");

        addCustomerPage.custName("Pavan");
        addCustomerPage.custGender("male");
        addCustomerPage.custDob("15","10","1985");
        Thread.sleep(5000);
        addCustomerPage.custAddress("INDIA");
        addCustomerPage.custCity("HYD");
        addCustomerPage.custState("AP");
        addCustomerPage.custPinno("5000074");
        addCustomerPage.custTelephoneno("987890091");
        String email = randomString()+"@gmail.com";
        logger.info("Random Email : "  +  email);
        addCustomerPage.custEmailid(email);
        addCustomerPage.custPassword("abcdef");

        addCustomerPage.custSubmit();

        Thread.sleep(3000);

        logger.info("validation started....");

        if(driver.getPageSource().contains("Customer Registered Successfully!!!")){
            logger.info("test case passed....");
            Assert.assertTrue(true);
        }else{
            logger.info("test case failed....");
            captureScreen(driver,"addNewCustomer");
            Assert.assertTrue(false);
        }

    }
}