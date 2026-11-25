package utils;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.AttachmentOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {

            WebDriver driver =
                    ((BaseTest) testClass).getDriver();

            if (driver != null) {

                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                Allure.attachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
            }
        }
    }
}

