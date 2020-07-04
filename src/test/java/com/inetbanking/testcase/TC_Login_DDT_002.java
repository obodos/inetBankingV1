package com.inetbanking.testcase;

import com.inetbanking.BaseClass;
import com.inetbanking.pageObject.LoginPage;
import com.inetbanking.utility.XLUtil;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_Login_DDT_002  extends BaseClass {

    @Test(dataProvider="LoginData")
    public void loginDDT(String user,String pwd) throws InterruptedException
    {
        LoginPage loginPage =new LoginPage(driver);
        loginPage.setUserName(user);
        logger.info("user name provided");
        loginPage.setPassword(pwd);
        logger.info("password provided");
        loginPage.clickSubmit();

        Thread.sleep(5000);

        if(isAlertPresent()==true)
        {
            driver.switchTo().alert().accept();//close alert
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            logger.warn("Login failed");
        }
        else
        {
            Assert.assertTrue(true);
            logger.info("Login passed");
            loginPage.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();//close logout alert
            Thread.sleep(3000);
            driver.switchTo().defaultContent();

        }


    }

    @DataProvider(name="LoginData")
    Object[] getData() throws IOException{

        String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";

        //String path = "D:/pavanoltraining/src/test/java/com/pavanoi/TestData/LoginData.xlsx";

        int rownum=XLUtil.getRowCount(path, "Sheet1");
        int colcount= XLUtil.getCellCount(path,"Sheet1",1);

        String logindata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                logindata[i-1][j]=XLUtil.getCellData(path,"Sheet1", i,j);//1 0
            }

        }
        return logindata;
    }

    public boolean isAlertPresent() throws InterruptedException //user defined method created to check alert is present or not
    {
        try
        {
            driver.switchTo().alert();
            Thread.sleep(3000);
            return true;
        }
        catch(NoAlertPresentException e)
        {
            return false;
        }

    }
}
