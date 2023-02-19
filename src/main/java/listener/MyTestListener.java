package listener;

import adapter.TestNGAdapter;
import org.testng.*;
import org.testng.internal.ConfigurationMethod;

public class MyTestListener implements ISuiteListener, ITestListener, ITestNGListener, IConfigurationListener {

    TestNGAdapter adapter = new TestNGAdapter();

    public void onStart(ISuite suite) {
        System.out.println("Listener: onStart (suite)");
        adapter.registerRunStart(suite);
    }

    public void onFinish(ISuite suite) {
        System.out.println("Listener: onFinish (suite)");
        adapter.registerRunFinish(suite);
    }

    public void onTestStart(ITestResult testResult) {
        System.out.println("Listener: onTestStart");
        adapter.registerTestStart(testResult);
    }

    public void onTestSuccess(ITestResult testResult) {
        System.out.println("Listener: onTestSuccess");
        adapter.registerTestFinish(testResult);
    }

    public void onTestFailure(ITestResult testResult) {
        System.out.println("Listener: onTestFailure");
        adapter.registerFailedTestFinish(testResult);
    }

    public void onTestSkipped(ITestResult testResult) {
        System.out.println("Listener: onTestSkipped");
        adapter.registerSkippedTestFinish(testResult);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        System.out.println("Listener: onTestFailedBut...");
        adapter.registerTestFinish(testResult);
    }

    public void onStart(ITestContext context) {
        System.out.println("Listener: onStart (not suite)");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Listener: onFinish (not suite)");
    }

//    public void beforeConfiguration(ITestResult tr, ITestNGMethod tm) {
//        System.out.println("Listener: beforeConfig");
//        ITestNGMethod testMethod = tr.getMethod();
//        if (testMethod instanceof ConfigurationMethod) {
//            ConfigurationMethod configurationMethod = (ConfigurationMethod)testMethod;
//            if (configurationMethod.isBeforeMethodConfiguration()) {
//                this.adapter.registerHeadlessTestStart(tr, tm);
//            }
//        }
//    }

//    public void onConfigurationSuccess(ITestResult tr) {
//        System.out.println("Listener: onConfigSuccess");
//
//    }
//
//    public void onConfigurationFailure(ITestResult tr) {
//        System.out.println("Listener: onConfigFailure");
//    }
//
//    public void onConfigurationSkip(ITestResult tr) {
//        System.out.println("Listener: onConfigSkip");
//    }


}
