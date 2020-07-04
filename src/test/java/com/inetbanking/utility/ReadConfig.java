package com.inetbanking.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {


    private static Properties properties;

    public ReadConfig(){
        final String propertyPath = System.getProperty("user.dir");
        final String configPath = propertyPath+"/src/test/resources/configuration/config.properties";
        System.out.println("Config Path :"  +  configPath);
        final File src = new File(configPath);

        try {
            final FileInputStream fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public  String getApplicationURL()
    {
        String url= properties.getProperty("baseURL");
        return url;
    }

    public  String getUsername()
    {
        String username= properties.getProperty("username");
        return username;
    }

    public  String getPassword()
    {
        String password= properties.getProperty("password");
        return password;
    }

    public String getChromePath()
    {
        String chromepath= properties.getProperty("chromepath");
        return chromepath;
    }

    public String getIEPath()
    {
        String iepath= properties.getProperty("iepath");
        return iepath;
    }

    public String getFirefoxPath()
    {
        String firefoxpath= properties.getProperty("firefoxpath");
        return firefoxpath;
    }

}
