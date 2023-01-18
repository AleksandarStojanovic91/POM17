package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    DriverManager driverManager;
    WebDriver driver;
    String path = "src/results/screenshots/";

    public void init(String type, String wait) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(type);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(wait), TimeUnit.SECONDS);
    }

    public void quit() {
        driverManager.quitDriver();
    }

    public void openApp(String env) throws Exception {
        env = env.toUpperCase();
        switch (env) {
            case "PROD": {
                driver.get("https://play2048.co/");
            }
            break;
            default:
                throw new Exception("Environment: " + env + " not supported!");
        }
    }

    public void takeScreenshot(String fileName) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(path + fileName + ".png"));
    }

    public void reporterScreenshot(String fileName, String desc) throws IOException {
        takeScreenshot(fileName);
        Path filePath = Paths.get(path + fileName + ".png");
        InputStream is = Files.newInputStream(filePath);
        Allure.addAttachment(desc, is);
    }

}