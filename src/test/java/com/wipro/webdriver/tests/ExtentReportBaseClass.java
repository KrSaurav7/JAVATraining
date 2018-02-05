package com.wipro.webdriver.tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.lang.reflect.Method;


public class ExtentReportBaseClass {

    ExtentReports extent;
    ExtentTest extentLogger;
    File file;


    @BeforeSuite
    public void initializeExtentReport() {
        //ExtentReports(String filePath,Boolean replaceExisting)
        //filepath - path of the file, in .htm or .html format - path where your report needs to generate.
        //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
        //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
        //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
        extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReport.html", true);
        //extent.addSystemInfo("Environment","Environment Name")
        extent.addSystemInfo("Host Name", "SoftwareTestingMaterial").addSystemInfo("Testing", "Automation Testing").addSystemInfo("User Name", "Kumar Saurav");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        //file = new File(System.getProperty("user.dir") + "/extent-config.xml");
        //extent.loadConfig(file);
    }
    /**
    @BeforeMethod
    public void beforeMethod(Method method) {
        extentLogger = extent.startTest("TestCase Name is :: " + this.getClass().getSimpleName());
        extentLogger.assignCategory("Practice Test");
        extentLogger.log(LogStatus.PASS, "Captured Test Case name");

    }
*/
     @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            extentLogger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
            extentLogger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            extentLogger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
        }else
        {
            extentLogger.log(LogStatus.PASS,"Tset Case  Passed is " +result.getName());
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        extent.endTest(extentLogger);
    }

    @AfterSuite
    public void deinitializeExtentReport(){
        //flush() - to write or update test information to your report.
        extent.flush();
        //Call close() at the very end of your session to clear all resources.
        //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
        //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream.
        //Once this method is called, calling any Extent method will throw an error.
        //close() - To close all the operation
        extent.close();
    }
}
