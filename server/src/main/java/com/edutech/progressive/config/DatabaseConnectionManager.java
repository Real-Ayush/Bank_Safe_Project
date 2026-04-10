package com.edutech.progressive.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnectionManager {
    private static Properties obj=new Properties();
    private static void loadProperties(){
        try {
            InputStream input=DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties");
            if(input==null){
                throw new RuntimeException("application.properties not found");
            }
            obj.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException{
       if(obj.isEmpty()){
        loadProperties();
       }
       String url=obj.getProperty("url");
       String username=obj.getProperty("username");
       String password=obj.getProperty("password");
       String driver=obj.getProperty("driver-class-name");
       try {
        Class.forName(driver);
       } catch (ClassNotFoundException e) {
        throw new RuntimeException("Driver not found",e);}
        return DriverManager.getConnection(url,username,password);
    }
}